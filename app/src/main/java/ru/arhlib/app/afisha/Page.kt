package ru.arhlib.app.afisha

import kotlinx.serialization.Serializable

@Serializable
class Page(
        val id: Int,
        private val title: Rendered,
        private val content: Rendered,
) {
    fun getRenderedTitle(): String {
        return title.rendered
    }

    fun getRenderedContent(): String {
        return content.rendered
    }

    @Serializable
    class Rendered(
            val rendered: String,
    )
}
