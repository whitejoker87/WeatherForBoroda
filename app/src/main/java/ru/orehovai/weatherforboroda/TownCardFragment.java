package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.orehovai.weatherforboroda.databinding.TownCardFragmentBinding;

public class TownCardFragment extends Fragment {

    private ListTownsViewModel model;

    private TownCardFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.town_card_fragment, container, false);
        model = ViewModelProviders.of(getActivity()).get(ListTownsViewModel.class);
        //binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTowncard(model.getCurrentTown());
        binding.executePendingBindings();
    }

}
