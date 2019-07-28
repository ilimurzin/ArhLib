package ru.arhlib.app.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import ru.arhlib.app.actual.ActualItemViewHolder;
import ru.arhlib.app.databinding.PostItemBinding;

public class PostAdapter extends ListAdapter<Post, PostAdapter.PostViewHolder> {

    public PostAdapter() {
        super(new PostDiffCallback());
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostItemBinding binding = PostItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.postBinding.setCallback(new PostCallback());
        holder.postBinding.setPost(getItem(position));
        holder.postBinding.executePendingBindings();
    }

    class PostViewHolder extends ActualItemViewHolder {
        PostViewHolder(PostItemBinding binding) {
            super(binding);
        }
    }
}
