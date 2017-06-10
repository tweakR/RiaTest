package ria.com.riatest.ui.fragment.mainscreen.mapper;

import java.util.ArrayList;
import java.util.List;

import ria.com.riatest.db.entities.WeatherData;
import ria.com.riatest.db.entities.WeatherDataEntity;
import ria.com.riatest.model.WeatherModel;
import rx.functions.Func1;

public class DbMapper implements Func1<List<WeatherModel>, List<WeatherData>> {

    @Override
    public List<WeatherData> call(List<WeatherModel> weatherModels) {
        List<WeatherData> weatherList = new ArrayList<>();
        for (WeatherModel weatherModel : weatherModels) {
            WeatherDataEntity entity = new WeatherDataEntity();
            entity.setCity(weatherModel.getCity());
            entity.setDate(weatherModel.getDate());
            entity.setDescription(weatherModel.getDescription());
            entity.setHumidity(weatherModel.getHumidity());
            entity.setIcon(weatherModel.getIcon());
            entity.setPressure(weatherModel.getPressure());
            entity.setMaxTemperature(weatherModel.getMaxTemperature());
            entity.setMinTemperature(weatherModel.getMinTemperature());
            weatherList.add(entity);
        }

        return weatherList;
    }
}
