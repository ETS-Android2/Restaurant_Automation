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
    public class CartController : ApiController
    {
        [HttpPost]
        [Route("api/cart/addToCart")]
        public HttpResponseMessage addToCart(AddToCartRequestModel addToCartRequestModel)
        {
            CartProvider cartProvider = new CartProvider();
            ErrorModel errorModel = null;
            var cartStatus = cartProvider.addToCart(addToCartRequestModel, out errorModel);
            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = cartStatus;
            aPIResponseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }


        [HttpGet]
        [Route("api/cart/cartItems")]
        //[ApiAuthorization]
        public HttpResponseMessage getCartItems()
        {
            CartProvider cartProvider = new CartProvider();
            ErrorModel errorModel = null;

            var cartItems = cartProvider.getCartItems( out errorModel);
            APIResponseModel responseModel = new APIResponseModel();
            responseModel.Response = menuItems;
            responseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, responseModel);
        }
    }
}
