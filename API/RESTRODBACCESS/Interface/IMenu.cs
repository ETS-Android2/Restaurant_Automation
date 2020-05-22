using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TESTRESTRO;

namespace RESTRODBACCESS.Interface
{
    interface IMenu
    {

        List<MenuItemResponseModel> getMenuItems(int categoryId, out ErrorModel errorModel);
    }
}
