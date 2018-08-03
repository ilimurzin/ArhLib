package ru.arhlib.app.services;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import ru.arhlib.app.BuildConfig;
import ru.arhlib.app.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String version = getString(R.string.about_version) + " " + BuildConfig.VERSION_NAME;
        TextView v = findViewById(R.id.version);
        v.setText(version);

        OssLicensesMenuActivity.setActivityTitle(getString(R.string.about_licenses));
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

    public void contactDeveloper(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_author_email))));
    }

    public void showLicences(View v) {
        startActivity(new Intent(this, OssLicensesMenuActivity.class));
    }
}
