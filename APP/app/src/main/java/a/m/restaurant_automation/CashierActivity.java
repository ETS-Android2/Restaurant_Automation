package a.m.restaurant_automation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class CashierActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public BottomNavigationView bottomNavigationView;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);
        setUpNavigation();


    }

    public void setUpNavigation(){

        bottomNavigationView= findViewById(R.id.BottomnavigateMenuCashier);
        navController= Navigation.findNavController(this,R.id.cashierHostFragment);
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
                navController.navigate(R.id.cashierDashboardFragment);
                return true;

            case R.id.OrderHistory:
//                navController.navigate(R.id.managerOrderHistoryFragment);
//                return true;


            case R.id.Moremenu:
//                fragment=new OrderFragment();
//                break;

        }
        return false;
    }


}
