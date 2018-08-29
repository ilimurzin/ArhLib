package ru.arhlib.app.news;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.arhlib.app.R;
import ru.arhlib.app.news.PostFragment.OnListFragmentInteractionListener;

public class PostAdapter extends ListAdapter<Post, PostViewHolder> {

    private final OnListFragmentInteractionListener listener;

    public PostAdapter(OnListFragmentInteractionListener listener) {
        super(new PostDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = getItem(position);
        holder.title.setText(post.getTitle());
        holder.excerpt.setText(post.getExcerpt());
        holder.date.setText(post.getDate());

        //Context context = holder.view.getContext();
        ImageView imageView = holder.image;
        String imageUrl = post.getImageUrl();
        if (imageUrl != null) {
            Picasso.get()
                    .load(imageUrl)
                    .centerCrop()
                    .fit()
                    .placeholder(R.color.colorPlaceholder)
                    .into(imageView);
        }

        holder.view.setOnClickListener(view -> {
            if (listener != null) {
                listener.onListFragmentInteraction(post);
            }
        });
    }
}
