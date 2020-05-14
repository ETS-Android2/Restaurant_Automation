using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTRODBACCESS.ResponseModel
{
    public class UserLoginResponseModel
    {
        public string Email { get; set; }
        public string UserId { get; set; }
        public string Token { get; set; }
        public string Name { get; set; }
    }
}
