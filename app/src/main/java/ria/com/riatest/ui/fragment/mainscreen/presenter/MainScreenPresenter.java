package ria.com.riatest.ui.fragment.mainscreen.presenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import ria.com.riatest.db.entities.CityData;
import ria.com.riatest.db.entities.CityDataEntity;
import ria.com.riatest.db.entities.WeatherData;
import ria.com.riatest.interactor.DataInteractor;
import ria.com.riatest.ui.core.presenter.CorePresenter;
import ria.com.riatest.ui.fragment.mainscreen.mapper.DbMapper;
import ria.com.riatest.ui.fragment.mainscreen.mapper.WeatherMapper;
import ria.com.riatest.ui.fragment.mainscreen.view.MainScreenView;
import ria.com.riatest.util.RxTransformers;

public class MainScreenPresenter extends CorePresenter<MainScreenView> {

    private DataInteractor interactor;

    @Inject
    MainScreenPresenter(DataInteractor interactor) {
        this.interactor = interactor;
    }

    public void getWeather(String city) {
        subscribe(interactor.getWeather(city)
                .map(new WeatherMapper())
                .map(new DbMapper())
                .compose(RxTransformers.applyApiRequestSchedulers())
                .compose(RxTransformers.applyOnBeforeAndAfter(showProgress, hideProgress))
                .subscribe(this::onSuccessWeather, this::onError));
    }

    private void onSuccessWeather(List<WeatherData> weatherData) {
        getView().setWeatherList(weatherData);
        try {
            subscribe(interactor.getDbManager().saveWeatherData(weatherData).subscribe());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataFromDb() {
        subscribe(interactor.getDbManager().getWeatherData()
                .subscribe(this::onSuccessGetFromDb, this::onError));
    }

    public void saveCityName(String city) {
        Set<CityData> list = new HashSet<>();
        CityDataEntity entity = new CityDataEntity();
        entity.setCity(city);
        list.add(entity);
        try {
            subscribe(interactor.getDbManager()
                    .saveCityName(list)
                    .subscribe(this::onSuccessSaveCity, this::onError));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSuccessSaveCity(Iterable<CityData> cityData) {
        subscribe(interactor.getDbManager()
                .getCityName()
                .compose(RxTransformers.applyApiRequestSchedulers())
                .subscribe(this::onSuccessGetCity, this::onError));
    }

    public void getCity() {
        subscribe(interactor.getDbManager()
                .getCityName()
                .compose(RxTransformers.applyApiRequestSchedulers())
                .subscribe(this::onSuccessGetCity, this::onError));
    }

    private void onSuccessGetCity(List<CityData> cityData) {
        List<String> cityNames = new ArrayList<>();
        for (CityData data : cityData) {
            cityNames.add(data.getCity());
        }
        getView().setCity(cityNames);
    }


    private void onSuccessGetFromDb(List<WeatherData> weatherData) {
        getView().setWeatherList(weatherData);
    }

    private void onError(Throwable throwable) {
        getView().showError(throwable);
    }

}
