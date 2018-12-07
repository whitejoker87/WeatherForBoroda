package ru.orehovai.weatherforboroda;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.orehovai.weatherforboroda.databinding.TownsItemBinding;
import ru.orehovai.weatherforboroda.model.Town;

public class TownsListAdapter extends RecyclerView.Adapter<TownsListAdapter.TownViewHolder> {

    private List<Town> listTowns;

    public TownsListAdapter(List<Town> listTowns) {
        this.listTowns = listTowns;
    }

    @NonNull
    @Override
    public TownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TownsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.towns_item, parent, false);
        return new TownViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TownViewHolder holder, int position) {
        holder.bind(listTowns.get(position));
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
