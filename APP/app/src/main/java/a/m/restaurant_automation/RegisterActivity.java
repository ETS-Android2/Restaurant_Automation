package a.m.restaurant_automation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends AppCompatActivity {

    EditText firstNameForRegister, emailForRegister, passwordForRegister,lastNameForRegister;

    ImageButton signUp;
    Button toLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameForRegister = findViewById(R.id.firstName);
        lastNameForRegister = findViewById(R.id.lastName);
        emailForRegister = findViewById(R.id.emailRegister);
        passwordForRegister = findViewById(R.id.passwordRegister);
        signUp = findViewById(R.id.signup);
        toLoginPage = findViewById(R.id.signinToLoginActivity);
        final Intent intentToLoginPage = new Intent(this,LoginActivity.class);
        toLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(intentToLoginPage);
            finish();
            }
        });



    }

    public boolean checkEmptyFields(){
        if (TextUtils.isEmpty(firstNameForRegister.getText().toString())){
            firstNameForRegister.setError("First Name Cannot be Empty!");
            firstNameForRegister.requestFocus();
            return true;
        }
        else if (TextUtils.isEmpty(lastNameForRegister.getText().toString())){
            lastNameForRegister.setError("Last Name Cannot be Empty!");
            lastNameForRegister.requestFocus();
            return true;
        }
        else if (TextUtils.isEmpty(emailForRegister.getText().toString())){
            emailForRegister.setError("Email Cannot be Empty!");
            emailForRegister.requestFocus();
            return true;
        }
        else if (TextUtils.isEmpty(passwordForRegister.getText().toString())){
            passwordForRegister.setError("Password Cannot be Empty!");
            passwordForRegister.requestFocus();
            return true;
        }
        return false;
    }

    public boolean checkPassword (){
        if (passwordForRegister.getText().length() <6){
            passwordForRegister.setError("Password Cannot be less than 6 Characters.");
            passwordForRegister.requestFocus();
            return true;
        }
        return false;
    }
}
