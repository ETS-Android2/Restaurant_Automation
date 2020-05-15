package a.m.restaurant_automation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Button singInButton, createAccount;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        singInButton =view.findViewById(R.id.signInButton);
        createAccount = view.findViewById(R.id.createAccount);


    }

    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.signInButton)
        {
            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.loginFragment);
        }
        else {
            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.registerFragment);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
