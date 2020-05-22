using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Web;
using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
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

        public ReserveTableResponseModel reserveTable(ReserveTableRequestModel reserveTableRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Table tableHelper = new Table();
                ReserveTableResponseModel model = tableHelper.reserveTable(reserveTableRequestModel, out errorModel);
                return model;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}