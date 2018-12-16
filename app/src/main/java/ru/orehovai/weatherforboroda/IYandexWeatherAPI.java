package ru.orehovai.weatherforboroda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.orehovai.weatherforboroda.model.weather.WeatherData;

public interface IYandexWeatherAPI {
    //@Headers("X-Yandex-API-Key: 5099ea72-fb4b-49e4-a88c-68794153a08a")
    //@GET("informers")
    @GET("forecast")
    Call <WeatherData> getWeatherData(@Header("X-Yandex-API-Key") String apikey,
                                      @Query("lat") String lat,
                                      @Query("lon") String lon,
                                      @Query("limit") int limit,
                                      @Query("hours") boolean hours,
                                      @Query("extra") boolean extra);
}
