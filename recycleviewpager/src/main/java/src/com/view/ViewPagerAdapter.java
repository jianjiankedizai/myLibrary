package src.com.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuXiaolong
 * on: 2017-12-13 15:27
 * 个人博客: http://wuxiaolong.me
 */
public class ViewPagerAdapter<T extends List<IModel>> extends PagerAdapter {
    private final Context context;
    private T models;
    private List<View> viewList = new ArrayList<>();

    public ViewPagerAdapter(T datas, final int layout, Context context) {
        this.models = datas;
        this.context = context;

        for (int i = 0; i < datas.size(); i++) {
            final int finalI = i;
            final BaseQuickAdapter baseQuickAdapter = new RecycleViewAdapter(layout, datas.get(i)) {
                @Override
                public void onConvert(ViewHolder helper, Object item) {
                    if (requestChangeDataListener != null)
                        requestChangeDataListener.onBindData(helper, item);
                }
            };


            baseQuickAdapter.setLoadMoreView(new SimpleLoadMoreView());
            baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (requestChangeDataListener != null)
                        requestChangeDataListener.onLoadMore(baseQuickAdapter, finalI);

                }
            });


            final SwipeRefreshRecycleView swipeRefreshRecycleView = new SwipeRefreshRecycleView(context);

            swipeRefreshRecycleView.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshRecycleView.swipeRefreshLayout.setRefreshing(false);
                    if (requestChangeDataListener != null)
                        requestChangeDataListener.onRefresh(baseQuickAdapter, finalI);

                }
            });


            swipeRefreshRecycleView.setAdapter(baseQuickAdapter);
            viewList.add(swipeRefreshRecycleView);
        }


    }


    public interface RequestChangeDataListener {
        void onLoadMore(BaseQuickAdapter baseQuickAdapter, int finalI);

        void onRefresh(BaseQuickAdapter baseQuickAdapter, int finalI);


        void onBindData(BaseViewHolder helper, Object item);
    }


    private RequestChangeDataListener requestChangeDataListener;

    public void setRequestChangeDataListener(RequestChangeDataListener requestChangeDataListener) {
        this.requestChangeDataListener = requestChangeDataListener;
    }



    public interface OnBindItemDataListener {
        void OnBindData(BaseViewHolder helper, Object item);

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return models.get(position).getTitle();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}
