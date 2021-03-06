package ru.orehovai.weatherforboroda;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.orehovai.weatherforboroda.model.geoposition.ResponseGeoposition;
import ru.orehovai.weatherforboroda.model.weather.WeatherData;

public class App extends Application {//класс для retrofit

    private static IYandexWeatherAPI api;
    private static IYandexGeopositionAPI apiGeo;

    private static WeatherData data;
    private static ResponseGeoposition dataGeo;

    public static synchronized WeatherData getWeatherData() {
        if (data == null) {
            data = new WeatherData();
        }
        return data;
    }

    public static synchronized ResponseGeoposition getGeoposition() {
        if (dataGeo == null) {
            dataGeo = new ResponseGeoposition();
        }
        return dataGeo;
    }

    public static synchronized void setWeatherData(WeatherData data) {
        App.data = data;
    }

    public static synchronized void setGeoposition(ResponseGeoposition dataGeo) {
        App.dataGeo = dataGeo;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weather.yandex.ru/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(IYandexWeatherAPI.class);

        Retrofit retrofitGeo = new Retrofit.Builder()
                .baseUrl("https://geocode-maps.yandex.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiGeo = retrofitGeo.create(IYandexGeopositionAPI.class);

    }

    public static IYandexWeatherAPI getAPI() {
        return api;
    }

    public static IYandexGeopositionAPI getApiGeo() {
        return apiGeo;
    }
}