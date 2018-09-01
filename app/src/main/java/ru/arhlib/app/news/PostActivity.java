package ru.arhlib.app.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

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
        //String imageUrl = intent.getStringExtra("imageUrl");

        String style = "<style>" + getString(R.string.style_css) + "</style>";
        content = style + "<h1>" + title + "</h1>" + content;
        //content = "<img width=\"100%\" src=\"" + imageUrl + "\">" + content; // temp

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadData(content, "text/html; charset=UTF-8", null);
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
