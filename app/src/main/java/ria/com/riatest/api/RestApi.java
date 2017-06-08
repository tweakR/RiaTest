package ria.com.riatest.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ria.com.riatest.api.response.MainResponse;
import rx.Observable;

public interface RestApi {

    @GET("daily?&mode=json&units=metric&cnt=7")
    Observable<MainResponse> getWeather(@Query("q") String city, @Query("apiKey") String apiKey);
}
