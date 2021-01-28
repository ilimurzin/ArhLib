package ru.arhlib.app.afisha

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.arhlib.app.MyWebViewClient
import ru.arhlib.app.R
import ru.arhlib.app.databinding.AfishaActivityBinding

class AfishaActivity : AppCompatActivity() {
    private val viewModel: AfishaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AfishaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.setBackgroundColor(Color.TRANSPARENT)

        viewModel.afisha.observe(this, { result ->
            when (result) {
                is LoadResult.Success<Page> -> {
                    val data = getStyle() + result.data.getRenderedContent()
                    binding.webview.loadDataWithBaseURL("https://arhlib.ru/", data, "text/html; charset=UTF-8", "UTF-8", null)

                    binding.progressBar.visibility = View.GONE
                    binding.retryBlock.visibility = View.GONE
                }
                is LoadResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.retryBlock.visibility = View.GONE
                }
                is LoadResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.retryBlock.visibility = View.VISIBLE
                }
            }
        })

        binding.retryButton.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun getStyle(): String {
        return "<style>" + getString(R.string.style_css) + "</style>";
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.afisha_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.share -> {
                startActivity(Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, getString(R.string.afisha_url))
                        .setType("text/plain"))
                true
            }
            R.id.browser -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.afisha_url))))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
