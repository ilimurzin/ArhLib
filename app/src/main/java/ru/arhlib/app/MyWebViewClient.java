package ru.arhlib.app;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        openUrl(view.getContext(), url);
        return true;
    }

    public static void openUrl(Context context, String url) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));
        intentBuilder.setShowTitle(true);
        intentBuilder.build().launchUrl(context, Uri.parse(url));
    }
}
