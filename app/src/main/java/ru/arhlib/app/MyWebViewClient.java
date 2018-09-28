package ru.arhlib.app;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.browser.customtabs.CustomTabsIntent;

public class MyWebViewClient extends WebViewClient {
    public static void openUrl(Context context, String url) {
        new CustomTabsIntent.Builder()
                .setToolbarColor(context.getResources().getColor(R.color.colorPrimary))
                .setShowTitle(true)
                .build().launchUrl(context, Uri.parse(url));
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        openUrl(view.getContext(), url);
        return true;
    }
}
