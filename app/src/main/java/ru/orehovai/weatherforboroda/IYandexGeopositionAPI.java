package ru.orehovai.weatherforboroda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.orehovai.weatherforboroda.model.geoposition.ResponseGeoposition;

public interface IYandexGeopositionAPI {
        @GET("1.x")
        Call<ResponseGeoposition> getGeoPosition(@Query("apikey") String apikey,
                                                 @Query("format") String format,
                                                 @Query("geocode") String adress);

}
