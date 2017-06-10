package ria.com.riatest.ui.fragment.mainscreen.bindmodel;

import android.os.Parcel;
import android.os.Parcelable;

import ria.com.riatest.db.entities.WeatherData;

public class WeatherViewModel implements Parcelable {
    public static final Creator<WeatherViewModel> CREATOR = new Creator<WeatherViewModel>() {
        @Override
        public WeatherViewModel createFromParcel(Parcel in) {
            return new WeatherViewModel(in);
        }

        @Override
        public WeatherViewModel[] newArray(int size) {
            return new WeatherViewModel[size];
        }
    };
    private WeatherData data;

    public WeatherViewModel(WeatherData data) {
        this.data = data;
    }

    protected WeatherViewModel(Parcel in) {
        data = in.readParcelable(WeatherData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCity() {
        return data.getCity();
    }

    public String getMaxTemperature() {
        return data.getMaxTemperature();
    }

    public String getMinTemperature() {
        return data.getMinTemperature();
    }

    public String getIcon() {
        return data.getIcon();
    }

    public String getHumidity() {
        return data.getHumidity();
    }

    public String getDate() {
        return data.getDate();
    }

    public String getDescription() {
        return data.getDescription();
    }

    public String getPressure() {
        return data.getPressure();
    }
}
