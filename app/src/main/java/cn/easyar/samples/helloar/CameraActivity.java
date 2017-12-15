package cn.easyar.samples.helloar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import src.com.view.RecycleViewPager;
import src.com.view.UserTest;

/**
 * Created by WuXiaolong
 * on: 2017-12-12 09:10
 * 个人博客: http://wuxiaolong.me
 */
public class CameraActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        My3DLayout my3DLayout = new My3DLayout(this);
//        ObjData objData = new ObjData();
//        objData.setObj("0.obj");
//        objData.setMtl("0.mtl");
//        my3DLayout.setObjResorse(objData);
//        setContentView(my3DLayout);
//
//        CameraView cameraView = new CameraView(this);
//        addContentView(cameraView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        List<UserTest> data = new ArrayList<>();
        data.add(new UserTest());
        data.add(new UserTest());
        data.add(new UserTest());
        data.add(new UserTest());
        data.add(new UserTest());

        RecycleViewPager recycleViewPager = new RecycleViewPager(this) {
            @Override
            public void onLoadMore(final BaseQuickAdapter baseQuickAdapter, int index) {

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseQuickAdapter.loadMoreEnd();
                    }
                }, 1000);

            }

            @Override
            public void onRefresh(final BaseQuickAdapter baseQuickAdapter, int index) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseQuickAdapter.loadMoreEnd();
                    }
                }, 1000);

            }

            @Override
            public void onBindData(BaseViewHolder helper, Object item) {

            }
        };


        TabLayout tabLayout = recycleViewPager.getTabView();//暴露出来便于设置一些参数
        ViewPager viewPager = recycleViewPager.getViewPager();//暴露出来便于设置一些参数


        recycleViewPager.setResourceAndData(R.layout.recycle_item, data);

        setContentView(recycleViewPager);


    }
}
