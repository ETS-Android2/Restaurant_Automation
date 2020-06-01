package a.m.restaurant_automation.customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import a.m.restaurant_automation.R;


public class CustomerOverviewFragment extends Fragment implements View.OnClickListener {
    Button buttonDineIn, buttonTakeOut;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.BottomnavigateMenuCustomer);
        bottomNavigationView.setVisibility(View.GONE);
        buttonDineIn = view.findViewById(R.id.btnDineIn);
        buttonTakeOut = view.findViewById(R.id.btnTakeOut);
        buttonDineIn.setOnClickListener(this);
        buttonTakeOut.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_overview, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public interface OnDineInPress {
        void OnDineInPress();
    }

    public interface OnTakeOutPress {
        void OnTakeOutPress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDineIn:
                Navigation.findNavController(view).navigate(R.id.customerTableViewFragment);
                break;
            case R.id.btnTakeOut:
                Navigation.findNavController(view).navigate(R.id.customerMenuItemsFragment);
                break;
        }
    }
}
