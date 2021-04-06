package ru.arhlib.app.news

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import ru.arhlib.app.R
import ru.arhlib.app.browser.CustomTabs
import ru.arhlib.app.browser.WebViewClientWithCustomTabs
import ru.arhlib.app.databinding.PostActivityBinding

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = PostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.postTitle.text = getPostTitle()

        binding.webview.webViewClient = WebViewClientWithCustomTabs()
        binding.webview.setBackgroundColor(Color.TRANSPARENT)
        binding.webview.loadDataWithBaseURL("https://arhlib.ru/", getContent(), "text/html; charset=UTF-8", "UTF-8", null)

        if (getImageUrl().startsWith("http")) {
            binding.imageView.isVisible = true

            Picasso.get()
                    .load(getImageUrl())
                    .centerCrop()
                    .fit()
                    .placeholder(R.color.colorPlaceholder)
                    .into(binding.imageView)

            binding.imageView.setOnClickListener {
                CustomTabs.openUrl(this, getSourceImageUrl())
            }
        }
    }

    private fun getPostTitle(): String {
        return intent.getStringExtra("title") ?: ""
    }

    private fun getContent(): String {
        val style = "<style>" + getString(R.string.style_css) + "</style>"
        return style + intent.getStringExtra("content")
    }

    private fun getLink(): String {
        return intent.getStringExtra("link") ?: ""
    }

    private fun getImageUrl(): String {
        return intent.getStringExtra("imageUrl") ?: ""
    }

    private fun getSourceImageUrl(): String {
        return intent.getStringExtra("sourceImageUrl") ?: ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.post_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.share -> {
                startActivity(Intent.createChooser(Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, getLink())
                        .setType("text/plain"), null))
                true
            }
            R.id.browser -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getLink())))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
