package a.m.restaurant_automation.customer;

import android.os.Bundle;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.customer.CustomerMenuItemAdapter;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CustomerCatergoryItemNavigate extends Fragment {

    int category;
    CustomerMenuItemAdapter menuItemAdaptercustomer;
    View viewMenu;
    ArrayList<MenuItemResponse> menuItemResponsecustomer;
    Call<ResponseModel<ArrayList<MenuItemResponse>>> call;
    BottomNavigationView bottomNavigationView;

    public CustomerCatergoryItemNavigate(int category) {
        this.category = category;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_catergory_item_navigate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewMenu = view;
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        call = dataService.getMenuItem(category);
        call.enqueue(new Callback<ResponseModel<ArrayList<MenuItemResponse>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Response<ResponseModel<ArrayList<MenuItemResponse>>> response) {
                ResponseModel<ArrayList<MenuItemResponse>> responseModel = response.body();

                if (responseModel != null && responseModel.getError() != null) {
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                } else if (responseModel != null && responseModel.getData() != null) {
                    menuItemResponsecustomer = responseModel.getData();

                    generateRecyclerView(menuItemResponsecustomer, viewMenu);

                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void generateRecyclerView(ArrayList<MenuItemResponse> menuItemResponse, View viewMenu) {
        menuItemAdaptercustomer = new CustomerMenuItemAdapter(menuItemResponse, getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = viewMenu.findViewById(R.id.recyclerView_customer_menuItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuItemAdaptercustomer);
        // menuItemAdaptercustomer.setOnItemClickListener(onClickListener);
        // menuItemAdaptercustomer.notifyDataSetChanged();
    }
}
