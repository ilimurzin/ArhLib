package ru.arhlib.app.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.arhlib.app.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    public final ImageView image;
    public final TextView title;
    public final TextView excerpt;
    public final TextView date;

    public PostViewHolder(View view) {
        super(view);
        this.view = view;
        this.image = view.findViewById(R.id.post_image);
        this.title = view.findViewById(R.id.post_title);
        this.excerpt = view.findViewById(R.id.post_excerpt);
        this.date = view.findViewById(R.id.post_date);
    }
}
