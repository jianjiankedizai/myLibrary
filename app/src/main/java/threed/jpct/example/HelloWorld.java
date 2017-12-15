package threed.jpct.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import cn.easyar.samples.helloar.My3DLayout;
import cn.easyar.samples.helloar.ObjData;
import cn.easyar.samples.helloar.R;

/**
 * @author Darai
 */
public class HelloWorld extends Activity {


    private FrameLayout cotral;
    private FrameLayout container;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activityhello);
        initView();
        my3DLayout = new My3DLayout(this);

        ObjData objData = new ObjData();
        objData.setObj("0.obj");
        objData.setMtl("0.mtl");


        my3DLayout.setObjResorse(objData);

        cotral.addView(my3DLayout);

    }

    private void initView() {


        cotral = ((FrameLayout) findViewById(R.id.cotral));
        container = ((FrameLayout) findViewById(R.id.cotral));


    }

    My3DLayout my3DLayout;

    @Override
    protected void onPause() {
        super.onPause();
        my3DLayout.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        my3DLayout.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        my3DLayout.onStop();
    }


}
