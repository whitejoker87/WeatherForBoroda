package ru.orehovai.weatherforboroda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import ru.orehovai.weatherforboroda.model.WeatherData;

public interface IYandexWeatherAPI {
    @Headers("X-Yandex-API-Key: c3a7cb4a-d543-4386-acd7-886d5fbb9839")
    @GET("informers")
    Call <WeatherData> getWeatherData(@Query("lat") String lat, @Query("lon") String lon);
}
