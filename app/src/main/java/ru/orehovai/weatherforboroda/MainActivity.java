package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ru.orehovai.weatherforboroda.model.WeatherData;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListCountriesViewModel model = ViewModelProviders.of(this).get(ListCountriesViewModel.class);
        model.downloadWeatherData();
        model.setTowns();
        model.getWeatherData().observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                if (weatherData != null){
                    setFragment(new ListCountruesFragment());
                }
            }
        });

        //Geocoder geocoder = new Geocoder()

    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
