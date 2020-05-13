using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class OrderHistory
    {
        public int OrderId { get; set; }
        public int StatusId { get; set; }
        public DateTime CreatedTime { get; set; }

        public virtual Orders Order { get; set; }
        public virtual OrderStatus Status { get; set; }
    }
}
