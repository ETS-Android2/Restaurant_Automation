using RESTRODBACCESS.Helper;
using RESTRODBACCESS.RequestModel;
using RESTRODBACCESS.ResponseModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Provider
{
    public class EmployeeProvider
    {
        public UserRegisterResponseModel registerEmployee(RegisterEmployeeRequestModel employeeRequestModel, out ErrorModel errorModel)
        {
            errorModel = null;
            try
            {
                Employee employeeHelper = new Employee();
                return employeeHelper.registerEmployee(employeeRequestModel, out errorModel);
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}