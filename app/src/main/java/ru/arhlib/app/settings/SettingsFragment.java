package ru.arhlib.app.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import ru.arhlib.app.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
