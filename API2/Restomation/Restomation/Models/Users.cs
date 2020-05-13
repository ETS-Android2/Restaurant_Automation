using System;
using System.Collections.Generic;

namespace Restomation.Models
{
    public partial class Users
    {
        public Users()
        {
            Cart = new HashSet<Cart>();
            MenuItemsCreatedByNavigation = new HashSet<MenuItems>();
            MenuItemsDeletedByNavigation = new HashSet<MenuItems>();
            MenuItemsUpdatedByNavigation = new HashSet<MenuItems>();
            Reservation = new HashSet<Reservation>();
        }

        public int UserId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Gender { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string PhoneNo { get; set; }
        public string ProfileImage { get; set; }
        public DateTime CreatedDate { get; set; }
        public DateTime? UpdatedDate { get; set; }
        public DateTime? DeletedDate { get; set; }
        public int? UserTypeId { get; set; }

        public virtual UserType UserType { get; set; }
        public virtual Tokens Tokens { get; set; }
        public virtual ICollection<Cart> Cart { get; set; }
        public virtual ICollection<MenuItems> MenuItemsCreatedByNavigation { get; set; }
        public virtual ICollection<MenuItems> MenuItemsDeletedByNavigation { get; set; }
        public virtual ICollection<MenuItems> MenuItemsUpdatedByNavigation { get; set; }
        public virtual ICollection<Reservation> Reservation { get; set; }
    }
}
