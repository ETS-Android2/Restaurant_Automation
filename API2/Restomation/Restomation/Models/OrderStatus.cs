using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class OrderStatus
    {
        public OrderStatus()
        {
            OrderFlowCurrentStatusNavigation = new HashSet<OrderFlow>();
            OrderFlowNextStatusNavigation = new HashSet<OrderFlow>();
            OrderHistory = new HashSet<OrderHistory>();
        }

        public int OrderStatusId { get; set; }
        public string OrderStatusTitle { get; set; }

        public virtual ICollection<OrderFlow> OrderFlowCurrentStatusNavigation { get; set; }
        public virtual ICollection<OrderFlow> OrderFlowNextStatusNavigation { get; set; }
        public virtual ICollection<OrderHistory> OrderHistory { get; set; }
    }
}
