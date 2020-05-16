using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TESTRESTRO.Models
{
    public class APIResponseModel
    {
        public object Response { get; set; }
        public ErrorModel Error { get; set; }
    }
}