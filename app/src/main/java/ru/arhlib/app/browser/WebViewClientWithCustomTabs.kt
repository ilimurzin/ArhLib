package ru.arhlib.app.browser

import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientWithCustomTabs : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        CustomTabs.openUrl(view.context, url)
        return true
    }
}
