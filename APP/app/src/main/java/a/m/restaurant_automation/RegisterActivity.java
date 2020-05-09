package a.m.restaurant_automation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends AppCompatActivity {

    EditText nameForRegister, emailForRegister, passwordForRegister;
    ImageButton signUp;
    Button toLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameForRegister = findViewById(R.id.name);
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
}
