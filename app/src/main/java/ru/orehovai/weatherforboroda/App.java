package ru.orehovai.weatherforboroda;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.orehovai.weatherforboroda.model.WeatherData;

public class App extends Application {

    private static IYandexWeatherAPI api;

    private static WeatherData data;

    //синглтон-оъбект с инфой из JSON
    public static synchronized WeatherData getWeatherData() {
        if (data == null) {
            data = new WeatherData();
        }
        return data;
    }

    public static synchronized void setWeatherData(WeatherData data) {
        App.data = data;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()//запрос для загрузки JSON
                .baseUrl("https://api.weather.yandex.ru/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(IYandexWeatherAPI.class);
    }

    public static IYandexWeatherAPI getAPI() {
        return api;
    }//интерфейс для загрузки JSON
}