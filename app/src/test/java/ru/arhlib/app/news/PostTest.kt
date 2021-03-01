package ru.arhlib.app.news

import org.junit.Test

class PostTest {
    @Test
    fun testEquals() {
        assert(getNewPost() == getNewPost()) {
            "Two same posts are not equals. Check if all nested classes are data classes"
        }
    }

    private fun getNewPost(): Post {
        return Post(
                1,
                "link",
                null,
                Post.Rendered("title"),
                Post.Rendered("content"),
                Post.Rendered("excerpt"),
                Post.Embedded(listOf(Post.Embedded.Media(
                        null,
                        Post.Embedded.Media.MediaDetails(
                                Post.Embedded.Media.MediaDetails.Sizes(
                                        Post.Embedded.Media.MediaDetails.Sizes.Size("u")
                                )
                        )
                )))
        )
    }
}
