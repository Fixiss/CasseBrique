package minijeu.ut3.cassebrique;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class Niveaux extends SurfaceView implements SensorEventListener, SurfaceHolder.Callback{
    private SensorManager sensorManager;
    private int position_barre;
    private int position_balle_x;
    private int position_balle_y;
    private String direction_balle = "BasDroite";
    private ThreadJeu jeu;
    private DisplayMetrics display;
    private Handler mHandler;
    private final Runnable deplacementBalle = new Runnable() {
        public void run() {
            if(position_balle_y+20 >= display.heightPixels - 180 && position_balle_x+20 >= position_barre-70 && position_balle_x-20 <= position_barre+70){
                if(direction_balle == "BasDroite"){
                    direction_balle = "HautDroite";
                }
                if(direction_balle == "BasGauche"){
                    direction_balle = "HautGauche";
                }
            }
            if(position_balle_x+20 >= display.widthPixels){
                if(direction_balle == "BasDroite"){
                    direction_balle = "BasGauche";
                }
                if(direction_balle == "HautDroite"){
                    direction_balle = "HautGauche";
                }
            }
            if(position_balle_x-20 <= 0){
                if(direction_balle == "BasGauche"){
                    direction_balle = "BasDroite";
                }
                if(direction_balle == "HautGauche"){
                    direction_balle = "HautDroite";
                }
            }
            if(position_balle_y-20 <= 0){
                if(direction_balle == "HautGauche"){
                    direction_balle = "BasGauche";
                }
                if(direction_balle == "HautDroite"){
                    direction_balle = "BasDroite";
                }
            }
            if(direction_balle == "BasDroite"){
                position_balle_x+=3;
                position_balle_y+=3;
            }
            if(direction_balle == "BasGauche") {
                position_balle_x-=3;
                position_balle_y+=3;
            }
            if(direction_balle == "HautDroite") {
                position_balle_x+=3;
                position_balle_y-=3;
            }
            if(direction_balle == "HautGauche") {
                position_balle_x-=3;
                position_balle_y-=3;
            }
            mHandler.post(deplacementBalle);
        }
    };

    public Niveaux(Context context) {
        super(context);
        getHolder().addCallback(this);
        jeu = new ThreadJeu(getHolder(), this);
        setFocusable(true);
        display = getContext().getResources().getDisplayMetrics();
        position_barre = display.widthPixels/2;
        position_balle_x = display.widthPixels/2;
        position_balle_y = display.heightPixels/2;
        sensorManager =  (SensorManager) this.getContext().getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        mHandler = new Handler();
        mHandler.post(deplacementBalle);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        position_barre = (int) (position_barre-(sensorEvent.values[0]*2));
        if(position_barre < 0){
            position_barre=0;
        }
        if(position_barre > display.widthPixels){
            position_barre=display.widthPixels;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //ne rien faire
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        jeu.setRunning(true);
        jeu.start();
    }

    public void drawElements(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            drawBalle(canvas);
            drawBarre(canvas);
        }
    }
    public void drawBarre(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(250, 0, 0));
        canvas.drawRect(position_barre - 70, display.heightPixels - 180, position_barre + 70, display.heightPixels - 150, paint);
    }

    public void drawBalle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(250, 0, 0));
        canvas.drawRect(position_balle_x-20, position_balle_y-20, position_balle_x+20, position_balle_y+20, paint);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                jeu.setRunning(false);
                jeu.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
