package ru.arhlib.app.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ru.arhlib.app.MyWebViewClient;
import ru.arhlib.app.R;

public class PostActivity extends AppCompatActivity {

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        link = intent.getStringExtra("link");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String imageUrl = intent.getStringExtra("imageUrl");

        String style = "<style>" + getString(R.string.style_css) + "</style>";
        content = style + "<h1>" + title + "</h1>" + content;

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadDataWithBaseURL("https://arhlib.ru/", content, "text/html; charset=UTF-8", "UTF-8", null);

        ImageView imageView = findViewById(R.id.imageView);
        if (imageUrl != null) {
            Picasso.get()
                    .load(imageUrl)
                    .centerCrop()
                    .fit()
                    .placeholder(R.color.colorPlaceholder)
                    .into(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share:
                startActivity(new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, link)
                        .setType("text/plain"));
                return true;
            case R.id.browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
