
using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Provider
{
    public class UserProvider
    {
        public UserRegisterResponseModel register(UserRegisterRequestModel userRegister, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                User userProvider = new User();
                UserRegisterResponseModel userRegisterResponse = userProvider.register(userRegister.Email, userRegister.Password,userRegister.FirstName, userRegister.lastName, userRegister.Phone,userRegister.UserType, out errorModel);
                return userRegisterResponse;
            }
            catch (Exception e)
            {
                return null;
            }
        }

        public List<UsersResponseModel> getAll(UsersRequestModel usersRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                User userHelper = new User();
                List<UsersResponseModel> users = userHelper.getUsers(usersRequestModel.userType, /*usersRequestModel.email,*/ out errorModel);
                return users;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}