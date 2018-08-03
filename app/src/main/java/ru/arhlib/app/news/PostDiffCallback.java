package ru.arhlib.app.news;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class PostDiffCallback extends DiffUtil.ItemCallback<Post> {
    @Override
    public boolean areItemsTheSame(
            @NonNull Post oldPost, @NonNull Post newPost) {
        return oldPost.id == newPost.id;
    }
    @Override
    public boolean areContentsTheSame(
            @NonNull Post oldPost, @NonNull Post newPost) {
        return oldPost.title == newPost.title &&
                oldPost.excerpt == newPost.excerpt;
    }
}
