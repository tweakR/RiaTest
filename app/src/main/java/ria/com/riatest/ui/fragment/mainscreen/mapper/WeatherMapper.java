package ria.com.riatest.ui.fragment.mainscreen.mapper;

import java.util.ArrayList;
import java.util.List;

import ria.com.riatest.api.response.ListResponse;
import ria.com.riatest.api.response.MainResponse;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.model.WeatherModel;
import ria.com.riatest.util.DateConverter;
import rx.functions.Func1;

public class WeatherMapper implements Func1<MainResponse, List<WeatherModel>> {

    @Override
    public List<WeatherModel> call(MainResponse mainResponse) {
        List<WeatherModel> weatherList = new ArrayList<>();
        WeatherModel model = new WeatherModel();

        for (ListResponse listResponse : mainResponse.getList()) {
            model = new WeatherModel();
            model.setCity(mainResponse.getCity().getName());
            model.setMaxTemperature(String.valueOf((int) listResponse.getTemp().getMax()) + "\u2103");
            model.setMinTemperature(String.valueOf((int) listResponse.getTemp().getMin()) + "\u2103");
            model.setDate(DateConverter.convertDate(listResponse.getDt()));
            model.setPressure(String.valueOf((int) listResponse.getPressure()) + " hPa");
            model.setHumidity(listResponse.getHumidity() + "\u0025");
            model.setId(listResponse.getWeather().get(0).getId());
            model.setIcon(Constant.ICON_URL + listResponse.getWeather().get(0).getIcon() + ".png");
            model.setDescription(listResponse.getWeather().get(0).getDescription());
            weatherList.add(model);
        }

        return weatherList;
    }
}
