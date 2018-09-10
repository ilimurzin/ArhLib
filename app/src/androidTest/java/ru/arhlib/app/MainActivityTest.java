package ru.arhlib.app;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navigateThroughApplication() {
        onView(withText(R.string.afisha)).perform(click());
        onView(withText(R.string.services)).perform(click());
        onView(withText(R.string.contacts)).perform(click());
        pressBack();
        onView(withText(R.string.about)).perform(click());
        onView(withText(R.string.about_licenses)).perform(click());
        pressBack();
        pressBack();
        onView(withText(R.string.news)).perform(click());
    }

    @Test
    public void openPostInBrowser() {
        onView(withId(R.id.news_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText(R.string.post_open_in_browser)).perform(click());
    }
}
