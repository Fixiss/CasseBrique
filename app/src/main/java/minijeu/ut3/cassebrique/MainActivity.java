package minijeu.ut3.cassebrique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musique);
        mediaPlayer.start();
    }

    public void OnClickJouerButton(View v) {
        mediaPlayer.pause();
        Intent intent = new Intent(this, NiveauxActivity.class);
        startActivity(intent);
    }
    public void OnClickClassementButton(View v) {
        mediaPlayer.pause();
        Intent intent = new Intent(this, ClassementActivity.class);
        startActivity(intent);
    }

    public void OnClickLuminositeButton(View v) {
        mediaPlayer.pause();
        Intent intent = new Intent(this, LuminositeActivity.class);
        startActivity(intent);
    }
}