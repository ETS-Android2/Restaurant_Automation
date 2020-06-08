package a.m.restaurant_automation.chef;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
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

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class ChefDashboard extends Fragment {
    static RecyclerView recyclerView;
    static Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call;
    static Set<Integer> ordersSet;
    static ChefDashboardAdapter dashboardAdapter;
    static LinearLayoutManager layoutManager;
    static Context context;
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

        context = getActivity().getApplicationContext();
        final Handler handler = new Handler();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
                        call = dataService.getOrders(0, "0", "0", "0");
                        fetchOrders();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timertask, 0, 10000);
    }

    public static void fetchOrders() {
        call.enqueue(new Callback<ResponseModel<ArrayList<GetOrderResponseModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call, Response<ResponseModel<ArrayList<GetOrderResponseModel>>> response) {
                ResponseModel<ArrayList<GetOrderResponseModel>> orders = response.body();
                if(orders != null && orders.getData() != null){
                    ArrayList<GetOrderResponseModel> ordersArrayList = orders.getData();
                    if(ordersSet != null){
                        Set<Integer> tempSet = new HashSet<>();
                        HashMap<Integer, GetOrderResponseModel> hashMap = new HashMap<>();
                        for(int i = 0; i < ordersArrayList.size(); i++){
                            tempSet.add(ordersArrayList.get(i).orderId);
                            hashMap.put(ordersArrayList.get(i).orderId, ordersArrayList.get(i));
                        }
                        tempSet.removeAll(ordersSet);
                        for (int x : tempSet) {
                            dashboardAdapter.orders.add(hashMap.get(x));
                            ordersSet.add(x);
                        }
                        if(!tempSet.isEmpty()){
                            Uri alarmSound =
                                    RingtoneManager. getDefaultUri (RingtoneManager. TYPE_NOTIFICATION );
                            MediaPlayer mp = MediaPlayer. create (context, alarmSound);
                            mp.start();
                            dashboardAdapter.notifyDataSetChanged();
                        }
                    }else{
                        dashboardAdapter = new ChefDashboardAdapter(ordersArrayList);
                        recyclerView.setAdapter(dashboardAdapter);
                        layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);
                        ordersSet = new HashSet<>();
                        for(int i = 0; i < ordersArrayList.size(); i++){
                            ordersSet.add(ordersArrayList.get(i).orderId);
                        }
                    }
                }else{
                    Toast.makeText(context, ""+orders.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<GetOrderResponseModel>>> call, Throwable t) {
                Toast.makeText(context, "Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
