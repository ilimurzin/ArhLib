package ru.arhlib.app.afisha

class Page(
        val id: Int,
        val title: Rendered,
        private val content: Rendered,
) {
    fun getRenderedContent(): String {
        return content.rendered
    }

    class Rendered(
            val rendered: String,
    )
}
