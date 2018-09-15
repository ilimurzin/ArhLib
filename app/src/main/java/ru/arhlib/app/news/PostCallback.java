package ru.arhlib.app.news;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class PostCallback {

    public void onClick(View view, Post post) {
        Context context = view.getContext();
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra("link", post.link);
        intent.putExtra("title", post.getTitle());
        intent.putExtra("content", post.getContent());
        intent.putExtra("imageUrl", post.getImageUrl());
        context.startActivity(intent);
    }
}
