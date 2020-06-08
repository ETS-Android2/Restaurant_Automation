﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTRODBACCESS.ResponseModel
{
    public class GetOrdersResponseModel
    {
        public int orderId { get; set; }
        public string orderDate { get; set; }
        public bool isDiningIn { get; set; }
        public string orderStatusTitle { get; set; }
        public double billingAmount { get; set; }
        public bool isCardPayment { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public List<MenuItems> menuItems { get; set; }
    }
    public class MenuItems
    {
        public string itemName { get; set; }
        public int itemQty { get; set; }
        public string itemDescription { get; set; }
        public double itemPrice { get; set; }
        public string category { get; set; }
    }
}
