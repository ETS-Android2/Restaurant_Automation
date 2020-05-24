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
    public class Employee
    {
        public UserRegisterResponseModel registerEmployee(RegisterEmployeeRequestModel employeeRegisterModel, out ErrorModel errorModel)
        {
            errorModel = null;
            UserRegisterResponseModel userRegisterResponse = null;
            SqlConnection connection = null;
            try
            {
                using(connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_registerEmployee, connection);
                    command.CommandType = System.Data.CommandType.StoredProcedure;

                    #region Commands Parameters
                    command.Parameters.AddWithValue("email", employeeRegisterModel.email);
                    command.Parameters.AddWithValue("password", employeeRegisterModel.email);
                    command.Parameters.AddWithValue("fname", employeeRegisterModel.firstName);
                    command.Parameters.AddWithValue("lname", employeeRegisterModel.lastName);
                    command.Parameters.AddWithValue("phone", employeeRegisterModel.phone);
                    command.Parameters.AddWithValue("userTypeId", employeeRegisterModel.userType);
                    command.Parameters.AddWithValue("gender", employeeRegisterModel.gender);

                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();

                    userRegisterResponse = new UserRegisterResponseModel();
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
                            userRegisterResponse.Email = reader["email"].ToString();
                            userRegisterResponse.UserId = reader["userId"].ToString();
                        }
                    }
                    command.Dispose();
                    return userRegisterResponse;
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
                if (connection != null)
                {
                    connection.Close();
                }
            }
        }

        
        
    }
}
