package ru.arhlib.app.actual;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import ru.arhlib.app.databinding.ActualItemBinding;

public class ActualAdapter extends ListAdapter<Actual, ActualAdapter.ActualViewHolder> {

    public ActualAdapter() {
        super(new ActualDiffCallback());
    }

    @NonNull
    @Override
    public ActualViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActualItemBinding binding = ActualItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ActualViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActualViewHolder holder, int position) {
        holder.actualBinding.setCallback(new ActualCallback());
        holder.actualBinding.setActual(getItem(position));
        holder.actualBinding.executePendingBindings();
    }

    class ActualViewHolder extends ActualItemViewHolder {
        ActualViewHolder(ActualItemBinding binding) {
            super(binding);
        }
    }
}
