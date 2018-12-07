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
import ru.orehovai.weatherforboroda.model.Geoposition.ResponseGeoposition;
import ru.orehovai.weatherforboroda.model.Town;
import ru.orehovai.weatherforboroda.model.WeatherData;

public class ListCountriesViewModel extends ViewModel {

    private String apikey = "c3a7cb4a-d543-4386-acd7-886d5fbb9839";
    private String format = "json";
    private String geocode = null;

    private String[] subStrGeoPos;

    public void downloadWeatherData(){
        App.getAPI().getWeatherData(getLatGeoPos(), getLonGeoPos()).enqueue(new Callback<WeatherData>() {//используем Retrofit 2 и асинхронную загрузку
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


    public void downloadGeoData() {
        App.getApiGeo().getGeoPosition("c3a7cb4a-d543-4386-acd7-886d5fbb9839", "json", "Москва").enqueue(new Callback<ResponseGeoposition>() {
            @Override
            public void onResponse(Call<ResponseGeoposition> call, Response<ResponseGeoposition> response) {
                if (response.code() == 200){
                    setGeoData(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseGeoposition> call, Throwable t) {

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

    private final MutableLiveData<ResponseGeoposition> geoData = new MutableLiveData<>();

    public void setGeoData(ResponseGeoposition data) {
        geoData.setValue(data);
    }
    public LiveData<ResponseGeoposition> getGeoData() {
        return geoData;
    }

    public void getGeoPos() {
        String geoPos = "";
        if (geoData.getValue() != null) {
            geoPos = geoData.getValue()
                    .getResponse()
                    .getGeoObjectCollection()
                    .getFeatureMember()
                    .get(0)
                    .getGeoObject()
                    .getPoint()
                    .getPos();
        }
        subStrGeoPos = geoPos.split(" ");
    }

    public String getLonGeoPos() {
        return subStrGeoPos[0];
    }

    public String getLatGeoPos () {
        if (subStrGeoPos == null) getGeoPos();
        return subStrGeoPos[1];
    }


    String[] arrTownsRussian = {"Москва", "Санкт-Петербург", "Екатеринбург", "Краснодар", "Ростов-на-Дону", "Самара", "Архангельск", "Тула", "Брянск", "Мурманск"};
    private List<Town> townsRussian = new ArrayList<>();
    String[] arrTownsOthers = {"Нью-Йорк", "Лондон", "Париж", "Берлин", "Токио", "Пхеньян", "Тбилиси", "Вена", "Буэнос-Айрес", "Рейкъявик"};
    private List<Town> townsOthers = new ArrayList<>();

    public void setTowns() {
        Town town;
        for (String townRussian:arrTownsRussian) {
            town = new Town();
            town.setName(townRussian);
            townsRussian.add(town);
        }
        for (String townOther:arrTownsOthers) {
            town = new Town();
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
