package ru.arhlib.app.actual;

import android.view.View;

import ru.arhlib.app.MyWebViewClient;

public class ActualCallback {

    public void onClick(View view, Actual item) {
        MyWebViewClient.openUrl(view.getContext(), item.link);
    }
}
