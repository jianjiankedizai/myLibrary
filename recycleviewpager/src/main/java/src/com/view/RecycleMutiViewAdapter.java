package src.com.view;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by wangchangjian on 2016/8/12.
 */
public abstract class RecycleMutiViewAdapter extends BaseMultiItemQuickAdapter{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RecycleMutiViewAdapter(List data) {
        super(data);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }
}
