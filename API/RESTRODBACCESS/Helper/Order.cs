using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections;
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

        public List<GetOrderHistoryResponseModel> getOrdersHistory(string startDate, string endDate, out ErrorModel errorModel)
        {
            errorModel = null;
            List<GetOrderHistoryResponseModel> list;
            SqlConnection connection = null;
            try
            {
                using(connection = new SqlConnection(Database.getConnectionString()))
                {
                    string query = "Select Orders.orderId, Orders.reservationId, Orders.orderDate, Orders.isDiningIn, OrderHistory.statusId as currentStatusId, OrderStatus.orderStatusTitle, Users.userId, Users.email as reservedBy, count(itemId) as totalItems from Orders"+
                                    " Inner join OrderHistory on OrderHistory.orderId = Orders.orderId"+
                                    " inner join OrderDetails on OrderDetails.orderId = OrderHistory.orderId"+
                                    " inner join OrderStatus on OrderStatus.orderStatusId = OrderHistory.statusId"+
                                    " inner join Reservation on Reservation.reservationId = Orders.reservationId"+
                                    " inner join Users on Reservation.reservedBy = Users.userId"+
                                    " where Orders.orderDate between '"+startDate+"' and '"+endDate+"'"+
                                    "group by Orders.orderId, Orders.reservationId, Orders.orderDate, Orders.isDiningIn, OrderHistory.statusId, OrderStatus.orderStatusTitle, Users.userId, Users.email";
                    SqlCommand sqlCommand = new SqlCommand(query, connection);
                    connection.Open();
                    SqlDataReader reader = sqlCommand.ExecuteReader();
                    list = new List<GetOrderHistoryResponseModel>();
                    while (reader.Read())
                    {
                        GetOrderHistoryResponseModel getOrderHistoryResponse = new GetOrderHistoryResponseModel();
                        getOrderHistoryResponse.orderId = Convert.ToInt32(reader["orderId"].ToString());
                        getOrderHistoryResponse.userId = Convert.ToInt32(reader["userId"].ToString());
                        getOrderHistoryResponse.totalItems = Convert.ToInt32(reader["totalItems"].ToString());
                        getOrderHistoryResponse.currentStatusId = Convert.ToInt32(reader["currentStatusId"].ToString());
                        getOrderHistoryResponse.isDineIn = Convert.ToBoolean(reader["isDiningIn"].ToString());
                        getOrderHistoryResponse.orderDate = reader["orderDate"].ToString();
                        getOrderHistoryResponse.reservationId = Convert.ToInt32(reader["reservationId"].ToString());
                        getOrderHistoryResponse.reservedBy = reader["reservedBy"].ToString();
                        getOrderHistoryResponse.orderStatusTitle = reader["orderStatusTitle"].ToString();
                        list.Add(getOrderHistoryResponse);
                    }
                    sqlCommand.Dispose();
                    return list;
                }
            }
            catch(Exception e)
            {
                errorModel = new ErrorModel();
                errorModel.ErrorMessage = e.Message;
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
