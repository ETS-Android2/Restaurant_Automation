using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class ReservationDetails
    {
        public int TableId { get; set; }
        public int ReservationId { get; set; }

        public virtual Reservation Reservation { get; set; }
        public virtual Tables Table { get; set; }
    }
}
