package ria.com.riatest.model;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class WeatherModel implements Parcelable {

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };
    private int id;
    private String city;
    private String date;
    private String minTemperature;
    private String maxTemperature;
    private String pressure;
    private String humidity;
    private String description;
    private String icon;

    public WeatherModel() {
    }

    protected WeatherModel(Parcel in) {
        id = in.readInt();
        city = in.readString();
        date = in.readString();
        minTemperature = in.readString();
        maxTemperature = in.readString();
        pressure = in.readString();
        humidity = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String icon) {
        Picasso.with(view.getContext())
                .load(icon)
                .into(view);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(city);
        dest.writeString(date);
        dest.writeString(minTemperature);
        dest.writeString(maxTemperature);
        dest.writeString(pressure);
        dest.writeString(humidity);
        dest.writeString(description);
        dest.writeString(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
