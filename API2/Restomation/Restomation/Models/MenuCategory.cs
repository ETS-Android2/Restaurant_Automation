using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class MenuCategory
    {
        public MenuCategory()
        {
            MenuItems = new HashSet<MenuItems>();
        }

        public int CategoryId { get; set; }
        public string CategoryTitle { get; set; }

        public virtual ICollection<MenuItems> MenuItems { get; set; }
    }
}
