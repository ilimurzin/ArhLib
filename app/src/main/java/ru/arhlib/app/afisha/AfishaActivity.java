package ru.arhlib.app.afisha;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import ru.arhlib.app.MyWebViewClient;
import ru.arhlib.app.R;

public class AfishaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setBackgroundColor(0);
        String style = "<style>" + getString(R.string.style_css) + "</style>";
        AfishaViewModel viewModel = ViewModelProviders.of(this).get(AfishaViewModel.class);
        viewModel.getPage(6671).observe(this, page -> {
            // null on first start
            if (page != null) {
                String data = style + page.getContent();
                webView.loadData(data, "text/html; charset=UTF-8", null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
