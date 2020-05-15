package a.m.restaurant_automation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button singInButton, createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        singInButton = findViewById(R.id.signInButton);
        createAccount = findViewById(R.id.createAccount);
        final Intent intentToLogin = new Intent(this,LoginFragment.class);
        final Intent intentTORegister = new Intent(this, RegisterFragment.class);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToLogin);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentTORegister);
            }
        });


    }
}
