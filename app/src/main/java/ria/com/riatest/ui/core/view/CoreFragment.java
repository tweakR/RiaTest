package ria.com.riatest.ui.core.view;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import ria.com.riatest.ui.core.activity.BaseActivity;
import ria.com.riatest.ui.core.presenter.CorePresenter;


public abstract class CoreFragment<P extends CorePresenter, B extends ViewDataBinding> extends Fragment implements CoreView {
    private static final int PERMISSION_REQUEST_CODE = 9000;
    @Inject
    P presenter;
    private B binding;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        getPresenter().onCreate(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        getPresenter().onCreateView(this);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroyView();
    }

    protected abstract int getLayoutId();

    /**
     * method for injecting presenter into component
     */
    protected abstract void inject();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public B getDataBinding() {
        return binding;
    }

    @Override
    public void showError(Throwable throwable) {
        showMessage(throwable.getMessage());
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void showProgress() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideProgress();
        }
    }
}
