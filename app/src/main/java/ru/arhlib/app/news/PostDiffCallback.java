package ru.arhlib.app.news;

import android.support.v7.util.DiffUtil;

public class PostDiffCallback extends DiffUtil.ItemCallback<Post> {
    @Override
    public boolean areItemsTheSame(Post oldPost, Post newPost) {
        return oldPost.id == newPost.id;
    }

    @Override
    public boolean areContentsTheSame(Post oldPost, Post newPost) {
        return oldPost.getTitle().equals(newPost.getTitle()) &&
                oldPost.getExcerpt().equals(newPost.getExcerpt());
    }
}
