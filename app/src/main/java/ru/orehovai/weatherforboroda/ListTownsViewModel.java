package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.orehovai.weatherforboroda.model.TownCard;
import ru.orehovai.weatherforboroda.model.geoposition.ResponseGeoposition;
import ru.orehovai.weatherforboroda.model.Town;
import ru.orehovai.weatherforboroda.model.weather.WeatherData;

public class ListTownsViewModel extends ViewModel {

    private String apikey = "c3a7cb4a-d543-4386-acd7-886d5fbb9839";
    private String format = "json";
    private String geocode = null;

    private String[] subStrGeoPos;

    private final MutableLiveData<List<Town>> townsRussian = new MutableLiveData<>();
    private final MutableLiveData<List<Town>> townsOthers = new MutableLiveData<>();

    public void setTowns() {
        Town town;
        String[] arrTownsRussian = {"Москва", "Санкт-Петербург", "Екатеринбург", "Краснодар", "Ростов-на-Дону", "Самара", "Архангельск", "Тула", "Брянск", "Мурманск"};
        String[] arrTownsOthers = {"Нью-Йорк", "Лондон", "Париж", "Берлин", "Токио", "Пхеньян", "Тбилиси", "Вена", "Буэнос-Айрес", "Рейкъявик"};
        List<Town> listTownsRussian = new ArrayList<>();
        List<Town> listTownsOthers = new ArrayList<>();

        for (String townRussian:arrTownsRussian) {
            town = new Town();
            town.setName(townRussian);
            listTownsRussian.add(town);
        }
        townsRussian.setValue(listTownsRussian);

        for (String townOther:arrTownsOthers) {
            town = new Town();
            town.setName(townOther);
            listTownsOthers.add(town);
        }
        townsOthers.setValue(listTownsOthers);
    }

    public MutableLiveData<List<Town>> getTownsRussian() {
        return townsRussian;
    }

    public MutableLiveData<List<Town>> getTownsOthers() {
        return townsOthers;
    }


    
    public void downloadGeoData(String townName) {
        App.getApiGeo().getGeoPosition("5099ea72-fb4b-49e4-a88c-68794153a08a", "json", townName).enqueue(new Callback<ResponseGeoposition>() {
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

    public void downloadWeatherData(String lat, String lon){
        App.getAPI().getWeatherData(lat, lon, 3, false, false).enqueue(new Callback<WeatherData>() {//используем Retrofit 2 и асинхронную загрузку
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
    private final MutableLiveData<TownCard> townCard = new MutableLiveData<>();

     public void setTownCard() {
        TownCard tempTownCard = new TownCard();
        if (getWeatherData().getValue() != null){
            tempTownCard.setTownName(getGeoData().getValue().getResponse().getGeoObjectCollection().getMetaDataProperty().getGeocoderResponseMetaData().getRequest());
            tempTownCard.setTempToday(getWeatherData().getValue().getFact().getTemp());
            tempTownCard.setTempTomorrow(getWeatherData().getValue().getForecasts().get(1).getParts().getDayShort().getTemp());
            tempTownCard.setDateFirstDay(getWeatherData().getValue().getForecasts().get(0).getDate());
            tempTownCard.setDateSecondDay(getWeatherData().getValue().getForecasts().get(1).getDate());
            tempTownCard.setDateThirdDay(getWeatherData().getValue().getForecasts().get(2).getDate());
            tempTownCard.setTempFirstDay(getWeatherData().getValue().getForecasts().get(0).getParts().getDayShort().getTemp());
            tempTownCard.setTempSecondDay(getWeatherData().getValue().getForecasts().get(1).getParts().getDayShort().getTemp());
            tempTownCard.setTempThirdDay(getWeatherData().getValue().getForecasts().get(2).getParts().getDayShort().getTemp());
        }
        townCard.setValue(tempTownCard);
    }

    public LiveData<TownCard> getTownCard() {
        return townCard;
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
        downloadWeatherData(subStrGeoPos[1], subStrGeoPos[0]);
    }

//    public String getLonGeoPos() {
//        return subStrGeoPos[0];
//    }
//
//    public String getLatGeoPos () {
//        //getGeoPos();
//        return subStrGeoPos[1];
//    }




    /*For observe to launch fragment*/
    private final MutableLiveData<String> fragmentLaunch = new MutableLiveData<>();

    public MutableLiveData<String> getFragmentLaunch() {
        return fragmentLaunch;
    }

    public void setFragmentLaunch(String setLaunch) {
        fragmentLaunch.setValue(setLaunch);
    }

    private boolean Russian;

    public void setRussian(boolean isRussian) {
        this.Russian = isRussian;
    }

    public boolean isRussian() {
        return Russian;
    }
}
