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
    public class Menu
    {
        public List<MenuItemResponseModel> getMenuItems(int categoryId, out ErrorModel errorModel)
        {
            errorModel = null;
            List<MenuItemResponseModel> menuItems = null;
            SqlConnection connection = null;
            try
            {
                using (connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_getMenuItems, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    #region Query Parameters

                   
                    
                    command.Parameters.Add(new SqlParameter("@categoryId", System.Data.SqlDbType.Int));
                    command.Parameters["@categoryId"].Value = categoryId;

                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    menuItems = new List<MenuItemResponseModel>();
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
                            MenuItemResponseModel menuItemResponse = new MenuItemResponseModel();
                            menuItemResponse.menuItemId = Convert.ToInt32(reader["ItemId"].ToString());
                            menuItemResponse.menuItemName = reader["ItemName"].ToString();
                            menuItemResponse.menuItemDescription = reader["Description"].ToString();
                            menuItemResponse.price = Convert.ToDouble(reader["Price"].ToString());
                            menuItemResponse.availablequantity = Convert.ToInt32(reader["QuantityAvailable"].ToString());
                            menuItemResponse.itemStatus = Convert.ToBoolean(reader["ItemStatus"].ToString());
                            menuItemResponse.categoryId = Convert.ToInt32(reader["CategoryId"].ToString());
                            menuItemResponse.userId = Convert.ToInt32(reader["CreatedById"].ToString());
                            menuItemResponse.updatedBy = reader["UpdatedBy"].ToString();
                            menuItemResponse.deletedBy = reader["DeletedBy"].ToString();
                            menuItemResponse.createdBy = reader["CreatedBy"].ToString();
                            menuItemResponse.categoryTitle = reader["CategoryTitle"].ToString();




                            menuItems.Add(menuItemResponse);
                        }
                    }
                    command.Dispose();
                    connection.Close();
                }

                return menuItems;
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
