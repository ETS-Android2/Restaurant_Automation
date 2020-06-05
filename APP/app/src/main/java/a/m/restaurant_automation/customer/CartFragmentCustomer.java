package a.m.restaurant_automation.customer;

import android.os.Bundle;

import a.m.restaurant_automation.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class CartFragmentCustomer extends Fragment {


    TextView textView_itemName_cart,textView_itemPrice_cart;
    Button addItem, subtractItem, RemoveItem;


    public CartFragmentCustomer() {
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
        return inflater.inflate(R.layout.fragment_cart_customer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView_itemName_cart = view.findViewById(R.id.textView_itemName_cart);
        textView_itemPrice_cart = view.findViewById(R.id.textView_itemPrice_cart);

    }
}
