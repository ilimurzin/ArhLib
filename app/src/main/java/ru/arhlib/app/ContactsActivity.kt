package ru.arhlib.app

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.arhlib.app.browser.WebViewClientWithCustomTabs
import ru.arhlib.app.databinding.WebviewBinding

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = WebviewBinding.inflate(layoutInflater)
        setContentView(binding.webview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webview.webViewClient = WebViewClientWithCustomTabs()
        binding.webview.loadUrl("file:///android_asset/contacts.html")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
