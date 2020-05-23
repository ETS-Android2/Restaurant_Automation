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
    public class EmployeeController : ApiController
    {
        [HttpPost]
        [Route("api/registerEmployee")]
        public HttpResponseMessage registerEmployee(RegisterEmployeeRequestModel employeeRequestModel)
        {
            EmployeeProvider employeeProvider = new EmployeeProvider();
            ErrorModel errorModel = null;
            var userRegisterResponse = employeeProvider.registerEmployee(employeeRequestModel, out errorModel);

            APIResponseModel aPIResponseModel = new APIResponseModel();
            aPIResponseModel.Response = userRegisterResponse;
            aPIResponseModel.Error = errorModel;

            return Request.CreateResponse(HttpStatusCode.OK, aPIResponseModel);
        }
    }
}
