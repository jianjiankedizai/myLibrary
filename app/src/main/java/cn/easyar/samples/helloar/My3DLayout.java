package cn.easyar.samples.helloar;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by WuXiaolong
 * on: 2017-12-08 17:37
 * 个人博客: http://wuxiaolong.me
 */
public class My3DLayout extends FrameLayout {


    private GLSurfaceView mGLView;
    private MyRenderer renderer = null;
    private FrameBuffer fb = null;
    private World world = null;
    private int fps = 0;
    private Light sun = null;
    private GL10 lastGl = null;
    private RGBColor back = new RGBColor(0, 0, 0, 0);
    public Object3D mainObj;
    private FrameLayout cotrol;
    private FrameLayout container;

    private Map<String, Integer> textures = new HashMap<>();
    private ObjData objData;


    public My3DLayout(@NonNull Context context) {
        this(context, null);
    }

    public My3DLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Logger.log("onCreate");

        mGLView = new GLSurfaceView(context);
        mGLView.setEGLContextClientVersion(2);
        mGLView.setPreserveEGLContextOnPause(true);
        mGLView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        renderer = new MyRenderer();
        mGLView.setRenderer(renderer);
        addView(mGLView);


    }

    public void onPause() {
        mGLView.onPause();
    }


    public void onResume() {
        mGLView.onResume();
    }

    public void onStop() {
        System.exit(0);
    }


    public void setObjResorse(ObjData objData) {
        this.objData = objData;


    }


    class MyRenderer implements GLSurfaceView.Renderer {

        private long time = System.currentTimeMillis();

        public MyRenderer() {
        }

        private boolean first = true;

        public void onSurfaceChanged(GL10 gl, int w, int h) {

            // Renew the frame buffer
            if (lastGl != gl) {
                Log.i("HelloWorld", "Init buffer");
                if (fb != null) {
                    fb.dispose();
                }
                fb = new FrameBuffer(w, h);
                fb.setVirtualDimensions(fb.getWidth(), fb.getHeight());
                lastGl = gl;
            } else {
                fb.resize(w, h);
                fb.setVirtualDimensions(w, h);
            }


            // Create the world if not yet created
            if (first) {
                first = false;
                world = new World();
                world.setAmbientLight(40, 40, 40);

                sun = new Light(world);
                sun.setIntensity(255, 255, 255);

                // Create the texture we will use in the blitting
                Texture bodya1_0 = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.mipmap.bodya1_0)), 256, 256));
                Texture eye1_0 = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.mipmap.eye1_0)), 256, 256));
                TextureManager.getInstance().addTexture("bodya1_0", bodya1_0);
                TextureManager.getInstance().addTexture("eye1_0", eye1_0);

                Object3D objs[] = new Object3D[0];
                try {
                    InputStream obgS = getContext().getAssets().open("obj/" + objData.getObj());
                    InputStream mtlS = getContext().getAssets().open("obj/" + objData.getMtl());
                    objs = Loader.loadOBJ(obgS, mtlS, 0.05f);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < objs.length; i++) {


                    Object3D obj = objs[i];
                    obj.translate(0, 0, 0);
                    if (i == 0) mainObj = obj;
                    SimpleVector se = mainObj.getRotationPivot();
                    if (i == 0)
                        obj.setTexture("eye1_0");
                    else obj.setTexture("bodya1_0");
                    obj.build();

                    if (i != 0) mainObj.addChild(obj);
                    world.addObject(obj);


                }
                mainObj.setRotationPivot(SimpleVector.ORIGIN);
                mainObj.translate(0, 0, 20);

                Camera cam = world.getCamera();
                cam.moveCamera(Camera.CAMERA_MOVEOUT, 15);
                cam.lookAt(SimpleVector.ORIGIN);

                SimpleVector sv = new SimpleVector();
                sv.set(SimpleVector.ORIGIN);
                sv.y -= 100;
                sv.z -= 100;
                sun.setPosition(sv);
                MemoryHelper.compact();
            }
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        }

        public void onDrawFrame(GL10 gl) {

            // Draw the main screen
            fb.clear(back);
            world.renderScene(fb);
            world.draw(fb);
            fb.display();

            if (System.currentTimeMillis() - time >= 1000) {
                Logger.log(fps + "fps");
                fps = 0;
                time = System.currentTimeMillis();
            }
            fps++;
        }
    }


    private float lastY = 0;
    private float lastX = 0;
    private double fingerBegainDis;
    private int firstIndex;
    private int secondIndex;

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 2) return true;
        if (mainObj == null) return super.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_POINTER_DOWN) {
            secondIndex = event.getActionIndex();
            fingerBegainDis = getFingerDistance(event, firstIndex, secondIndex);
            return true;
        }


        if (action == MotionEvent.ACTION_DOWN) {
            lastY = 0;
            lastX = 0;
            firstIndex = event.getActionIndex();
            return true;
        }

        if (action == MotionEvent.ACTION_UP) {
            return true;
        }

        if (action == MotionEvent.ACTION_MOVE) {
            if (event.getPointerCount() == 1) {
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                float dx = rawX - lastX;
                float dy = rawY - lastY;
                if (lastX == 0) dx = 0;
                if (lastY == 0) dy = 0;

                mainObj.rotateX((float) (-Math.PI * dy / 360));
                mainObj.rotateY((float) (-Math.PI * dx / 360));
                lastY = rawY;
                lastX = rawX;
            } else if (event.getPointerCount() == 2) {
                double currDis = getFingerDistance(event, firstIndex, secondIndex);
                if (fingerBegainDis == 0) return true;
                float absScale = (float) (currDis / fingerBegainDis);
                if (absScale < 0.2f) absScale = 0.2f;
                else if (absScale > 3f) absScale = 3f;
                mainObj.setScale(absScale);

            }

            return true;
        }
        try {
            Thread.sleep(15);
        } catch (Exception e) {
            // No need for this...
        }

        return super.onTouchEvent(event);
    }

    private double getFingerDistance(MotionEvent event, int firstIndex, int secondIndex) {
        double pow = Math.pow(Math.pow((event.getX(firstIndex) - event.getX(secondIndex)), 2f) +
                Math.pow((event.getY(firstIndex) - event.getY(secondIndex)), 2f), 0.5f);
        return pow;
    }


}
