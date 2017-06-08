package ria.com.riatest;

import android.app.Application;

import ria.com.riatest.constant.Constant;
import ria.com.riatest.di.component.ApplicationComponent;
import ria.com.riatest.di.component.DaggerApplicationComponent;
import ria.com.riatest.di.module.ApiModule;
import ria.com.riatest.di.module.ApplicationModule;

public class RiaApplication extends Application {

    private static RiaApplication instance;
    private ApplicationComponent component;

    public static RiaApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerApplicationComponent.builder()
                .apiModule(new ApiModule(Constant.API_BASE_URL))
                .applicationModule(new ApplicationModule(getInstance()))
                .build();
    }
}
