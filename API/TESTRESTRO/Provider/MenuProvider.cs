using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Provider
{
    public class MenuProvider
    {
        public List<MenuItemResponseModel> getAllMenuItems(int categoryId, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Menu menuHelper = new Menu();
                List<MenuItemResponseModel> menuItems = menuHelper.getMenuItems(categoryId, out errorModel);
                return menuItems;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}