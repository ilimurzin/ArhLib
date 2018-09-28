package ru.arhlib.app.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import ru.arhlib.app.databinding.PostItemBinding;

public class PostAdapter extends ListAdapter<Post, PostViewHolder> {

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
        holder.binding.setCallback(new PostCallback());
        holder.binding.setPost(getItem(position));
        holder.binding.executePendingBindings();
    }
}
