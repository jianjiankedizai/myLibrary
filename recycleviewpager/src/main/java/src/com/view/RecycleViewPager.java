package src.com.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class RecycleViewPager<T extends IModel> extends LinearLayout {

    public RecycleViewPager(@NonNull Context context) {
        this(context, null);
    }

    public RecycleViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        addTabLayout();
        addViewPager();
    }

    private ViewPager viewPager;

    private void addViewPager() {
        viewPager = new ViewPager(getContext());
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(layoutParams);
        addView(viewPager);

    }

    private TabLayout tabLayout;

    private void addTabLayout() {
        tabLayout = new TabLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabLayout.setLayoutParams(layoutParams);
        addView(tabLayout);
    }


    public void setResourceAndData(int layout, List<T> data) {

        if (data.size() > 0 && !(data.get(0) instanceof IModel)) {
            throw new IllegalArgumentException("the data in the list must instance of IModel");
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(data, layout, getContext());

        viewPagerAdapter.setRequestChangeDataListener(new ViewPagerAdapter.RequestChangeDataListener() {
            @Override
            public void onLoadMore(BaseQuickAdapter baseQuickAdapter, int finalI) {
                RecycleViewPager.this.onLoadMore(baseQuickAdapter, finalI);

            }

            @Override
            public void onRefresh(BaseQuickAdapter baseQuickAdapter, int finalI) {
                RecycleViewPager.this.onRefresh(baseQuickAdapter, finalI);
            }

            @Override
            public void onBindData(BaseViewHolder helper, Object item) {
                RecycleViewPager.this.onBindData(helper, item);
            }
        });

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    public abstract void onLoadMore(BaseQuickAdapter baseQuickAdapter, int index);

    public abstract void onRefresh(BaseQuickAdapter baseQuickAdapter, int index);

    public abstract void onBindData(BaseViewHolder helper, Object item);


    public TabLayout getTabView() {
        return tabLayout;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
