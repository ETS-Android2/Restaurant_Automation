using RESTRODBACCESS.RequestModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using TESTRESTRO.Models;
using TESTRESTRO.Provider;

namespace TESTRESTRO.Controllers
{
    public class OrderController : ApiController
    {
        [HttpPost]
        [Route("api/orders/addOrders")]
        public HttpResponseMessage addOrders(OrderCartItemRequestModel orderCartItemRequestModel)
        {
            OrderProvider orderProvider = new OrderProvider();
            ErrorModel errorModel = null;
            var order = orderProvider.addOrder(orderCartItemRequestModel, out errorModel);

            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = order;
            aPIResponseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }
    }
}
