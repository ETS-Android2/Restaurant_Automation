using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Reservation
    {
        public Reservation()
        {
            Orders = new HashSet<Orders>();
            ReservationDetails = new HashSet<ReservationDetails>();
        }

        public int ReservationId { get; set; }
        public DateTime ReservationDate { get; set; }
        public int? ReservedBy { get; set; }
        public int? NumberOfPeople { get; set; }
        public TimeSpan StartTime { get; set; }
        public TimeSpan? EndTime { get; set; }

        public virtual Users ReservedByNavigation { get; set; }
        public virtual ICollection<Orders> Orders { get; set; }
        public virtual ICollection<ReservationDetails> ReservationDetails { get; set; }
    }
}
