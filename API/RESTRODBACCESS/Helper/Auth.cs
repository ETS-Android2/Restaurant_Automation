﻿using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Data;
using System.Data.SqlClient;
using TESTRESTRO;

namespace RESTRODBACCESS.Helper
{
    public class Auth
    {


        public bool isLoggedIn()
        {
            throw new NotImplementedException();
        }
        public UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            UserLoginResponseModel userLoginResponseModel = null;
            SqlConnection connection = null;
            try
            {
                using (connection = new SqlConnection(Database.getConnectionString()))
                {
                    SqlCommand command = new SqlCommand(SqlCommands.SP_LoginUser, connection);
                    command.CommandType = CommandType.StoredProcedure;

                    #region Query Parameters
                    command.Parameters.Add(new SqlParameter("@email", System.Data.SqlDbType.VarChar, 150));
                    command.Parameters["@email"].Value = userLoginRequestModel.Email;

                    command.Parameters.Add(new SqlParameter("@password", System.Data.SqlDbType.VarChar, 16));
                    command.Parameters["@password"].Value = userLoginRequestModel.Password;

                    //command.Parameters.Add(new SqlParameter("@userType", System.Data.SqlDbType.Int));
                    //command.Parameters["@userType"].Value = userLoginRequestModel.UserType;
                    #endregion
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
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
                            userLoginResponseModel = new UserLoginResponseModel();
                            userLoginResponseModel.Email = reader["Email"].ToString();
                            userLoginResponseModel.UserId = reader["UserId"].ToString();
                            userLoginResponseModel.Name = reader["Name"].ToString();
                            userLoginResponseModel.UserType =reader["UserType"].ToString();
                        }
                    }
                    command.Dispose();
                    connection.Close();
                }

                return userLoginResponseModel;
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
