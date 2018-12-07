package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.orehovai.weatherforboroda.model.Geoposition.ResponseGeoposition;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListCountriesViewModel model = ViewModelProviders.of(this).get(ListCountriesViewModel.class);
        model.downloadGeoData();
        model.getGeoData().observe(this, new Observer<ResponseGeoposition>() {
            @Override
            public void onChanged(@Nullable ResponseGeoposition responseGeoposition) {
                model.downloadWeatherData();
            }
        });

        model.setTowns();

        setFragment(new ListCountruesFragment());
//        model.getWeatherData().observe(this, new Observer<WeatherData>() {
//            @Override
//            public void onChanged(@Nullable WeatherData weatherData) {
//                if (weatherData != null){
//                    setFragment(new ListCountruesFragment());
//                }
//            }
//        });

    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
