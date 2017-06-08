package ria.com.riatest.ui.core.view;


import android.databinding.ViewDataBinding;

import ria.com.riatest.ui.core.presenter.CorePresenter;


public interface CoreView {

    CorePresenter getPresenter();

    ViewDataBinding getDataBinding();

    void showError(Throwable throwable);

    void showMessage(String text);

    boolean checkInternetConnection();

    void showProgress();

    void hideProgress();

}
