package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListTownsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = ViewModelProviders.of(this).get(ListTownsViewModel.class);
        model.downloadGeoData();
//        model.getGeoData().observe(this, new Observer<ResponseGeoposition>() {
//            @Override
//            public void onChanged(@Nullable ResponseGeoposition responseGeoposition) {
//                startDownloadWeatherData();
//            }
//        });

        model.setTowns();

        setFragment(new ListTownsFragment());
//        model.getWeatherData().observe(this, new Observer<WeatherData>() {
//            @Override
//            public void onChanged(@Nullable WeatherData weatherData) {
//                if (weatherData != null){
//                    setFragment(new ListTownsFragment());
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

//    void startDownloadWeatherData() {
//        model.downloadWeatherData();
//    }

}
