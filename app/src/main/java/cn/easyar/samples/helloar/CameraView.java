package cn.easyar.samples.helloar;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private Camera camera;

    public CameraView(Context context) {
        super(context);
        getHolder().addCallback(this);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters p = camera.getParameters();
        /**
         List sizes = p.getSupportedPictureSizes();
         Camera.Size result = null;
         for (int i=0;i<sizes.size();i++){
         result = (Camera.Size) sizes.get(i);
         Log.i("PictureSize", "Supported Size. Width: " + result.width + "height : " + result.height);
         }
         */
        p.setPreviewSize(640, 480);
        camera.setParameters(p);

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}