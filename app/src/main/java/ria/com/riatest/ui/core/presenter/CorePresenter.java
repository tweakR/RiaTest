package ria.com.riatest.ui.core.presenter;


import android.support.annotation.NonNull;

import ria.com.riatest.ui.core.view.CoreView;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

public abstract class CorePresenter<V extends CoreView> {

    private CompositeSubscription subscriptions;
    private V view;

    protected Action0 showProgress = () -> {
        if (view != null) {
            view.showProgress();
        }
    };
    protected Action0 hideProgress = () -> {
        if (view != null) {
            view.hideProgress();
        }
    };

    public void onCreateView(@NonNull V view) {
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    public void onCreate(@NonNull V view) {
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    public void onDestroyView() {
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }
        view = null;
    }

    public void subscribe(@NonNull Subscription observable) {
        this.subscriptions.add(observable);
    }

    public V getView() {
        return view;
    }

    public interface DataDeliver<T> {
        void onReceive(@NonNull T t);
    }
}