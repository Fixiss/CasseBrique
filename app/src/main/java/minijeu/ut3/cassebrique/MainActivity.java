package minijeu.ut3.cassebrique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickJouerButton(View v) {
        Intent intent = new Intent(this, NiveauxActivity.class);
        startActivity(intent);
    }
    public void OnClickClassementButton(View v) {
        Intent intent = new Intent(this, ClassementActivity.class);
        startActivity(intent);
    }

    public void OnClickLuminositeButton(View v) {
        Intent intent = new Intent(this, LuminositeActivity.class);
        startActivity(intent);
    }
}