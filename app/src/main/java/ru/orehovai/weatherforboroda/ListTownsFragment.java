package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.orehovai.weatherforboroda.databinding.ListTownsFragmentBinding;

public class ListTownsFragment extends Fragment {

    private ListTownsViewModel mViewModel;

    private ListTownsFragmentBinding binding;

    private RecyclerView listTowns;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_russia:
                    mViewModel.setRussian(true);
                    mTextMessage.setText(R.string.title_russia);
                    listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsRussian().getValue(), getActivity()));
                    return true;
                case R.id.navigation_other:
                    mViewModel.setRussian(false);
                    mTextMessage.setText(R.string.title_other);
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
        mTextMessage = binding.message;
        listTowns.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mViewModel.isRussian()) {
            listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsRussian().getValue(), getActivity()));
            mTextMessage.setText(R.string.title_russia);
        }
        else {
            listTowns.setAdapter(new TownsListAdapter(mViewModel.getTownsOthers().getValue(), getActivity()));
            mTextMessage.setText(R.string.title_other);
        }
    }
}

