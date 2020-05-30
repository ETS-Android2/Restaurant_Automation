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
    public class Order
    {
        public OrderCartItemResponseModel addOrder(OrderCartItemRequestModel orderCartItemRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            OrderCartItemResponseModel orderCartItemResponseModel = null;
            SqlConnection connection = null;
            try
            {
                using (connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_orderCartItem, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;
                    #region Command Parameters
                    command.Parameters.AddWithValue("orderBy", orderCartItemRequestModel.orderBy);
                    command.Parameters.AddWithValue("isDiningIn", orderCartItemRequestModel.isDiningIn);
                    command.Parameters.AddWithValue("isCardPayment", orderCartItemRequestModel.isCardPayment);
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
                            orderCartItemResponseModel = new OrderCartItemResponseModel();
                            orderCartItemResponseModel.billId = Convert.ToInt32(reader["billId"].ToString());
                            orderCartItemResponseModel.billDate = reader["billDate"].ToString();
                            orderCartItemResponseModel.billAmount = Convert.ToDecimal(reader["billAmount"].ToString());
                        }
                    }
                    command.Dispose();
                    return orderCartItemResponseModel;
                }
            }
            catch (Exception e)
            {
                errorModel = new ErrorModel();
                errorModel.ErrorMessage = e.Message;
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
