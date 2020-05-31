package a.m.restaurant_automation.chef;

import android.content.Intent;
import android.os.Bundle;

import a.m.restaurant_automation.LoginActivity;
import a.m.restaurant_automation.R;
import a.m.restaurant_automation.repository.UserSession;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ChefDashboard extends Fragment {
    Button logoutChef;



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
        logoutChef=view.findViewById(R.id.logout_chef);
        logoutChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession.getInstance().clearSession();
                Intent _intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(_intent);
                getActivity().finish();
            }
        });

    }

}
