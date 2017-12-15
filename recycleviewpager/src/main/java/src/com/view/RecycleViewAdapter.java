package src.com.view;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by wangchangjian on 2016/8/12.
 */
public abstract class RecycleViewAdapter extends BaseQuickAdapter<Object, RecycleViewAdapter.ViewHolder> {


    public RecycleViewAdapter(int res, IModel mapList) {
        super(res, mapList.getData());
    }

    @Override
    protected void convert(ViewHolder helper, Object item) {
        onConvert(helper, item);
    }


    public abstract void onConvert(ViewHolder helper, Object item);

    static class ViewHolder extends BaseViewHolder {
        ViewHolder(View view) {
            super(view);
        }

    }


}
