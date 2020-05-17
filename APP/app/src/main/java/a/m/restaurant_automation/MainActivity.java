package a.m.restaurant_automation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import a.m.restaurant_automation.repository.UserSession;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.logout_menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                UserSession.getInstance().clearSession();
                Intent goToMainActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(goToMainActivity);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
