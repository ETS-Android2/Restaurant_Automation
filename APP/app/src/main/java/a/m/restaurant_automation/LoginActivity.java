package a.m.restaurant_automation;

import a.m.restaurant_automation.model.AppStaticData;
import a.m.restaurant_automation.requestModel.RegisterRequestModel;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.service.IUserService;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements RegisterFragment.OnRegisterPress{

    private int UserType = AppStaticData.USERTYPE_CUSTOMER;
    private String registerEmail, registerPassword, registerFirstName, registerLastName;


    public void OnUserTypeSet(int userType) {
        UserType = userType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void OnRegisterPress(String firstName,String lastName, String email, String password, int userType) {

        registerEmail = email;
        registerFirstName = firstName;
        registerLastName = lastName;
        registerPassword = password;
        UserType = userType;

        Register();


    }

    public void Register() {
        IUserService userService =RetrofitClient.getRetrofitInstance().create(IUserService.class);
        RegisterRequestModel registerRequestModel = new RegisterRequestModel();
        registerRequestModel.FirstName = registerFirstName;
        registerRequestModel.lastName =  registerLastName;
        registerRequestModel.Email = registerEmail;
        registerRequestModel.Password = registerPassword;
        registerRequestModel.UserType = UserType;

        Call<ResponseModel<RegisterResponseModel>> call = userService.register(registerRequestModel);

        call.enqueue(new Callback<ResponseModel<RegisterResponseModel>>() {
            @Override
            public void onResponse(Call<ResponseModel<RegisterResponseModel>> call, Response<ResponseModel<RegisterResponseModel>> response) {
                ResponseModel<RegisterResponseModel> responseModel= response.body();

                if (responseModel!= null){
                    if (responseModel.getError() != null){
                        Toast.makeText(getApplicationContext(),responseModel.getError().getErrorMessage(),Toast.LENGTH_LONG).show();
                    }
                    else if(responseModel.getData()!= null){
                        RegisterResponseModel registerRequestModel =responseModel.getData();
                        Toast.makeText(getApplicationContext(), responseModel.getData().getEmail() + " registered", Toast.LENGTH_LONG).show();
                        //login(registerRequestModel.getEmail(), loginPassword);
                    }
                    else{
                        Intent gotoMainActivity =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(gotoMainActivity);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<RegisterResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"something went wrong"+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}
