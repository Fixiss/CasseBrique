package minijeu.ut3.cassebrique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClassementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
    }

    public void OnClickRetour(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}