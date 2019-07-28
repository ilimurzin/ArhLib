package ru.arhlib.app.actual;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.arhlib.app.news.Post;
import ru.arhlib.app.news.PostDiffCallback;

public class ActualItemDiffCallback extends DiffUtil.ItemCallback<ActualItem> {
    @Override
    public boolean areItemsTheSame(@NonNull ActualItem oldItem, @NonNull ActualItem newItem) {
        if (oldItem.getType() == newItem.getType()) {
            if (oldItem.getType() == ActualItem.TYPE_LINK) {
                return (new ActualDiffCallback()).areItemsTheSame((Actual) oldItem, (Actual) newItem);
            } else if (oldItem.getType() == ActualItem.TYPE_POST) {
                return (new PostDiffCallback()).areItemsTheSame((Post) oldItem, (Post) newItem);
            }
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ActualItem oldItem, @NonNull ActualItem newItem) {
        if (oldItem.getType() == newItem.getType()) {
            if (oldItem.getType() == ActualItem.TYPE_LINK) {
                return (new ActualDiffCallback()).areContentsTheSame((Actual) oldItem, (Actual) newItem);
            } else if (oldItem.getType() == ActualItem.TYPE_POST) {
                return (new PostDiffCallback()).areContentsTheSame((Post) oldItem, (Post) newItem);
            }
        }
        return false;
    }
}
