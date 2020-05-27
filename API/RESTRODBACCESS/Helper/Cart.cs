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
    public class Cart
    {
        public AddToCartResponseModel addToCart(AddToCartRequestModel addToCartRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            AddToCartResponseModel addToCartResponseModel = null;
            SqlConnection connection = null;
            try
            {
                using(connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_addToCart, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;
                    #region Command Parameters
                    command.Parameters.AddWithValue("itemId", addToCartRequestModel.itemId);
                    command.Parameters.AddWithValue("addedBy", addToCartRequestModel.addedby);
                    command.Parameters.AddWithValue("qty", addToCartRequestModel.quantity);
                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    if (reader.Read())
                    {
                        addToCartResponseModel = new AddToCartResponseModel();
                        addToCartResponseModel.StatusCode = reader["StatusCode"].ToString();
                        addToCartResponseModel.StatusMessage = reader["StatusMessage"].ToString();
                    }
                    command.Dispose();
                    return addToCartResponseModel;
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
                if(connection != null)
                {
                    connection.Close();
                }
            }
        }
    }
}
