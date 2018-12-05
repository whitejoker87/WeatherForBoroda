package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.orehovai.weatherforboroda.databinding.ListCountruesFragmentBinding;

public class ListCountruesFragment extends Fragment {

    private ListCountriesViewModel mViewModel;

    private ListCountruesFragmentBinding binding;

    private RecyclerView listTowns;
    private TownsListAdapter adapter;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_russia:
                    mTextMessage.setText(R.string.title_russia);
                    return true;
                case R.id.navigation_other:
                    mTextMessage.setText(R.string.title_other);
                    return true;
            }
            return false;
        }
    };

    public static ListCountruesFragment newInstance() {
        return new ListCountruesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_countrues_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTextMessage = binding.message;
        BottomNavigationView navigation = binding.navigation;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        listTowns = binding.recyclerTowns;
        listTowns.setLayoutManager(new LinearLayoutManager(getActivity()));

        mViewModel = ViewModelProviders.of(getActivity()).get(ListCountriesViewModel.class);
        mViewModel.getWeatherData()
    }

}
