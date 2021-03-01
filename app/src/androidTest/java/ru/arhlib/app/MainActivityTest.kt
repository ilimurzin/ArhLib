package ru.arhlib.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun navigateThroughApplication() {
        swipeRight()
        swipeRight()
        onView(withText(R.string.services)).perform(click())
        onView(withText(R.string.contacts)).perform(click())
        pressBack()
        onView(withText(R.string.about)).perform(click())
        onView(withText(R.string.about_licenses)).perform(click())
        pressBack()
        pressBack()
        onView(withText(R.string.afisha)).perform(click())
        pressBack()
        onView(withText(R.string.news)).perform(click())
    }
}
