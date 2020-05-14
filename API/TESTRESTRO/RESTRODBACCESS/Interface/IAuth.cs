using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TESTRESTRO;

namespace RESTRODBACCESS.Interface
{
    interface IAuth
    {
        UserLoginResponseModel login(UserLoginRequestModel userLoginRequestModel, out ErrorModel errorModel);
    }
}
