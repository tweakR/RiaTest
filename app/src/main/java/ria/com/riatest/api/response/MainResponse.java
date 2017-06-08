package ria.com.riatest.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {

    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private double message;
    @SerializedName("city")
    private CityResponse city;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private List<ListResponse> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public CityResponse getCity() {
        return city;
    }

    public void setCity(CityResponse city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ListResponse> getList() {
        return list;
    }

    public void setList(List<ListResponse> list) {
        this.list = list;
    }

}
