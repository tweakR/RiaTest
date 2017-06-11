package ria.com.riatest.db;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import ria.com.riatest.db.entities.CityData;
import ria.com.riatest.db.entities.WeatherData;
import rx.Observable;

public class DbManager implements DbCalls {

    @Inject
    SingleEntityStore<Persistable> dataStore;

    public DbManager(SingleEntityStore<Persistable> dataStore) {
        this.dataStore = dataStore;
    }

    private Observable<Boolean> clearDataBase() throws Exception {
        return Observable.create(subscriber -> {
            try {
                dataStore.toBlocking().delete(WeatherData.class).get().value();
                subscriber.onNext(true);
            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<WeatherData>> getWeatherData() {
        return Observable.just(dataStore.select(WeatherData.class).get().toList());
    }

    @Override
    public Observable<Boolean> saveWeatherData(List<WeatherData> data) throws Exception {
        return Observable.combineLatest(clearDataBase(), dataStore.insert(data).toObservable(), (t1, t2) -> true);
    }

    @Override
    public Observable<List<CityData>> getCityName() {
        return Observable.just(dataStore.select(CityData.class).get().toList());
    }

    @Override
    public Observable<Iterable<CityData>> saveCityName(Set<CityData> cityData) throws Exception {
        return dataStore.insert(cityData).toObservable();
    }

}
