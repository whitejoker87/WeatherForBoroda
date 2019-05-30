package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.orehovai.weatherforboroda.model.TownCard;
import ru.orehovai.weatherforboroda.model.geoposition.ResponseGeoposition;
import ru.orehovai.weatherforboroda.model.weather.WeatherData;

public class ListTownsViewModel extends ViewModel {

    private String apikey = "5099ea72-fb4b-49e4-a88c-68794153a08a";
    private String format = "json";

    private String[] subStrGeoPos;//масссив координат

    private TownCard currentTown;//город заполняемый сейчас
    private List<TownCard> currentListTowns;//списко городов заполняемый сейчас
    private int currentPos;//текущая позиция в списке городов

    public void setCurrentTown(TownCard currentTown) {
        this.currentTown = currentTown;
    }

    public TownCard getCurrentTown() {
        return currentTown;
    }

    private final MutableLiveData<List<TownCard>> townsRussian = new MutableLiveData<>();//список русских городов
    private final MutableLiveData<List<TownCard>> townsOthers = new MutableLiveData<>();//список иностранных городов

    public void setTownsRussian() {
        TownCard town;
        String[] arrTownsRussian = {"Москва", "Санкт-Петербург", "Екатеринбург", "Краснодар", "Ростов-на-Дону", "Самара", "Архангельск", "Тула", "Брянск", "Мурманск"};

        List<TownCard> listTownsRussian = new ArrayList<>();

        for (String townRussian:arrTownsRussian) {
            town = new TownCard();
            town.setTownName(townRussian);
            listTownsRussian.add(town);
        }
        townsRussian.setValue(listTownsRussian);
    }

    public void setTownsOthers() {
        TownCard town;
        String[] arrTownsOthers = {"Нью-Йорк", "Лондон", "Париж", "Берлин", "Токио", "Пхеньян", "Тбилиси", "Вена", "Буэнос-Айрес", "Рейкъявик"};
        List<TownCard> listTownsOthers = new ArrayList<>();

        for (String townOther:arrTownsOthers) {
            town = new TownCard();
            town.setTownName(townOther);
            listTownsOthers.add(town);
        }
        townsOthers.setValue(listTownsOthers);
    }

    public MutableLiveData<List<TownCard>> getTownsRussian() {
        return townsRussian;
    }

    public MutableLiveData<List<TownCard>> getTownsOthers() {
        return townsOthers;
    }

    public void fillTownsList(List<TownCard>  towns, int pos){
        if (pos < towns.size()){//пока не закончатся города в переданном списке
            currentTown = towns.get(pos);
            currentListTowns = towns;
            currentPos = pos;
            downloadGeoData(currentTown.getTownName());//скачать данные геокодирования для города
        }
        else if (getTownsOthers().getValue() == null)//законился список русских городов - заполняем иносттранные
            setTownsOthers();
            else setFragmentLaunch("towns_list");
    }

    public void downloadGeoData(String townName) {
        //асинхронно получаем данные от геокодера
        App.getApiGeo().getGeoPosition(apikey, format, townName).enqueue(new Callback<ResponseGeoposition>() {
            @Override
            public void onResponse(Call<ResponseGeoposition> call, Response<ResponseGeoposition> response) {
                if (response.code() == 200)
                    setGeoData(response.body());//полученные данные сохраняем
            }

            @Override
            public void onFailure(Call<ResponseGeoposition> call, Throwable t) {

            }
        });
    }

    public void downloadWeatherData(String lat, String lon){
        //асинхронно получаем данные от api погоды
        App.getAPI().getWeatherData(apikey, lat, lon/*, 3, false, false*/).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.code() == 200)
                    setWeatherData(response.body());//сохраняем данные
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }

    private final MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();//данные о погоде

    public void setWeatherData(WeatherData data) {
        weatherData.setValue(data);
    }
    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    private final MutableLiveData<ResponseGeoposition> geoData = new MutableLiveData<>();//данные геокодера

    public void setGeoData(ResponseGeoposition data) {
        geoData.setValue(data);
    }
    public LiveData<ResponseGeoposition> getGeoData() {
        return geoData;
    }

    public void getGeoPos() {//получаем координаты
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
        downloadWeatherData(subStrGeoPos[1], subStrGeoPos[0]);//скачиваем данные погоды по координатам(яндекс возвращает координаты в обратном порядке)
    }

    public void setTownCard(TownCard town){//заполняем объект города
        if (town.getTownName().contains(Objects.requireNonNull(getGeoData()//если название города совпадает с названием города из ответа api погоды заполняем его
                .getValue())
                .getResponse()
                .getGeoObjectCollection()
                .getMetaDataProperty()
                .getGeocoderResponseMetaData()
                .getRequest())) {
            town.setTempToday(Objects.requireNonNull(getWeatherData().getValue()).getFact().getTemp());
            town.setTempTomorrow(getWeatherData().getValue().getForecast().getParts().get(1).getTempAvg());
            town.setDateFirstDay(getWeatherData().getValue().getForecast().getParts().get(0).getPartName());
            town.setDateSecondDay(getWeatherData().getValue().getForecast().getParts().get(1).getPartName());
            town.setDateThirdDay(getWeatherData().getValue().getForecast().getParts().get(1).getPartName());
            town.setTempFirstDay(getWeatherData().getValue().getForecast().getParts().get(0).getTempAvg());
            town.setTempSecondDay(getWeatherData().getValue().getForecast().getParts().get(1).getTempAvg());
            town.setTempThirdDay(getWeatherData().getValue().getForecast().getParts().get(1).getTempAvg());
        }
        fillTownsList(currentListTowns, currentPos + 1);//приступаем к следующему городу
    }

    private final MutableLiveData<String> fragmentLaunch = new MutableLiveData<>();//для запуска фрагмента

    public MutableLiveData<String> getFragmentLaunch() {
        return fragmentLaunch;
    }

    public void setFragmentLaunch(String setLaunch) {
        fragmentLaunch.setValue(setLaunch);
    }

    private final MutableLiveData<Boolean> progressbar = new MutableLiveData<>();//для запуска фрагмента

    public MutableLiveData<Boolean> isProgressbar() {
        return progressbar;
    }

    public void setProgressbar(Boolean bar) {
        progressbar.setValue(bar);
    }

    private boolean Russian;//флаг для загрузки списка русских городов

    public void setRussian(boolean isRussian) {
        this.Russian = isRussian;
    }

    public boolean isRussian() {
        return Russian;
    }

    private boolean today;//флаг для загрузки погоды на сегодня

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }
}
