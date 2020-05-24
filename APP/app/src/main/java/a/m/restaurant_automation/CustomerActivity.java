package a.m.restaurant_automation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public BottomNavigationView bottomNavigationView;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        setUpNavigation();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    public void setUpNavigation(){

        bottomNavigationView= findViewById(R.id.BottomnavigateMenu);
        bottomNavigationView.setVisibility(View.VISIBLE);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());


        navController= Navigation.findNavController(CustomerActivity.this,R.id.hostfragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        // NavigationUI.setupActionBarWithNavController(this,navController);
       // NavigationUI.setupWithNavController(bottomNavigationView,navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);

        int id = menuItem.getItemId();

        switch (id){
            case R.id.Dashboard:
                // Toast.makeText(getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.CustomerOverviewFragment);

                return true;

            case R.id.Menu:
                //  Toast.makeText(getApplicationContext(),"Friend Dashboard",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.MenuItemsFragment);

                return true;

            case R.id.Orders:
                // Toast.makeText(getApplicationContext(),"Account",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.OrdersFragment);

                return true;

            case R.id.Moremenu:
                // Toast.makeText(getApplicationContext(),"Account",Toast.LENGTH_LONG).show();
               navController.navigate(R.id.moreOptionsFragment);

                return true;
        }
        return false;
    }

    private class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView>{
        private int height;

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
            height = child.getHeight();
            return super.onLayoutChild(parent, child, layoutDirection);
        }

        @Override
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                           BottomNavigationView child, @NonNull
                                                   View directTargetChild, @NonNull View target,
                                           int axes, int type)
        {
            return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

        @Override
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                                   @NonNull View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed,
                                   @ViewCompat.NestedScrollType int type)
        {
            if (dyConsumed > 0) {
                slideDown(child);
            } else if (dyConsumed < 0) {
                slideUp(child);
            }
        }

        private void slideUp(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(0).setDuration(100);
        }

        private void slideDown(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(height).setDuration(100);
        }
    }
}
