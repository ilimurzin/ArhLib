package ru.arhlib.app.news;

import android.support.v7.widget.RecyclerView;

import ru.arhlib.app.databinding.PostItemBinding;

public class PostViewHolder extends RecyclerView.ViewHolder {

    final PostItemBinding binding;

    public PostViewHolder(PostItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
