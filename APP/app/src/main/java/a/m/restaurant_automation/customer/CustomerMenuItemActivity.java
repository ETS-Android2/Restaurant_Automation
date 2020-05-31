package a.m.restaurant_automation.customer;

import android.content.Intent;
import android.os.Bundle;

import a.m.restaurant_automation.CustomerActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import a.m.restaurant_automation.R;

public class CustomerMenuItemActivity extends AppCompatActivity {

    ViewPager mViewPagercustomer;
    TabLayout mTabLayoutcustomer;
    TabItem appetizerItem, mainCourseItem, beverageItem, dessertItem;
    PagerAdapter pagerAdaptercustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu_item);


        mViewPagercustomer = findViewById(R.id.viewpagercustomer);
        mTabLayoutcustomer = findViewById(R.id.tablayoutcustomer);

        appetizerItem = findViewById(R.id.appetizerItemcustomer);
        mainCourseItem = findViewById(R.id.mainCourseItemcustomer);
        beverageItem = findViewById(R.id.beverageItemcustomer);
        dessertItem = findViewById(R.id.dessertItemcustomer);

        pagerAdaptercustomer = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayoutcustomer.getTabCount());
        mViewPagercustomer.setAdapter(pagerAdaptercustomer);


        mTabLayoutcustomer.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPagercustomer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPagercustomer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutcustomer));
    }

    @Override
    public void onBackPressed() {
        finish();


        //super.onBackPressed();
        Intent intent = new Intent(CustomerMenuItemActivity.this, CustomerActivity.class);
        startActivity(intent);

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private int tabNumber;

        public PagerAdapter(@NonNull FragmentManager fm, int behavior, int mTabs) {
            super(fm, behavior);
            this.tabNumber = mTabs;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CustomerMenuItemsFragment(1);
                case 1:
                    return new CustomerMenuItemsFragment(2);
                case 2:
                    return new CustomerMenuItemsFragment(3);
                case 3:
                    return new CustomerMenuItemsFragment(4);

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabNumber;
        }
    }
}
