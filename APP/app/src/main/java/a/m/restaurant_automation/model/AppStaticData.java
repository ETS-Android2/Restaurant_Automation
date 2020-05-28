package a.m.restaurant_automation.model;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppStaticData {
    public static final int USERTYPE_CUSTOMER = 1;
    public static final int USERTYPE_MANAGER = 2;
    public static final int USERTYPE_CHEF = 3;
    public static final int USERTYPE_CASHIER = 4;


    public static final HashMap<String, Integer> categories = new HashMap<String, Integer>(){{
        put("Appetizer", 1);
        put("Main Course",2);
        put("Beverage",3);
        put("Dessert",4);
    }};

    public static final HashMap<String, Integer> employeeType = new HashMap<String, Integer>(){{
        put("Manager", 2);
        put("Chef",3);
        put("Cashier",4);

    }};


    public static final String BASE_URL= "http://restroapi-dev.us-west-2.elasticbeanstalk.com/api/";

}
