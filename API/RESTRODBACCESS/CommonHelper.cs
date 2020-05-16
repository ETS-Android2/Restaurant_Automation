using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTRODBACCESS
{
    static class CommonHelper
    {
        public static bool isColumnExists(this SqlDataReader reader, string columnName)
        {
            if (reader != null)
            {
                try
                {
                    return reader.GetOrdinal(columnName) >= 0;
                }
                catch (IndexOutOfRangeException)
                {
                    return false;
                }
            }
            return false;
        }
    }
}
