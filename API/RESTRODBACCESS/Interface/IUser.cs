using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TESTRESTRO;

namespace RESTRODBACCESS.Interface
{
    interface IUser
    {
        UserRegisterResponseModel register(string email, string password,string fname, string lname,string phone,int userTypeId, out ErrorModel errorModel);

        List<UsersResponseModel> getUsers(int userType, /*string email,*/ out ErrorModel errorModel);
    }
}
