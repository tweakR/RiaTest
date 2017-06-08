package ria.com.riatest.ui.core.view.adapter;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BindingHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    /**
     * The Binding.
     */
    public final B binding;

    /**
     * Instantiates a new Binding holder.
     *
     * @param binding the binding
     */
    public BindingHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
