package ru.arhlib.app.afisha

class Page(
        val id: Int,
        val link: String,
        val title: Rendered,
        val content: Rendered,
) {
    fun getRenderedContent(): String {
        return content.rendered
    }

    class Rendered(
            val rendered: String,
    )
}
