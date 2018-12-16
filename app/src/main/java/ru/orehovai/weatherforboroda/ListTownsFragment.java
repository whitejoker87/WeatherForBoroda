package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.orehovai.weatherforboroda.databinding.ListTownsFragmentBinding;

public class ListTownsFragment extends Fragment {

    private ListTownsViewModel mViewModel;

    private ListTownsFragmentBinding binding;

    private RecyclerView listTowns;

    private TabLayout tabLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_russia:
                    mViewModel.setRussian(true);
                    listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsRussian().getValue(), getActivity()));
                    return true;
                case R.id.navigation_other:
                    mViewModel.setRussian(false);
                    listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsOthers().getValue(), getActivity()));
                    return true;
            }
            return false;
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_towns_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BottomNavigationView navigation = binding.navigation;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewModel = ViewModelProviders.of(getActivity()).get(ListTownsViewModel.class);
        listTowns = binding.recyclerTowns;
        listTowns.setLayoutManager(new LinearLayoutManager(getActivity()));
        fillTodayList();//устанавливаем флаг для загрузки погоды на сегодня
        if (mViewModel.isRussian()) {//загружаем русские города
            listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsRussian().getValue(), getActivity()));
        }
        else {//или нерусские
            listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsOthers().getValue(), getActivity()));
        }

        tabLayout = binding.daysTabs;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fillTodayList();
                        break;
                    case 1:
                        fillTomorrowList();
                        break;
                    default:
                        break;
                }
                if (mViewModel.isRussian())  listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsRussian().getValue(), getActivity()));
                else listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsOthers().getValue(), getActivity()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void fillTodayList() {
        mViewModel.setToday(true);
    }

    private void fillTomorrowList() {
        mViewModel.setToday(false);
    }

}


