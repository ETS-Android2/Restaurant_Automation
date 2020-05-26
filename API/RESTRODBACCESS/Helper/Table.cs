using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TESTRESTRO;

namespace RESTRODBACCESS.Helper
{
    public class Table
    {
        public List<GetTableResponseModel> getTables(int tableId, out ErrorModel errorModel)
        {
            errorModel = null;
            List<GetTableResponseModel> tableItems = null;
            SqlConnection connection = null;
            try
            {
                using (connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_getTables, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    #region Query Parameters



                    command.Parameters.Add(new SqlParameter("@tableId", System.Data.SqlDbType.Int));
                    command.Parameters["@tableId"].Value = tableId;

                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    tableItems = new List<GetTableResponseModel>();
                    while (reader.Read())
                    {
                        if (reader.isColumnExists("ErrorCode"))
                        {
                            errorModel = new ErrorModel();
                            errorModel.ErrorCode = reader["ErrorCode"].ToString();
                            errorModel.ErrorMessage = reader["ErrorMessage"].ToString();
                        }
                        else
                        {
                            GetTableResponseModel getTableResponseModel = new GetTableResponseModel();
                            getTableResponseModel.tableId = Convert.ToInt32(reader["tableId"].ToString());
                            getTableResponseModel.capacity = Convert.ToInt32(reader["capacity"].ToString());
                            getTableResponseModel.availability = reader.GetBoolean(reader.GetOrdinal("availability"));
                          
                            tableItems.Add(getTableResponseModel);
                        }
                    }
                    command.Dispose();
                    connection.Close();
                }

                return tableItems;
            }
            catch (Exception exception)
            {
                errorModel = new ErrorModel();
                errorModel.ErrorMessage = exception.Message;
                return null;
            }
            finally
            {
                if (connection != null)
                {
                    connection.Close();
                }
            }
        }



        public ReserveTableResponseModel reserveTable(ReserveTableRequestModel reserveTableRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            ReserveTableResponseModel response = null;
            SqlConnection connection = null;
            try
            {
                using (connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_reserveTable, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    #region Query Parameters



                    command.Parameters.Add(new SqlParameter("@tableId", System.Data.SqlDbType.Int));
                    command.Parameters["@tableId"].Value = reserveTableRequestModel.tableId;

                    command.Parameters.Add(new SqlParameter("@reservationDate", System.Data.SqlDbType.DateTime));
                    command.Parameters["@reservationDate"].Value = reserveTableRequestModel.reservationDate;

                    command.Parameters.Add(new SqlParameter("@reservedBy", System.Data.SqlDbType.Int));
                    command.Parameters["@reservedBy"].Value = reserveTableRequestModel.reservedBy;

                    command.Parameters.Add(new SqlParameter("@numberOfPeople", System.Data.SqlDbType.Int));
                    command.Parameters["@numberOfPeople"].Value = reserveTableRequestModel.numberOfPeople;

                    command.Parameters.Add(new SqlParameter("@startTime", System.Data.SqlDbType.DateTime));
                    command.Parameters["@startTime"].Value = reserveTableRequestModel.startTime;

                    command.Parameters.Add(new SqlParameter("@endTime", System.Data.SqlDbType.DateTime));
                    command.Parameters["@endTime"].Value = reserveTableRequestModel.endTime;
                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    response = new ReserveTableResponseModel();
                    while (reader.Read())
                    {
                        if (reader.isColumnExists("ErrorCode"))
                        {
                            errorModel = new ErrorModel();
                            errorModel.ErrorCode = reader["ErrorCode"].ToString();
                            errorModel.ErrorMessage = reader["ErrorMessage"].ToString();
                        }
                        else
                        {
                            response.StatusCode = reader["StatusCode"].ToString();
                            response.StatusMessage = reader["StatusMessage"].ToString();
                        }
                    }
                    command.Dispose();
                    connection.Close();
                }

                return response;
            }
            catch (Exception exception)
            {
                errorModel = new ErrorModel();
                errorModel.ErrorMessage = exception.Message;
                return null;
            }
            finally
            {
                if (connection != null)
                {
                    connection.Close();
                }
            }
        }

        public GetTableResponseModel addTable(AddTableRequestModel addTableRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            GetTableResponseModel getTableResponseModel = null;
            SqlConnection connection = null;
            try
            {
                using(connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_addTable, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    #region Command Parameters
                    command.Parameters.AddWithValue("capacity", addTableRequestModel.capacity);
                    #endregion

                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    if (reader.Read())
                    {
                        if (reader.isColumnExists("ErrorCode"))
                        {
                            errorModel = new ErrorModel();
                            errorModel.ErrorCode = reader["ErrorCode"].ToString();
                            errorModel.ErrorMessage = reader["ErrorMessage"].ToString();
                        }
                        else
                        {
                            getTableResponseModel = new GetTableResponseModel();
                            getTableResponseModel.tableId = Convert.ToInt32(reader["tableId"]);
                            getTableResponseModel.capacity = Convert.ToInt32(reader["capacity"]);
                            getTableResponseModel.availability = Convert.ToBoolean(reader["availability"]);
                        }
                        command.Dispose();
                        return getTableResponseModel;
                    }
                    return null;
                }
            }
            catch (Exception exception)
            {
                errorModel = new ErrorModel();
                errorModel.ErrorMessage = exception.Message;
                return null;
            }
            finally
            {
                if(connection != null)
                {
                    connection.Close();
                }
            }
        }

    }
}
