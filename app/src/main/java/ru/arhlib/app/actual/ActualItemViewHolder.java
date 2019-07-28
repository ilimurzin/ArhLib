package ru.arhlib.app.actual;

import androidx.recyclerview.widget.RecyclerView;

import ru.arhlib.app.databinding.ActualItemBinding;
import ru.arhlib.app.databinding.PostItemBinding;

public class ActualItemViewHolder extends RecyclerView.ViewHolder {

    ActualItemBinding actualBinding;
    public PostItemBinding postBinding;

    ActualItemViewHolder(ActualItemBinding binding) {
        super(binding.getRoot());
        actualBinding = binding;
    }

    public ActualItemViewHolder(PostItemBinding binding) {
        super(binding.getRoot());
        postBinding = binding;
    }
}
