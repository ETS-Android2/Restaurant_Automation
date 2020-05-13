using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class OrderDetails
    {
        public int OrderId { get; set; }
        public int ItemId { get; set; }
        public int ItemQty { get; set; }

        public virtual MenuItems Item { get; set; }
        public virtual Orders Order { get; set; }
    }
}
