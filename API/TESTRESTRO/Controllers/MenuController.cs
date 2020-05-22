using RESTRODBACCESS.RequestModel;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using TESTRESTRO.Models;
using TESTRESTRO.Provider;

namespace TESTRESTRO.Controllers
{
    public class MenuController : ApiController
    {
        [HttpGet]
        [Route("api/menu/{categoryId:int?}")]
        //[ApiAuthorization]
        public HttpResponseMessage getAllMenuItems(int categoryId = 0)
        {
            MenuProvider menuProvider = new MenuProvider();
            ErrorModel errorModel = null;
            
            var menuItems = menuProvider.getAllMenuItems(categoryId, out errorModel);
            APIResponseModel responseModel = new APIResponseModel();
            responseModel.Response = menuItems;
            responseModel.Error = errorModel;
            return Request.CreateResponse(HttpStatusCode.OK, responseModel);
        }
    }
}
