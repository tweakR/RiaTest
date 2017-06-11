package ria.com.riatest.db;

import java.util.List;
import java.util.Set;

import ria.com.riatest.db.entities.CityData;
import ria.com.riatest.db.entities.WeatherData;
import rx.Observable;

public interface DbCalls {

    Observable<List<WeatherData>> getWeatherData();

    Observable<Boolean> saveWeatherData(List<WeatherData> data) throws Exception;

    Observable<List<CityData>> getCityName();

    Observable<Iterable<CityData>> saveCityName(Set<CityData> cityData) throws Exception;
}
