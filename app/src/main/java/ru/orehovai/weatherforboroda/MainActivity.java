package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ru.orehovai.weatherforboroda.model.TownCard;
import ru.orehovai.weatherforboroda.model.geoposition.ResponseGeoposition;
import ru.orehovai.weatherforboroda.model.weather.WeatherData;

public class MainActivity extends AppCompatActivity {

    ListTownsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = ViewModelProviders.of(this).get(ListTownsViewModel.class);
        model.setTownsRussian();//заполнение списка русских городов

        //наблюдаем за изменением списка русских городов
        model.getTownsRussian().observe(this, new Observer<List<TownCard>>() {
            @Override
            public void onChanged(@Nullable List<TownCard> towns) {
                model.setRussian(true);//устанавливаем флаг для определения какой списко грузить
                if (towns != null) {
                    model.fillTownsList(towns, 0);//когда список готов - заполняем его данными
                }
            }
        });

        //наблюдаем за изменением списка иностранных гороов
        model.getTownsOthers().observe(this, new Observer<List<TownCard>>() {
            @Override
            public void onChanged(@Nullable List<TownCard> towns) {
                if (towns != null) {
                    model.fillTownsList(towns, 0);
                }
            }
        });

        //наблюдаем сигнал о загрузке фрагмента
        model.getFragmentLaunch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String aString) {
                if (aString != null) {
                    switch (aString) {
                        case "towns_list"://фрагмент списка городов
                            setFragment(new ListTownsFragment());
                            break;
                        case "town_card"://фрагмент карточки города
                            setFragment(new TownCardFragment());
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        //наблюдаем данные геокодера
        model.getGeoData().observe(this, new Observer<ResponseGeoposition>() {
            @Override
            public void onChanged(@Nullable ResponseGeoposition responseGeoposition) {
                if (responseGeoposition != null) model.getGeoPos();//как только получены данные - извлекаем координаты
            }
        });

        //наблюдаем данные погоды
        model.getWeatherData().observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                if (weatherData != null) {
                    model.setTownCard(model.getCurrentTown());//как только получены данные о погоде заполняем их в объект города
                }
            }
        });
    }

    public void setFragment(Fragment fragment){
        //не загружать новый фрагмент если это фрагмент списка городов и сейчас уже есть какой то фрагмент в стеке
        if (fragment instanceof ListTownsFragment && getSupportFragmentManager().getFragments().size() > 0) {
            return;
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
