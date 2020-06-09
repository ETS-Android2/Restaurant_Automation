using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
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

        [HttpGet]
        [Route("api/orders/getOrdersHistory/{startDate}/{endDate}")]
        public HttpResponseMessage getOrdersHistory(string startDate, string endDate)
        {
            OrderProvider orderProvider = new OrderProvider();
            ErrorModel errorModel = null;
            var orders = orderProvider.getOrdersHistory(startDate, endDate, out errorModel);
            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = orders;
            aPIResponseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }

        [HttpGet]
        [Route("api/orders/getOrders/{customerId:int?}/{fromDate}/{toDate}/{email}/")]
        public HttpResponseMessage getOrders(int customerId, string fromDate, string toDate, string email)
        {
            GetOrdersRequestModel requestModel = new GetOrdersRequestModel();
            requestModel.customerId = customerId;
            requestModel.fromDate = fromDate;
            requestModel.toDate = toDate;
            requestModel.email = email;
            if (requestModel.email.Equals("0")) requestModel.email = null;
            if (requestModel.fromDate.Equals("0")) requestModel.fromDate = null;
            if (requestModel.toDate.Equals("0")) requestModel.toDate = null;
            if (requestModel.customerId == 0) requestModel.customerId = 0;
            ErrorModel errorModel = null;
            OrderProvider orderProvider = new OrderProvider();
            var orders = orderProvider.getOrders(requestModel, out errorModel);
            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = orders;
            aPIResponseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }

        [HttpPost]
        [Route("api/orders/updateOrderStatus")]
        public HttpResponseMessage updateOrderStatus(ChangeOrdersStatusRequestModel changeOrdersStatusRequestModel)
        {
            ErrorModel errorModel = null;
            OrderProvider provider = new OrderProvider();
            var status = provider.updateOrderStatus(changeOrdersStatusRequestModel, out errorModel);
            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = status;
            aPIResponseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }
    }
}
