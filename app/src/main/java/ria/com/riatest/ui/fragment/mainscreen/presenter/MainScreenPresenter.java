package ria.com.riatest.ui.fragment.mainscreen.presenter;

import java.util.List;

import javax.inject.Inject;

import ria.com.riatest.interactor.DataInteractor;
import ria.com.riatest.model.WeatherModel;
import ria.com.riatest.ui.core.presenter.CorePresenter;
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
                .compose(RxTransformers.applyApiRequestSchedulers())
                .compose(RxTransformers.applyOnBeforeAndAfter(showProgress, hideProgress))
                .subscribe(this::onSuccessWeather, this::onError));
    }

    private void onError(Throwable throwable) {
        getView().showError(throwable);
    }

    private void onSuccessWeather(List<WeatherModel> weather) {
        getView().setWeatherList(weather);
    }
}
