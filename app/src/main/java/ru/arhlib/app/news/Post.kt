package ru.arhlib.app.news

import androidx.core.text.parseAsHtml
import com.google.gson.annotations.SerializedName
import ru.arhlib.app.actual.ActualItem

class Post(
        val id: Int,
        val link: String,
        private val date: String?,
        private val title: Rendered,
        private val content: Rendered,
        private val excerpt: Rendered,
        @SerializedName("_embedded")
        private val embedded: Embedded
) : ActualItem {
    fun getDateFormatted(): String {
        return DateFormatter.format(date) ?: ""
    }

    fun getTitle(): String {
        return title.rendered.parseAsHtml().toString().trim()
    }

    fun getContent(): String {
        return content.rendered
    }

    fun getExcerpt(): String {
        return excerpt.rendered.parseAsHtml().toString().trim()
    }

    fun getImageUrl(): String {
        return if (getMediumImageUrl().isNotEmpty()) {
            getMediumImageUrl()
        } else {
            getSourceImageUrl()
        }
    }

    private fun getMediumImageUrl(): String {
        return embedded.featuredMedia?.first()?.mediaDetails?.sizes?.mediumLarge?.sourceUrl ?: ""
    }

    fun getSourceImageUrl(): String {
        return embedded.featuredMedia?.first()?.sourceUrl ?: ""
    }

    class Rendered(
            val rendered: String
    )

    class Embedded(
            @SerializedName("wp:featuredmedia")
            val featuredMedia: Array<Media>?
    ) {
        class Media(
                @SerializedName("source_url")
                val sourceUrl: String?,

                @SerializedName("media_details")
                val mediaDetails: MediaDetails?
        ) {
            class MediaDetails(
                    val sizes: Sizes?
            ) {
                class Sizes(
                        @SerializedName("medium_large")
                        val mediumLarge: Size?
                )
            }

            class Size(
                    @SerializedName("source_url")
                    val sourceUrl: String?
            )
        }
    }
}
