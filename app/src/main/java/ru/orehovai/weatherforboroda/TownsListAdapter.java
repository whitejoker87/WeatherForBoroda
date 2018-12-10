package ru.orehovai.weatherforboroda;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.orehovai.weatherforboroda.databinding.TownsItemBinding;
import ru.orehovai.weatherforboroda.model.Town;

public class TownsListAdapter extends RecyclerView.Adapter<TownsListAdapter.TownViewHolder> {

    private List<Town> listTowns;
    //private Context context;
    private ListTownsViewModel model;

    public TownsListAdapter(List<Town> listTowns, Context context) {
        this.listTowns = listTowns;
        //this.context = context;
        model = ViewModelProviders.of((FragmentActivity)context).get(ListTownsViewModel.class);
    }

    @NonNull
    @Override
    public TownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TownsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.towns_item, parent, false);
        return new TownViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final TownViewHolder holder, final int position) {
        holder.bind(listTowns.get(position));
        holder.binding.cardTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.downloadGeoData(listTowns.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTowns.size();
    }

    class TownViewHolder extends RecyclerView.ViewHolder {

        TownsItemBinding binding;

        TownViewHolder(TownsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Town town) {
            binding.setTown(town);
            binding.executePendingBindings();
        }
    }

}
