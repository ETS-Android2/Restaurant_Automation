package a.m.restaurant_automation.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerMenuItemsFragment extends Fragment {

    int category;
    CustomerMenuItemAdapter menuItemAdaptercustomer;
    TextView emptyText;
    View viewMenu;

    ViewPager mViewPager;
    TabLayout mTabLayout;
    TabItem appetizerItem, mainCourseItem, beverageItem, dessertItem;
    PagerAdapter pagerAdapter;


    ArrayList<MenuItemResponse> menuItemResponsecustomer;
    private View.OnClickListener onClickListener;

    public CustomerMenuItemsFragment() {
    }

    public CustomerMenuItemsFragment(int category) {
        this.category = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_menu_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //emptyText = view.findViewById(R.id.textView_empty);


        viewMenu = view;
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        Call<ResponseModel<ArrayList<MenuItemResponse>>> call = dataService.getMenuItem(category);


        call.enqueue(new Callback<ResponseModel<ArrayList<MenuItemResponse>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Response<ResponseModel<ArrayList<MenuItemResponse>>> response) {
                ResponseModel<ArrayList<MenuItemResponse>> responseModel = response.body();

                if (responseModel != null && responseModel.getError() != null) {
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                } else if (responseModel != null && responseModel.getData() != null) {
                    menuItemResponsecustomer = responseModel.getData();
                   // if (menuItemResponsecustomer == null || menuItemResponsecustomer.size() == 0) {
                   //     emptyText.setVisibility(View.VISIBLE);
                   // } else {
                   //     emptyText.setVisibility(View.GONE);
                    //}
                    generateRecyclerView(menuItemResponsecustomer, viewMenu);

                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        mViewPager = view.findViewById(R.id.viewpagercustomer);
        mTabLayout = view.findViewById(R.id.tablayoutcustomer);

        appetizerItem = view.findViewById(R.id.appetizerItemcustomer);
        mainCourseItem = view.findViewById(R.id.mainCourseItemcustomer);
        beverageItem = view.findViewById(R.id.beverageItemcustomer);
        dessertItem = view.findViewById(R.id.dessertItemcustomer);



        pagerAdapter = new PagerAdapter(getChildFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    public void generateRecyclerView(ArrayList<MenuItemResponse> menuItemResponse, View viewMenu) {
        menuItemAdaptercustomer = new CustomerMenuItemAdapter(menuItemResponse, getActivity().getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = viewMenu.findViewById(R.id.recyclerView_customer_menuItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuItemAdaptercustomer);
       // menuItemAdaptercustomer.setOnItemClickListener(onClickListener);
        menuItemAdaptercustomer.notifyDataSetChanged();
    }


    private class PagerAdapter extends FragmentPagerAdapter {
        private int tabNumber;

        public PagerAdapter(@NonNull FragmentManager fm, int behavior,int mTabs) {
            super(fm, behavior);
            this.tabNumber = mTabs;
        }

        public PagerAdapter(FragmentManager childFragmentManager, int tabCount) {
            super(childFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
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
