package ru.arhlib.app.actual;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import ru.arhlib.app.news.Post;
import ru.arhlib.app.news.PostAdapter;
import ru.arhlib.app.news.PostCallback;

public class ActualItemAdapter extends ListAdapter<ActualItem, ActualItemViewHolder> {

    public ActualItemAdapter() {
        super(new ActualItemDiffCallback());
    }

    @NonNull
    @Override
    public ActualItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ActualItem.TYPE_LINK:
                return (new ActualAdapter()).onCreateViewHolder(parent, viewType);
            case ActualItem.TYPE_POST:
                return (new PostAdapter()).onCreateViewHolder(parent, viewType);
            default:
                throw new RuntimeException("Тип должен быть задан.");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ActualItemViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ActualItem.TYPE_LINK:
                holder.actualBinding.setCallback(new ActualCallback());
                holder.actualBinding.setActual((Actual) getItem(position));
                holder.actualBinding.executePendingBindings();
                break;
            case ActualItem.TYPE_POST:
                holder.postBinding.setCallback(new PostCallback());
                holder.postBinding.setPost((Post) getItem(position));
                holder.postBinding.executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }
}
