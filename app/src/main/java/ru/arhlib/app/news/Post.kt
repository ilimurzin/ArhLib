package ru.arhlib.app.news

import androidx.core.text.parseAsHtml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.arhlib.app.actual.ActualItem

@Serializable
class Post(
        val id: Int,
        val link: String,
        private val date: String?,
        private val title: Rendered,
        private val content: Rendered,
        private val excerpt: Rendered,
        @SerialName("_embedded")
        private val embedded: Embedded? = null
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
        return embedded?.featuredMedia?.first()?.mediaDetails?.sizes?.mediumLarge?.sourceUrl ?: ""
    }

    fun getSourceImageUrl(): String {
        return embedded?.featuredMedia?.first()?.sourceUrl ?: ""
    }

    @Serializable
    class Rendered(
            val rendered: String
    )

    @Serializable
    class Embedded(
            @SerialName("wp:featuredmedia")
            val featuredMedia: Array<Media>? = null
    ) {
        @Serializable
        class Media(
                @SerialName("source_url")
                val sourceUrl: String? = null,

                @SerialName("media_details")
                val mediaDetails: MediaDetails? = null
        ) {
            @Serializable
            class MediaDetails(
                    val sizes: Sizes? = null
            ) {
                @Serializable
                class Sizes(
                        @SerialName("medium_large")
                        val mediumLarge: Size? = null
                ) {
                    @Serializable
                    class Size(
                            @SerialName("source_url")
                            val sourceUrl: String? = null
                    )
                }
            }
        }
    }
}
