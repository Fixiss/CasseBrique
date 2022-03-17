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
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Niveaux extends SurfaceView implements SensorEventListener, SurfaceHolder.Callback{
    private SensorManager sensorManager;
    private int position_barre;
    private ThreadBarre barre;
    private DisplayMetrics display;

    public Niveaux(Context context) {
        super(context);
        getHolder().addCallback(this);
        barre = new ThreadBarre(getHolder(), this);
        setFocusable(true);
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
        sensorManager =  (SensorManager) this.getContext().getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        display = getContext().getResources().getDisplayMetrics();
        position_barre = display.widthPixels/2;
        barre.setRunning(true);
        barre.start();
    }

    public void drawBarre(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(position_barre-70, display.heightPixels-180, position_barre+70, display.heightPixels-150, paint);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                barre.setRunning(false);
                barre.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
