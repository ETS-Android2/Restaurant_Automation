using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Provider
{
    public class CartProvider
    {
        public AddToCartResponseModel addToCart(AddToCartRequestModel addToCartRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Cart cartHelper = new Cart();
                return cartHelper.addToCart(addToCartRequestModel, out errorModel);
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}