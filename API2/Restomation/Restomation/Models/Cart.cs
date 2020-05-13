using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Cart
    {
        public int CartId { get; set; }
        public int? MenuItemId { get; set; }
        public int? AddedBy { get; set; }
        public int Quantity { get; set; }
        public bool IsOrdered { get; set; }

        public virtual Users AddedByNavigation { get; set; }
        public virtual MenuItems MenuItem { get; set; }
    }
}
