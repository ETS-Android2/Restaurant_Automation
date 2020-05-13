using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class OrderFlow
    {
        public int OrderFlowId { get; set; }
        public int CurrentStatus { get; set; }
        public int? NextStatus { get; set; }

        public virtual OrderStatus CurrentStatusNavigation { get; set; }
        public virtual OrderStatus NextStatusNavigation { get; set; }
    }
}
