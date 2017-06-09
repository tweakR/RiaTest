package ria.com.riatest.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {

    @SerializedName("dt")
    private long dt;
    @SerializedName("temp")
    private TempResponse temp;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("weather")
    private List<WeatherResponse> weather;

    public List<WeatherResponse> getWeather() {
        return weather;
    }

    public TempResponse getTemp() {
        return temp;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
