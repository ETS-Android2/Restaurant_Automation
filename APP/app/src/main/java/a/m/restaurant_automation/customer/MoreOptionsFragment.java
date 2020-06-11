package a.m.restaurant_automation.customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import a.m.restaurant_automation.LoginActivity;
import a.m.restaurant_automation.R;
import a.m.restaurant_automation.repository.UserSession;


public class MoreOptionsFragment extends Fragment implements View.OnClickListener {

    Button logoutCustomer;
    TextView editProfile;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.BottomnavigateMenuCustomer);
        bottomNavigationView.setVisibility(View.VISIBLE);
        logoutCustomer=view.findViewById(R.id.logoutCustomer);
        logoutCustomer.setOnClickListener(this);
        editProfile=view.findViewById(R.id.EditProfile);

        editProfile.setOnClickListener(this);

//        logoutCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
//                        .setTitle("Logout")
//                        .setMessage("Are you sure you want to logout?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                UserSession.getInstance().clearSession();
//                                Intent _intent = new Intent(getActivity(), LoginActivity.class);
//                                startActivity(_intent);
//                                getActivity().finish();
//
//
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                        .create();
//                alertDialog.show();

            }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.logoutCustomer)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UserSession.getInstance().clearSession();
                            Intent _intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(_intent);
                            getActivity().finish();



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
        else if(id == R.id.EditProfile)
        {
            Navigation.findNavController(v).navigate(R.id.editProfileFragment);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_options, container, false);
    }
}
