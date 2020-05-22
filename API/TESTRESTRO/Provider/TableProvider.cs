using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using RESTRODBACCESS.Helper;
using RESTRODBACCESS.ResponseModel;

namespace TESTRESTRO.Provider
{
    public class TableProvider
    {
        public List<GetTableResponseModel> getTables(int tableId, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Table tableHelper = new Table();
                List<GetTableResponseModel> tableItems = tableHelper.getTables(tableId, out errorModel);
                return tableItems;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}