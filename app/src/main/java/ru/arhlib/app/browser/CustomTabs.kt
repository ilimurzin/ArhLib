package ru.arhlib.app.browser

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object CustomTabs {
    private val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()

    fun openUrl(context: Context, url: String) {
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}
