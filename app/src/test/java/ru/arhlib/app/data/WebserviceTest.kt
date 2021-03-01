package ru.arhlib.app.data

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Test

class WebserviceTest {
    private val mockWebServer = MockWebServer()
    private val webservice = Webservice.create(mockWebServer.url("/"))

    @Test
    fun `is actual links decoded correctly`() {
        val json = "[{\"emoji\":\"\\ud83d\\udda5\\ufe0f\",\"title\":\"Запись без описания\",\"description\":\"\",\"link\":\"https:\\/\\/arhlib.ru\\/e-events\\/\"},{\"id\":2,\"emoji\":\"\\ud83d\\udcda\",\"title\":\"Online-\\u0430\\u043a\\u0446\\u0438\\u044f \\u00ab\\u0410\\u0432\\u0442\\u043e\\u0440\\u0438\\u0442\\u0435\\u0442 \\u0438\\u043c\\u0435\\u043d\\u0438\\u00bb\",\"description\":\"\\u0423\\u0441\\u043f\\u0435\\u0448\\u043d\\u044b\\u0439 \\u0447\\u0435\\u043b\\u043e\\u0432\\u0435\\u043a \\u043f\\u0440\\u0435\\u043a\\u0440\\u0430\\u0441\\u043d\\u043e \\u0437\\u043d\\u0430\\u0435\\u0442, \\u043a\\u0430\\u043a \\u0432\\u0430\\u0436\\u043d\\u043e \\u0447\\u0438\\u0442\\u0430\\u0442\\u044c.\",\"link\":\"https:\\/\\/arhlib.ru\\/interesno\\/avtoritet-imeni\\/\"}]"
        val response = MockResponse()
                .setResponseCode(200)
                .setBody(json)
        mockWebServer.enqueue(response)
        runBlocking {
            val actualLinks = webservice.getActualLinks()
            assertEquals("", actualLinks[0].description)
        }
    }

    @Test
    fun `is empty list decoded correctly`() {
        val json = "[]"
        val response = MockResponse()
                .setResponseCode(200)
                .setBody(json)
        mockWebServer.enqueue(response)
        runBlocking {
            val posts = webservice.getPosts(1)
            assert(posts.isEmpty())
        }
    }

    @Test
    fun `is posts decoded correctly`() {
        val json = """
            [
              {
                "id": 70893,
                "date": "2021-03-01T13:30:31",
                "date_gmt": "2021-03-01T10:30:31",
                "guid": {
                  "rendered": "https://arhlib.ru/?p=70893"
                },
                "modified": "2021-03-01T09:48:49",
                "modified_gmt": "2021-03-01T06:48:49",
                "slug": "poeziya-russkogo-severa",
                "status": "publish",
                "type": "post",
                "link": "https://arhlib.ru/2021/03/poeziya-russkogo-severa/",
                "title": {
                  "rendered": "\u041f\u043e\u044d\u0437\u0438\u044f \u0420\u0443\u0441\u0441\u043a\u043e\u0433\u043e \u0421\u0435\u0432\u0435\u0440\u0430"
                },
                "content": {
                  "rendered": "some html",
                  "protected": false
                },
                "excerpt": {
                  "rendered": "some html",
                  "protected": false
                },
                "author": 4,
                "featured_media": 70894,
                "comment_status": "closed",
                "ping_status": "closed",
                "sticky": false,
                "template": "",
                "format": "standard",
                "meta": [],
                "categories": [
                  7,
                  11
                ],
                "tags": []
              }
            ]
        """.trimIndent()
        val response = MockResponse()
                .setResponseCode(200)
                .setBody(json)
        mockWebServer.enqueue(response)
        runBlocking {
            val posts = webservice.getPosts(1)
            assertEquals(70893, posts[0].id)
        }
    }

    @Test
    fun `is page title decoded correctly`() {
        val pageId = 2756
        val json = """
            {
              "id": $pageId,
              "date": "2014-01-28T10:49:37",
              "date_gmt": "2014-01-28T06:49:37",
              "guid": {
                "rendered": "http://arhlib.ru/?page_id=2756"
              },
              "modified": "2019-12-16T14:09:37",
              "modified_gmt": "2019-12-16T11:09:37",
              "slug": "kontaktyi",
              "status": "publish",
              "type": "page",
              "link": "https://arhlib.ru/vsyo-o-tsbs/kontaktyi/",
              "title": {
                "rendered": "\u041f\u043e\u044d\u0437\u0438\u044f \u0420\u0443\u0441\u0441\u043a\u043e\u0433\u043e \u0421\u0435\u0432\u0435\u0440\u0430"
              },
              "content": {
                "rendered": "<p>some html</p>\n",
                "protected": false
              },
              "excerpt": {
                "rendered": "<p>some html</p>\n",
                "protected": false
              },
              "author": 1,
              "featured_media": 0,
              "parent": 7,
              "menu_order": 5,
              "comment_status": "closed",
              "ping_status": "closed",
              "template": "",
              "meta": []
            }
        """.trimIndent()
        val response = MockResponse()
                .setResponseCode(200)
                .setBody(json)
        mockWebServer.enqueue(response)
        runBlocking {
            val page = webservice.getPage(pageId)
            assertEquals(pageId, page.id)
            assertEquals("Поэзия Русского Севера", page.getRenderedTitle())
        }
    }
}
