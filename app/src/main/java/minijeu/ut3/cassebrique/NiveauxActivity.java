package minijeu.ut3.cassebrique;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NiveauxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveaux);
    }

    public void OnClickNiv1(View v){

    }

    public void OnClickNiv2(View v){

    }

    public void OnClickNiv3(View v){

    }

    public void OnClickRetour(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
