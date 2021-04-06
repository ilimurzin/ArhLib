package ru.arhlib.app.actual

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import ru.arhlib.app.R
import ru.arhlib.app.data.Webservice
import ru.arhlib.app.news.Post

class ActualFragmentTest {
    private val webservice = mockk<Webservice>()

    init {
        Webservice.instance = webservice
    }

    @Test
    fun testPostOpening() {
        coEvery { webservice.getActualLinks() } returns listOf(
                ActualLink("😘", "title", "description", "link"),
        )
        coEvery { webservice.getStickyPosts() } returns listOf(
                Post(
                        1,
                        "no",
                        "",
                        Post.Rendered("post title"),
                        Post.Rendered("post content"),
                        Post.Rendered("post excerpt"),
                        null
                ),
        )

        launchFragmentInContainer<ActualFragment>()

        onView(withId(R.id.errorText)).check(doesNotExist())
        onView(withText("post excerpt")).perform(click())
        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.post_title)).check(matches(withText("post title")))
    }

    @Test
    fun testNetworkLoadAndError() {
        coEvery { webservice.getActualLinks() } coAnswers {
            CompletableDeferred<List<ActualLink>>().await()
        }
        coEvery { webservice.getStickyPosts() } coAnswers {
            CompletableDeferred<List<Post>>().await()
        }

        launchFragmentInContainer<ActualFragment>()

        onView(withId(R.id.progressBar)).check(matches(isCompletelyDisplayed()))

        coEvery { webservice.getActualLinks() } throws Exception()
        coEvery { webservice.getStickyPosts() } throws Exception()

        onView(withId(R.id.swipeRefresh)).perform(swipeDown())

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retryBlock)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun testRetryAfterNetworkError() {
        coEvery { webservice.getActualLinks() } throws Exception()
        coEvery { webservice.getStickyPosts() } throws Exception()

        launchFragmentInContainer<ActualFragment>()

        onView(withId(R.id.retryBlock)).check(matches(isCompletelyDisplayed()))

        coEvery { webservice.getActualLinks() } returns listOf(
                ActualLink("", "actual link", "", ""),
        )
        coEvery { webservice.getStickyPosts() } returns emptyList()

        onView(withId(R.id.retryButton)).perform(click())

        onView(withId(R.id.retryBlock)).check(matches(not(isDisplayed())))
        onView(withText("actual link")).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun testEmptyActual() {
        coEvery { webservice.getActualLinks() } returns emptyList()
        coEvery { webservice.getStickyPosts() } returns emptyList()

        launchFragmentInContainer<ActualFragment>()

        onView(withText(R.string.empty_actual)).check(matches(isCompletelyDisplayed()))
    }
}
