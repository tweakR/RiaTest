package ria.com.riatest.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Context context;

    private Application application;

    public ApplicationModule(Application application, Context context) {
        this.context = context;
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

}
