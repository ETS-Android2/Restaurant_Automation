package a.m.restaurant_automation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import a.m.restaurant_automation.customer.CartFragmentCustomer;
import a.m.restaurant_automation.customer.CustomerMenuItemAdapter;
import a.m.restaurant_automation.responseModel.StatusCheckResponse;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import a.m.restaurant_automation.customer.CustomerCatergoryItemNavigate;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.requestModel.AddToCartRequestModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CustomerCatergoryItemNavigate.OnaddToCartPress, CartFragmentCustomer.OnAddItemCartPress {

    public BottomNavigationView bottomNavigationView;
    public NavController navController;
    UserSession session;
    private int Itemid , Quantity , AddedBy;
    private int cartIdCart,cartQuantity;
    private boolean cartIsDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setUpNavigation();
    }

    public void setUpNavigation(){

        bottomNavigationView= findViewById(R.id.BottomnavigateMenuCustomer);
        navController= Navigation.findNavController(this,R.id.customerhostfragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        NavigationUI.setupActionBarWithNavController(this,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }



    @Override
    public void onBackPressed() { super.onBackPressed(); }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);

        int id = menuItem.getItemId();

        switch (id){
            case R.id.tables:
                // Toast.makeText(getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.customerTableViewFragment);
               // fragment=new CustomerOverviewFragment();
               // break;
                return true;

            case R.id.menu:
                //  Toast.makeText(getApplicationContext(),"Friend Dashboard",Toast.LENGTH_LONG).show();
               //navController.navigate(R.id.);
                navController.navigate(R.id.customerMenuItemsFragment);
               return true;

             //   fragment=new CustomerMenuItemsFragment();
               // break;
            case R.id.cart:
                // Toast.makeText(getApplicationContext(),"Account",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.orderFragment);
                return true;

               // fragment=new OrderFragment();
                //break;

            case R.id.moreMenu:
                // Toast.makeText(getApplicationContext(),"Account",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.moreOptionsFragment);
                return true;

               // fragment=new MoreOptionsFragment();
                //break;
        }
        return false;
    }

    public void addToCart(){
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        AddToCartRequestModel addToCartRequestModel =  new AddToCartRequestModel();
        addToCartRequestModel.itemId = Itemid ;
        addToCartRequestModel.quantity = Quantity;
        addToCartRequestModel.addedby = AddedBy;

        Call<ResponseModel<StatusCheckResponse>> call = dataService.addToCart(addToCartRequestModel);
        call.enqueue(new Callback<ResponseModel<StatusCheckResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<StatusCheckResponse>> call, Response<ResponseModel<StatusCheckResponse>> response) {
                ResponseModel<StatusCheckResponse> responseModel = response.body();
                if (responseModel != null) {
                    if (responseModel.getError() != null) {
                        Toast.makeText(getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),responseModel.getData().statusCode + " Added to cart", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<StatusCheckResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "something went wrong" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void deleteOrModifyCart()
    {
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);

        Call<ResponseModel<StatusCheckResponse>> call = dataService.deleteOrModifyCartItems(cartIdCart,cartQuantity,cartIsDelete);
        call.enqueue(new Callback<ResponseModel<StatusCheckResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<StatusCheckResponse>> call, Response<ResponseModel<StatusCheckResponse>> response) {
                ResponseModel<StatusCheckResponse> responseModel = response.body();
                if (responseModel != null) {
                    if (responseModel.getError() != null) {
                        Toast.makeText(getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),responseModel.getData().statusMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<StatusCheckResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "something went wrong" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onaddToCartPress(int itemId, int quantity, int addedby) {
         Itemid = itemId;
         Quantity = quantity;
         AddedBy = addedby;
        addToCart();
    }

    @Override
    public void onAddItemCartPress(int cartId, int quantity, boolean isDelete) {
        cartIdCart=cartId;
        cartQuantity=quantity;
        cartIsDelete=isDelete;
        deleteOrModifyCart();

    }
}
