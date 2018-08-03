package ru.arhlib.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ru.arhlib.app.news.Post;
import ru.arhlib.app.news.PostActivity;
import ru.arhlib.app.news.PostFragment;
import ru.arhlib.app.services.AboutActivity;
import ru.arhlib.app.services.ContactsActivity;
import ru.arhlib.app.services.Service;
import ru.arhlib.app.services.ServiceFragment;
import ru.arhlib.app.settings.SettingsActivity;

public class MainActivity
        extends AppCompatActivity
        implements ServiceFragment.OnListFragmentInteractionListener,
        PostFragment.OnListFragmentInteractionListener {

    public static Snackbar webserviceErrorSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        webserviceErrorSnackbar = Snackbar.make(findViewById(R.id.main_content),
                R.string.webservice_error, Snackbar.LENGTH_LONG);
        //webserviceErrorSnackbar.setAction(R.string.snackbar_update_button, new SnackbarListener());
    }

    @Override
    public void onListFragmentInteraction(Service item) {
        if (item.name == R.string.catalog) {
            MyWebViewClient.openUrl(this, getString(R.string.catalog_url));
        } else if (item.name == R.string.contacts) {
            startActivity(new Intent(this, ContactsActivity.class));
        } else if (item.name == R.string.ask_lawyer) {
            MyWebViewClient.openUrl(this, getString(R.string.lawyer_url));
        } else if (item.name == R.string.prolongation) {
            MyWebViewClient.openUrl(this, getString(R.string.prolongation_url));
        } else if (item.name == R.string.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (item.name == R.string.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
    }

    @Override
    public void onListFragmentInteraction(Post post) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("title", post.getTitle());
        intent.putExtra("content", post.getContent());
        //intent.putExtra("imageUrl", post.getImageUrl());
        startActivity(intent);
    }
}
