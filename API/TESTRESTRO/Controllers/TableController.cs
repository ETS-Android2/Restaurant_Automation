using RESTRODBACCESS.RequestModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Routing;
using TESTRESTRO.Models;
using TESTRESTRO.Provider;

namespace TESTRESTRO.Controllers
{
    public class TableController : ApiController
    {
        [HttpGet]
        [Route("api/table/{tableId:int?}")]
        public HttpResponseMessage getTables(int tableId = 0)
        {
            TableProvider tableProvider = new TableProvider();
            ErrorModel errorModel = null;

            var tableItems = tableProvider.getTables(tableId, out errorModel);
            APIResponseModel responseModel = new APIResponseModel();
            responseModel.Response = tableItems;
            responseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, responseModel);
        }




        [HttpPost]
        [Route("api/reserveTable/")]
        public  HttpResponseMessage reserveTable(ReserveTableRequestModel model)
        {
            TableProvider tableProvider = new TableProvider();
            ErrorModel errorModel = null;

            var tableItems = tableProvider.reserveTable(model, out errorModel);
            APIResponseModel responseModel = new APIResponseModel();
            responseModel.Response = tableItems;
            responseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, responseModel);
        }

        [HttpPost]
        [Route("api/addTable")]
        public HttpResponseMessage addTables(AddTableRequestModel addTableRequestModel)
        {
            TableProvider tableProvider = new TableProvider();
            ErrorModel errorModel = null;
            var table = tableProvider.addTable(addTableRequestModel, out errorModel);

            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = table;
            aPIResponseModel.Error = errorModel;

            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }
    }
}
