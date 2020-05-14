using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using TESTRESTRO.Models;

namespace TESTRESTRO.Provider
{
    public class AuthProvider
    {
        public UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Auth authHelper = new Auth();
                UserLoginResponseModel loginResponseModel = authHelper.login(userLoginRequestModel, out errorModel);

                return loginResponseModel;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}