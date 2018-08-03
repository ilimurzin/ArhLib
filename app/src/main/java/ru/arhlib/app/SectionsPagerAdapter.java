package ru.arhlib.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.arhlib.app.afisha.AfishaFragment;
import ru.arhlib.app.news.PostFragment;
import ru.arhlib.app.services.ServiceFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PostFragment();
            case 1:
                return new AfishaFragment();
            default:
                return new ServiceFragment();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
