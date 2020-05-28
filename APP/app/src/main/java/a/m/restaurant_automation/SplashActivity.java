package a.m.restaurant_automation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a.m.restaurant_automation.responseModel.LoginResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import androidx.appcompat.app.AppCompatActivity;

import a.m.restaurant_automation.repository.UserSession;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {
    Animation animationTop;
    ImageView imageView;
    UserSession session;
    private final int SPLASH_DISPLAY_LENGTH = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.imageView_splashLogo);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("session", 0); // 0 - for private mode
        session = UserSession.getInstance(preferences);
        animationTop = AnimationUtils.loadAnimation(this, R.anim.splash_logo_in_anim);
        imageView.setAnimation(animationTop);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!TextUtils.isEmpty(session.getToken())&& !TextUtils.isEmpty(session.getEmail() )&& !TextUtils.isEmpty(session.getToken())) {
                        startActivity(new Intent(SplashActivity.this,ManagerActivity.class));
                        finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
        }
}
