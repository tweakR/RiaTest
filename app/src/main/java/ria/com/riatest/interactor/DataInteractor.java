package ria.com.riatest.interactor;

import javax.inject.Inject;

import ria.com.riatest.BuildConfig;
import ria.com.riatest.api.RestApi;
import ria.com.riatest.api.response.MainResponse;
import rx.Observable;

public class DataInteractor {

    private RestApi api;

    @Inject
    DataInteractor(RestApi api) {
        this.api = api;
    }

    public Observable<MainResponse> getWeather(String city) {
        return api.getWeather(city, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
    }
}
