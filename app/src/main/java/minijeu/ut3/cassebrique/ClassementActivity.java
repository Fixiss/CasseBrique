package minijeu.ut3.cassebrique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ClassementActivity extends AppCompatActivity {

    private ListView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        loadScores();
    }

    public void OnClickRetour(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OnClickReset(View v){
        SharedPreferences sharedPreferences= this.getSharedPreferences("game", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this,"Scores reseted!",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OnClickTest(View v){
        //TODO cette partie ne sert qu'à démontrer l'enregistrement des scores, elle n'a rien à faire ici
        SharedPreferences sharedPreferences= this.getSharedPreferences("game", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        Scores scores = new Scores();
        scores.add("Florent","1 000 000");
        scores.add("Léo","1 000");
        scores.add("Clément","1");

        String json = gson.toJson(scores);
        editor.putString("scores", json);

        editor.apply();

        Toast.makeText(this,"Scores saved!",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //fin TODO
    }

    private void loadScores(){

        SharedPreferences sharedPreferences= this.getSharedPreferences("game", Context.MODE_PRIVATE);

        if(sharedPreferences.getAll().size()!=0) {

            Gson gson = new Gson();
            String json = sharedPreferences.getString("scores", "");
            Scores scores = gson.fromJson(json, Scores.class);

            scoreView = (ListView) findViewById(R.id.liste_score);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), scores);
            scoreView.setAdapter(customAdapter);

        }
    }
}