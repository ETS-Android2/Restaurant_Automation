package a.m.restaurant_automation.customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerCatergoryItemNavigate extends Fragment  {
    TextView  menuItemCapacity;
    Button addItemButton, plusItem , subItem;
    int category;
    int qty=1;
    CustomerMenuItemAdapter menuItemAdaptercustomer;
    View viewMenu;
    ArrayList<MenuItemResponse> menuItemResponsecustomer;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onaddToCartPress= (OnaddToCartPress) context;
    }

    Call<ResponseModel<ArrayList<MenuItemResponse>>> call;
    BottomNavigationView bottomNavigationView;

    int capacity ;

    UserSession session;
    OnaddToCartPress onaddToCartPress;


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
        menuItemCapacity=view.findViewById(R.id.textviewcapacity);
        plusItem = view.findViewById(R.id.buttonPlusCapacityItem);
        subItem= view.findViewById(R.id.buttonMinusCapacityItem);
        addItemButton = view.findViewById(R.id.customer_addItemButton);



        final IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
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

      /* plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItemCapacity.setText((Integer.parseInt(menuItemCapacity.getText().toString())+1));

            }
        });
        subItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(menuItemCapacity.getText().toString()) > 1)
                    menuItemCapacity.setText((Integer.parseInt(menuItemCapacity.getText().toString()) - 1));
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Items Should be atleast one", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void generateRecyclerView(ArrayList<MenuItemResponse> menuItemResponse, View viewMenu) {
        menuItemAdaptercustomer = new CustomerMenuItemAdapter(menuItemResponse, getActivity().getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = viewMenu.findViewById(R.id.recyclerView_customer_menuItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(menuItemAdaptercustomer);
        menuItemAdaptercustomer.setOnItemClickListener(onClickListener);
        menuItemAdaptercustomer.notifyDataSetChanged();
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id==R.id.buttonPlusCapacityItem)
            {
                menuItemCapacity.setText(""+Integer.parseInt(menuItemCapacity.getText().toString())+1);

            }
            else if(id==R.id.buttonMinusCapacityItem)
            {
                if (Integer.parseInt(menuItemCapacity.getText().toString()) > 1)
                    menuItemCapacity.setText(""+(Integer.parseInt(menuItemCapacity.getText().toString()) - 1));
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Items Should be atleast one", Toast.LENGTH_SHORT).show();

            }

            if(id == R.id.customer_addItemButton){

                //capacity = Integer.parseInt(menuItemCapacity.getText().toString());
                //menuItemCapacity=(int) v.getTag(1);

                int itemid =(int) v.getTag();
                int quantity = (int) v.getTag();/* Integer.parseInt(menuItemCapacity.getText().toString());*/
                int addedby = Integer.parseInt(session.getInstance().getUserId());

                onaddToCartPress.onaddToCartPress(itemid,quantity,addedby);
            }

        }
    };



    public interface OnaddToCartPress {
        void onaddToCartPress (int itemId,int quantity, int addedby);
    }
}
