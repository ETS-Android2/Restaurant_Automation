using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTRODBACCESS
{
    class Database
    {
        private Database() { }

        public static string getConnectionString()
        {
            return ConfigurationManager.ConnectionStrings["RestaurantAutomation"].ToString();
        }
    }
}
