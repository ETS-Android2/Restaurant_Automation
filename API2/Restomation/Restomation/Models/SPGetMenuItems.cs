using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Restomation.Models
{
    public class SPGetMenuItems
    {
        public int ItemId { get; set; }
        public string ItemName { get; set; }
        public string ItemDescription { get; set; }
        public decimal Price { get; set; }
        public string? CategoryTitle { get; set; }
        public int AvailableQty { get; set; }
        public bool ItemStatusTitle { get; set; }
        public int CreatedBy { get; set; }
        public int? UpdatedBy { get; set; }
        public int? DeletedBy { get; set; }
    }
}
