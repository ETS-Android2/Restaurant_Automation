package a.m.restaurant_automation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import a.m.restaurant_automation.repository.UserSession;

public class CustomerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public BottomNavigationView bottomNavigationView;
    public NavController navController;
    UserSession session;

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
}
