package cn.easyar.samples.helloar;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.fernandosierra.obj2es2.Converter;
import com.fernandosierra.obj2es2.GlEs2Model;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by WuXiaolong
 * on: 2017-12-06 13:18
 * 个人博客: http://wuxiaolong.me
 */
public class ImageRecognition implements GLSurfaceView.Renderer {
    private boolean first = true;
    private GL10 lastGl = null;
    private Context context;
    private HelloAR AR;
    GlEs2Model obj;
    GlEs2Model mtl;


    public ImageRecognition(Context context) {
        this.context = context;

        Converter converter = new Converter();


        try {
            obj = converter.parseObjFile(context.getAssets().open("obj/0.obj"));
            mtl = converter.parseObjFile(context.getAssets().open("obj/0.mtl"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
        AR.resizeGL(w, h);
    }


    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        AR.initGL();
    }

    public void onDrawFrame(GL10 gl) {
        // Draw the main screen
        AR.render(obj, mtl);
    }

    public void setAR(HelloAR AR) {
        this.AR = AR;
    }
}