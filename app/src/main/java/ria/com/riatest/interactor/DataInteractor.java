package ria.com.riatest.interactor;

import ria.com.riatest.BuildConfig;
import ria.com.riatest.api.RestApi;
import ria.com.riatest.api.response.MainResponse;
import ria.com.riatest.db.DbManager;
import rx.Observable;

public class DataInteractor {

    private RestApi api;
    private DbManager dbManager;

    public DataInteractor(DbManager dbManager, RestApi api) {
        this.api = api;
        this.dbManager = dbManager;
    }

    public Observable<MainResponse> getWeather(String city) {
        return api.getWeather(city, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
    }

    public DbManager getDbManager() {
        return dbManager;
    }
}
