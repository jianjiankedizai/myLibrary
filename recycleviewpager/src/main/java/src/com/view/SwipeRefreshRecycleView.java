package src.com.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import compositpublish.retech.cn.recycleviewpager.R;

/**
 * Created by WuXiaolong
 * on: 2017-12-14 09:03
 * 个人博客: http://wuxiaolong.me
 */
public class SwipeRefreshRecycleView extends FrameLayout {
    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;

    public SwipeRefreshRecycleView(@NonNull Context context) {
        this(context, null);
    }

    public SwipeRefreshRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.swipe_recycleview, this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });

    }


    public void setAdapter(BaseQuickAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }


}
