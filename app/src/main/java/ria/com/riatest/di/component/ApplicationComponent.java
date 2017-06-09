package ria.com.riatest.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ria.com.riatest.di.module.ApiModule;
import ria.com.riatest.di.module.ApplicationModule;
import ria.com.riatest.ui.fragment.detailed.view.WeatherDetailedFragment;
import ria.com.riatest.ui.fragment.mainscreen.view.MainScreenFragment;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})

public interface ApplicationComponent {

    void inject(MainScreenFragment mainScreenFragment);

    void inject(WeatherDetailedFragment weatherDetailedFragment);
}
