package a.m.restaurant_automation.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.responseModel.UsersResponseModel;

public class EditProfileFragment extends Fragment {
    TextView user,email,firstname,lastname;
    UserSession session;
    String username,userEmail,userfirstname,userlastname;
    UsersResponseModel usersResponseModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user= view.findViewById(R.id.textview_user);
        username = session.getInstance().getName();
        user.setText(username);

        email = view.findViewById(R.id.emailIdUser);
        userEmail=session.getInstance().getEmail();
        email.setText(userEmail);

        firstname= view.findViewById(R.id.fisrtnameuser);
       // userfirstname=usersResponseModel.getFirstName();
        firstname.setText(userfirstname);

        //userlastname=usersResponseModel.getLastName();
        lastname=view.findViewById(R.id.lastnameuser);
        lastname.setText(userlastname);

    }

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }
}
