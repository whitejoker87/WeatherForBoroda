package ru.orehovai.weatherforboroda;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import ru.orehovai.weatherforboroda.databinding.TownsItemBinding;

public class TownsListAdapter extends RecyclerView.Adapter<TownsListAdapter.TownViewHolder> {

    @NonNull
    @Override
    public TownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TownViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TownViewHolder extends RecyclerView.ViewHolder {

        TownsItemBinding binding;

        public TownViewHolder(TownsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind()
    }

}
