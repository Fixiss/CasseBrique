package minijeu.ut3.cassebrique;

import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

public class ThreadBarre extends Thread{
    private SurfaceHolder surfaceHolder;
    private Niveaux niveau;
    private Canvas canvas;
    private boolean running;
    private Handler mHandler;


    public ThreadBarre(SurfaceHolder surfaceHolder, Niveaux niveau) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.niveau = niveau;
        mHandler = new Handler();
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    @Override
    public void run() {
        if (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.niveau.drawBarre(canvas);
                }
            } catch (Exception e) {}
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        mHandler.post(this);
    }
}
