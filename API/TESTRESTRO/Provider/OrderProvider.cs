using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Provider
{
    public class OrderProvider
    {
        public OrderCartItemResponseModel addOrder(OrderCartItemRequestModel orderCartItemRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Order orderHelper = new Order();
                OrderCartItemResponseModel response = orderHelper.addOrder(orderCartItemRequestModel, out errorModel);
                return response;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public List<GetOrderHistoryResponseModel> getOrdersHistory(string startDate, string endDate, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Order orderHelper = new Order();
                return orderHelper.getOrdersHistory(startDate, endDate, out errorModel);
            }
            catch (Exception)
            {
                return null;
            }
        }

        public List<GetOrdersResponseModel> getOrders(GetOrdersRequestModel getOrdersRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Order orderHelper = new Order();
                return orderHelper.getOrders(getOrdersRequestModel, out errorModel);
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}