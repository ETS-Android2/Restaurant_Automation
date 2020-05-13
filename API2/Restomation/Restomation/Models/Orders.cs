using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Orders
    {
        public Orders()
        {
            OrderDetails = new HashSet<OrderDetails>();
            OrderHistory = new HashSet<OrderHistory>();
        }

        public int OrderId { get; set; }
        public DateTime OrderDate { get; set; }
        public int ReservationId { get; set; }
        public bool IdDiningIn { get; set; }

        public virtual Reservation Reservation { get; set; }
        public virtual ICollection<OrderDetails> OrderDetails { get; set; }
        public virtual ICollection<OrderHistory> OrderHistory { get; set; }
    }
}
