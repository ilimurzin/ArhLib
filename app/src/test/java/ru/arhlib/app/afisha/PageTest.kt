package ru.arhlib.app.afisha

import org.junit.Test

class PageTest {
    @Test
    fun `is equals work correct`() {
        assert(getNewPage() == getNewPage()) {
            "Two same page are not equals"
        }
    }

    private fun getNewPage(): Page {
        return Page(1, Page.Rendered("title"), Page.Rendered("content"))
    }
}
