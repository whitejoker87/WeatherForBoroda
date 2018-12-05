package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.orehovai.weatherforboroda.model.Town;
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


    String[] arrTownsRussian = {"Москва", "Санкт-Петербург", "Екатеринбург", "Краснодар", "Ростов-на-Дону", "Самара", "Архангельск", "Тула", "Брянск", "Мурманск"};
    private List<Town> townsRussian = new ArrayList<>();
    String[] arrTownsOthers = {"Нью-Йорк", "Лондон", "Париж", "Берлин", "Токио", "Пхеньян", "Тбилиси", "Вена", "Буэнос-Айрес", "Рейкъявик"};
    private List<Town> townsOthers = new ArrayList<>();

    public void setTowns() {
        Town town = new Town();
        for (String townRussian:arrTownsRussian) {
            town.setName(townRussian);
            townsRussian.add(town);
        }
        for (String townOther:arrTownsOthers) {
            town.setName(townOther);
            townsOthers.add(town);
        }
    }

    public List<Town> getTownsRussian() {
        return townsRussian;
    }

    public List<Town> getTownsOthers() {
        return townsOthers;
    }

}
