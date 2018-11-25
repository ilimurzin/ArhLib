package ru.arhlib.app.actual;

import androidx.recyclerview.widget.RecyclerView;
import ru.arhlib.app.databinding.ActualItemBinding;

public class ActualViewHolder extends RecyclerView.ViewHolder {

    final ActualItemBinding binding;

    public ActualViewHolder(ActualItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
