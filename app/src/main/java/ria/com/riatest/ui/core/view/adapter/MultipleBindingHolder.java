package ria.com.riatest.ui.core.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class MultipleBindingHolder<B extends ViewDataBinding> extends ViewHolder {
    public final B binding;

    public MultipleBindingHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }
}
