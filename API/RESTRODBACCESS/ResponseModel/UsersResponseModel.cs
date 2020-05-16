using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTRODBACCESS.ResponseModel
{
    public class UsersResponseModel
    {
        public int UserType { get; set; }
        public string Email { get; set; }
        public string FirstName { get; set; }

        public int Id { get; set; }
        public string LastName { get; set; }
        public string Phone { get; set; }
        public string Gender { get; set; }
        public string Image { get; set; }
       
    }
}
