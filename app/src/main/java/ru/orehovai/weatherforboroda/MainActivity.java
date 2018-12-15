package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ru.orehovai.weatherforboroda.model.Town;
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
        model.setTowns();

        model.getTownsRussian().observe(this, new Observer<List<Town>>() {
            @Override
            public void onChanged(@Nullable List<Town> towns) {
                model.setRussian(true);
                if (towns != null) {
                    model.downloadGeoData(towns);
                   //setFragment(new ListTownsFragment());
                }
            }
        });

        model.getFragmentLaunch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String aString) {
                if (aString != null) {
                    switch (aString) {
                        case "town_card":
                            setFragment(new TownCardFragment());
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        model.getGeoData().observe(this, new Observer<ResponseGeoposition>() {
            @Override
            public void onChanged(@Nullable ResponseGeoposition responseGeoposition) {
                if (responseGeoposition != null) model.getGeoPos();;
            }
        });

        model.getWeatherData().observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                if (weatherData != null) model.setTownCard();
            }
        });

        model.getTownCard().observe(this, new Observer<TownCard>() {
            @Override
            public void onChanged(@Nullable TownCard townCard) {
                if (townCard != null)model.setFragmentLaunch("town_card");
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
