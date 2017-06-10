package ria.com.riatest.db;

import java.util.List;

import ria.com.riatest.db.entities.WeatherData;
import rx.Observable;

public interface DbCalls {

    Observable<List<WeatherData>> getWeatherData();

    Observable<Boolean> saveWeatherData(List<WeatherData> data) throws Exception;
}
