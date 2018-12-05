package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.orehovai.weatherforboroda.model.WeatherData;

public class ListCountriesViewModel extends ViewModel {

    public void downloadWeatherData(){
        App.getAPI().getWeatherData().enqueue(new Callback<WeatherData>() {//используем Retrofit 2 и асинхронную загрузку
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {//получаем ответ
                if(response.code() == 200) {
                    setWeatherData(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {//обработка ошибок Retrofit

//                if(t instanceof SocketTimeoutException || t instanceof UnknownHostException)
//                    showError("Check network connection");
//                else
//                    showError("Error: " + t.getMessage());

            }
        });
    }

    private final MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();

    public void setWeatherData(WeatherData data) {
        weatherData.setValue(data);
    }
    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    private List<String> towns = new ArrayList<>();
    String[] arrTowns = {"Москва", "Санкт-Петербург", "Екатеринбург", "Краснодар"};
    

}
