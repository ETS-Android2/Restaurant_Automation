package a.m.restaurant_automation.responseModel;

import java.io.Serializable;
import java.util.ArrayList;

public class GetOrderResponseModel implements Serializable {
    public int orderId;
    public String orderDate;
    public boolean isDiningIn;
    public String orderStatusTitle;
    public double billingAmount;
    public boolean isCardPayment;
    public String firstName;
    public String lastName;
    public String tableId;
    public int statusId;
    public boolean isPaid;
    public ArrayList<MenuItems> menuItems;
}

