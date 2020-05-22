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
    }
}
