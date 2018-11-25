package ru.arhlib.app.actual;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class ActualDiffCallback extends DiffUtil.ItemCallback<Actual> {
    @Override
    public boolean areItemsTheSame(@NonNull Actual oldItem, @NonNull Actual newItem) {
        return oldItem.title.equals(newItem.title);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Actual oldItem, @NonNull Actual newItem) {
        return oldItem.description.equals(newItem.description)
                && oldItem.link.equals(newItem.link)
                && oldItem.emoji.equals(newItem.emoji);
    }
}
