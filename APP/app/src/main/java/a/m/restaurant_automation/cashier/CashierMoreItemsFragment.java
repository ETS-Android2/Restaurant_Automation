package a.m.restaurant_automation.cashier;

import android.os.Bundle;

import a.m.restaurant_automation.R;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class CashierMoreItemsFragment extends Fragment {

    public CashierMoreItemsFragment() {
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
        return inflater.inflate(R.layout.fragment_cashier_more_items, container, false);
    }
}
