package a.m.restaurant_automation.customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.BoringLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import a.m.restaurant_automation.manager.EmployeeTableFragment;
import a.m.restaurant_automation.requestModel.ChangePasswordRequestModel;
import a.m.restaurant_automation.responseModel.ErrorModel;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.StatusCheckResponse;
import a.m.restaurant_automation.responseModel.TableResponseModel;
import a.m.restaurant_automation.responseModel.UsersResponseModel;
import a.m.restaurant_automation.service.IUserService;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import a.m.restaurant_automation.LoginActivity;
import a.m.restaurant_automation.R;
import a.m.restaurant_automation.RetrofitClient;
import a.m.restaurant_automation.repository.UserSession;
import a.m.restaurant_automation.responseModel.CustomerNotificationResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.INotificationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoreOptionsFragment extends Fragment implements View.OnClickListener {

    Button logoutCustomer;
    CardView editProfile,changePassword;
    AlertDialog.Builder alertDialog;
    OnChangePasswordPress onChangePasswordPress;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.BottomnavigateMenuCustomer);
        bottomNavigationView.setVisibility(View.VISIBLE);
        logoutCustomer=view.findViewById(R.id.logoutCustomer);
        logoutCustomer.setOnClickListener(this);
        editProfile=view.findViewById(R.id.EditProfile);
        changePassword = view.findViewById(R.id.changePassword);


        view.getContext().stopService(new Intent(view.getContext(), CustomerNotificationService.class));
        editProfile.setOnClickListener(this);
        changePassword.setOnClickListener(this);

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
        else if (id == R.id.changePassword){
            alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Change Password")
                    .setMessage("Do you want to change your password?");
            final EditText editText_changePassword = new EditText(getActivity().getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            editText_changePassword.setLayoutParams(layoutParams);
            editText_changePassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            if (TextUtils.isEmpty(editText_changePassword.getText().toString())){
                editText_changePassword.setError("Password Cannot be Empty!");
                editText_changePassword.requestFocus();
            }
            else if (editText_changePassword.getText().length()<6){
                editText_changePassword.setError("Password Cannot be less than 6 Characters.");
                editText_changePassword.requestFocus();
            }
            alertDialog.setView(editText_changePassword)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String newPassword = editText_changePassword.getText().toString();
                            UserSession session = UserSession.getInstance();
                            int userId = Integer.parseInt(session.getUserId());
                            onChangePasswordPress.OnChangePasswordPress(newPassword,userId);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(R.drawable.ic_vpn_key_black_24dp)
                    .create();
            alertDialog.show();

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onChangePasswordPress= (MoreOptionsFragment.OnChangePasswordPress) context;
    }

    public interface OnChangePasswordPress {
        void  OnChangePasswordPress(String password, int userId);
    }


}
