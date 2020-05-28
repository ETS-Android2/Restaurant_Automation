package a.m.restaurant_automation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import a.m.restaurant_automation.customer.CustomerOverviewFragment;
import a.m.restaurant_automation.customer.MenuItemsFragment;
import a.m.restaurant_automation.customer.MoreOptionsFragment;
import a.m.restaurant_automation.customer.OrderFragment;
import a.m.restaurant_automation.manager.AddMenuItemFragment;
import a.m.restaurant_automation.manager.ManagerDashboardFragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class ManagerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public BottomNavigationView bottomNavigationView;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);


        setUpNavigation();


    }
    public void setUpNavigation(){

        bottomNavigationView= findViewById(R.id.BottomnavigateMenuManager);
        navController= Navigation.findNavController(this,R.id.managerHostFragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

         NavigationUI.setupActionBarWithNavController(this,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment fragment=null;
      menuItem.setCheckable(true);

        int id = menuItem.getItemId();

        switch (id){
            case R.id.Dashboard:
                navController.navigate(R.id.managerDashboardFragment);
                return true;

            case R.id.OrderHistory:
                navController.navigate(R.id.managerOrderHistoryFragment);
                return true;


            case R.id.Moremenu:
//                fragment=new OrderFragment();
//                break;

        }
        return false;
    }
}
