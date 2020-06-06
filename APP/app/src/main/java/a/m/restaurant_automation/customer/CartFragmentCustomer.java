package a.m.restaurant_automation.customer;

import android.content.Context;
import android.os.Bundle;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.manager.MenuItemAdapter;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.requestModel.AddToCartRequestModel;
import a.m.restaurant_automation.requestModel.DeleteOrModifyCart;
import a.m.restaurant_automation.responseModel.CustomerReserveTableResponse;
import a.m.restaurant_automation.responseModel.GetCartItemResponseModel;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CartFragmentCustomer extends Fragment {


    TextView textView_itemName_cart,textView_itemPrice_cart,emptyTextCart,textView_quantity_cart;
    Button addCartItem, subtractCartItem, RemoveItem;
    int userId;
    View viewCartItems;
    OnAddItemCartPress onAddItemCartPress;

    CustomerMenuItemAdapter customerMenuItemAdapter;
    ArrayList<GetCartItemResponseModel> getCartItemResponseModels;


    public CartFragmentCustomer() {
        // Required empty public constructor
    }
//    public CartFragmentCustomer(int userId) {
//        this.userId = userId;
//        //  public constructor to get the parameter
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_customer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.BottomnavigateMenuCustomer);
        bottomNavigationView.setVisibility(View.GONE);
        emptyTextCart = view.findViewById(R.id.emptyTextCart);
        textView_itemName_cart = view.findViewById(R.id.textView_itemName_cart);
        textView_itemPrice_cart = view.findViewById(R.id.textView_itemPrice_cart);
        addCartItem=view.findViewById(R.id.buttonAddQuantity);
        subtractCartItem=view.findViewById(R.id.buttonSubtractQuantity);
        textView_quantity_cart=view.findViewById(R.id.textviewQuantityCart);
        viewCartItems = view;
        UserSession session =UserSession.getInstance();
        String userId = session.getUserId();

        IDataService dataService = RetrofitClient.getRetrofitInstance().create(IDataService.class);
        Call<ResponseModel<ArrayList<GetCartItemResponseModel>>> call =dataService.getCartItems(userId);
        call.enqueue(new Callback<ResponseModel<ArrayList<GetCartItemResponseModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<GetCartItemResponseModel>>> call, Response<ResponseModel<ArrayList<GetCartItemResponseModel>>> response) {
                ResponseModel<ArrayList<GetCartItemResponseModel>> responseModel =response.body();

                if (responseModel != null && responseModel.getError() != null){
                    Toast.makeText(getActivity().getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
                else if (responseModel!= null && responseModel.getData() != null){
                    getCartItemResponseModels =responseModel.getData();
                    if (getCartItemResponseModels == null || getCartItemResponseModels.size() == 0){
                        emptyTextCart.setVisibility(View.VISIBLE);
                    }else {
                        emptyTextCart.setVisibility(View.GONE);
                        generateRecyclerView(getCartItemResponseModels,viewCartItems);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<GetCartItemResponseModel>>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong!" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }

    private void generateRecyclerView(ArrayList<GetCartItemResponseModel> getCartItemResponseModels, View viewCartItems) {
        customerMenuItemAdapter =new CustomerMenuItemAdapter(getCartItemResponseModels,getActivity().getApplication(),0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView =viewCartItems.findViewById(R.id.recyclerView_cart);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customerMenuItemAdapter);
        customerMenuItemAdapter.setOnItemClickListener(onClickListener);
        customerMenuItemAdapter.notifyDataSetChanged();
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.buttonAddQuantity) {
                DeleteOrModifyCart tag = (DeleteOrModifyCart) v.getTag();

                boolean isDelete =false;

                //int addedby = Integer.parseInt(session.getInstance().getUserId());

               onAddItemCartPress.onAddItemCartPress(tag.cartId,tag.quantity,isDelete);
            }

        }
    };

    public interface OnAddItemCartPress {
        void onAddItemCartPress (int cartId,int quantity,boolean isDelete);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       onAddItemCartPress= (OnAddItemCartPress) context;
    }
}
