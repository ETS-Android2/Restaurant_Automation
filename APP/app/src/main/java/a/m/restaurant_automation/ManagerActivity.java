package a.m.restaurant_automation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import a.m.restaurant_automation.manager.AddEmployeeFragment;
import a.m.restaurant_automation.manager.EmployeeTableFragment;
import a.m.restaurant_automation.requestModel.AddTableRequestModel;
import a.m.restaurant_automation.requestModel.RegisterRequestModel;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.responseModel.TableResponseModel;
import a.m.restaurant_automation.service.IUserService;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,AddEmployeeFragment.OnEmployeeRegisterPress, EmployeeTableFragment.OnTableAddPress {
    public BottomNavigationView bottomNavigationView;
    public NavController navController;

    private String registerEmail, registerPassword, registerFirstName, registerLastName;
    private int UserType;

    private int addCapacity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);


        setUpNavigation();


    }
    public void setUpNavigation(){

        bottomNavigationView= findViewById(R.id.BottomnavigateMenuManager);
        navController= Navigation.findNavController(this,R.id.managerHostFragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        NavigationUI.setupActionBarWithNavController(this,navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
      menuItem.setCheckable(true);

        int id = menuItem.getItemId();

        switch (id){
            case R.id.Dashboard:
                navController.navigate(R.id.managerDashboardFragment);
                return true;

            case R.id.OrderHistory:
                navController.navigate(R.id.managerOrderHistoryFragment);
                return true;


            case R.id.Moremenu:
                navController.navigate(R.id.managerMoreOptionsFragment);
                return true;

        }
        return false;
    }
    public void OnTableAddPress( int capacity) {
        addCapacity = capacity;
        AddTable();
    }

    public void OnEmployeeRegisterPress(String firstName, String lastName, String email, String password, int userType) {

        registerEmail = email;
        registerFirstName = firstName;
        registerLastName = lastName;
        registerPassword = password;
        UserType = userType;
        EmployeeRegister();
    }

    public void EmployeeRegister() {
        IUserService userService = RetrofitClient.getRetrofitInstance().create(IUserService.class);
        RegisterRequestModel registerRequestModel = new RegisterRequestModel();
        registerRequestModel.FirstName = registerFirstName;
        registerRequestModel.lastName = registerLastName;
        registerRequestModel.Email = registerEmail;
        registerRequestModel.Password = registerPassword;
        registerRequestModel.UserType = UserType;

        Call<ResponseModel<RegisterResponseModel>> call = userService.register(registerRequestModel);

        call.enqueue(new Callback<ResponseModel<RegisterResponseModel>>() {
            @Override
            public void onResponse(Call<ResponseModel<RegisterResponseModel>> call, Response<ResponseModel<RegisterResponseModel>> response) {
                ResponseModel<RegisterResponseModel> responseModel = response.body();

                if (responseModel != null) {
                    if (responseModel.getError() != null) {
                        Toast.makeText(getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        //RegisterResponseModel registerRequestModel = responseModel.getData();
                        Toast.makeText(getApplicationContext(), responseModel.getData().getEmail() + " Registered Succesfully", Toast.LENGTH_LONG).show();
//                        login(registerRequestModel.getEmail(), loginPassword);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<RegisterResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "something went wrong" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void AddTable(){
        IUserService userService = RetrofitClient.getRetrofitInstance().create(IUserService.class);
        AddTableRequestModel addTableRequestModel = new AddTableRequestModel();
        addTableRequestModel.capacity = addCapacity;

        Call<ResponseModel<TableResponseModel>>call = userService.addTable(addTableRequestModel);
        call.enqueue(new Callback<ResponseModel<TableResponseModel>>() {
            @Override
            public void onResponse(Call<ResponseModel<TableResponseModel>> call, Response<ResponseModel<TableResponseModel>> response) {
                ResponseModel<TableResponseModel> responseModel = response.body();

                if (responseModel != null) {
                    if (responseModel.getError() != null) {
                        Toast.makeText(getApplicationContext(), responseModel.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), responseModel.getData().getTableId() + "Table Added", Toast.LENGTH_LONG).show();
//
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TableResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "something went wrong" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }
}
