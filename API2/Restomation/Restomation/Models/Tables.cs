using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Tables
    {
        public Tables()
        {
            ReservationDetails = new HashSet<ReservationDetails>();
        }

        public int TableId { get; set; }
        public int Capacity { get; set; }
        public bool IsTableAvailable { get; set; }

        public virtual ICollection<ReservationDetails> ReservationDetails { get; set; }
    }
}
