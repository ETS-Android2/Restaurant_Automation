package a.m.restaurant_automation.manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.manager.MenuItemAdapter;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MenuItemsFragment extends Fragment {

    int category;
    MenuItemAdapter menuItemAdapter;
    TextView emptyText;
    View viewMenu;
    Button removeItemButton;

    ArrayList<MenuItemResponse> menuItemResponse;

    public MenuItemsFragment(int category) {
        // Required empty public constructor
        this.category = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyText = view.findViewById(R.id.textView_empty);


        viewMenu = view;
        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        Call<ResponseModel<ArrayList<MenuItemResponse>>> call =dataService.getMenuItem(category);

        call.enqueue(new Callback<ResponseModel<ArrayList<MenuItemResponse>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Response<ResponseModel<ArrayList<MenuItemResponse>>> response) {
                ResponseModel<ArrayList<MenuItemResponse>> responseModel =response.body();

                if (responseModel != null && responseModel.getError() != null){
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
                else if (responseModel!= null && responseModel.getData() != null){
                    menuItemResponse =responseModel.getData();
                    if (menuItemResponse == null || menuItemResponse.size() == 0){
                        emptyText.setVisibility(View.VISIBLE);
                    }else {
                        emptyText.setVisibility(View.GONE);
                    }
                    generateRecyclerView(menuItemResponse,viewMenu);

                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MenuItemResponse>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    public void generateRecyclerView(ArrayList<MenuItemResponse> menuItemResponse, View viewMenu) {
        menuItemAdapter =new MenuItemAdapter(menuItemResponse,getActivity().getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView =viewMenu.findViewById(R.id.recyclerView_menuItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuItemAdapter);
        menuItemAdapter.setOnItemClickListener(onClickListener);
        menuItemAdapter.notifyDataSetChanged();
    }

    public View.OnClickListener onClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete this Item?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity().getApplicationContext(), "yes selected", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            alertDialog.show();
        }
    };



}
