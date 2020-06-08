package a.m.restaurant_automation.chef;

import android.content.Intent;
import android.os.Bundle;

import a.m.restaurant_automation.LoginActivity;
import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.responseModel.GetOrderResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class ChefDashboard extends Fragment {
    RecyclerView recyclerView;
    Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call;
    Set<Integer> ordersSet;
    ChefDashboardAdapter dashboardAdapter;
    LinearLayoutManager layoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chef_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewChefDashboard);
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        call = dataService.getOrders(0, "0", "0", "0");
        call.enqueue(new Callback<ResponseModel<ArrayList<GetOrderResponseModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call, Response<ResponseModel<ArrayList<GetOrderResponseModel>>> response) {
                ResponseModel<ArrayList<GetOrderResponseModel>> orders = response.body();
                if(orders != null && orders.getData() != null){
                    ArrayList<GetOrderResponseModel> ordersArrayList = orders.getData();
                    if(ordersSet != null){

                    }else{
                        dashboardAdapter = new ChefDashboardAdapter(ordersArrayList);
                        recyclerView.setAdapter(dashboardAdapter);
                        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), ""+orders.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
