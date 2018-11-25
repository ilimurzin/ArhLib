package ru.arhlib.app.actual;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import ru.arhlib.app.databinding.ActualItemBinding;

public class ActualAdapter extends ListAdapter<Actual, ActualViewHolder> {

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
        holder.binding.setCallback(new ActualCallback());
        holder.binding.setActual(getItem(position));
        holder.binding.executePendingBindings();
    }
}
