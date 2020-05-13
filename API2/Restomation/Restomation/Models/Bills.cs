using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Bills
    {
        public int? BillId { get; set; }
        public int? OrderId { get; set; }
        public DateTime? BillDate { get; set; }
        public decimal BillingAmount { get; set; }
        public bool IsCardPayment { get; set; }

        public virtual Orders Order { get; set; }
    }
}
