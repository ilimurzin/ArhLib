package ru.arhlib.app.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import ru.arhlib.app.actual.Actual;

import static org.junit.Assert.assertEquals;

public class WebserviceTest {

    private MockWebServer mockWebServer;
    private Webservice webservice;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        webservice = Api.getInstance(mockWebServer.url("/"));
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getActual() throws IOException {
        String json = "[{\"emoji\":\"\\ud83d\\udda5\\ufe0f\",\"title\":\"Запись без описания\",\"description\":\"\",\"link\":\"https:\\/\\/arhlib.ru\\/e-events\\/\"},{\"id\":2,\"emoji\":\"\\ud83d\\udcda\",\"title\":\"Online-\\u0430\\u043a\\u0446\\u0438\\u044f \\u00ab\\u0410\\u0432\\u0442\\u043e\\u0440\\u0438\\u0442\\u0435\\u0442 \\u0438\\u043c\\u0435\\u043d\\u0438\\u00bb\",\"description\":\"\\u0423\\u0441\\u043f\\u0435\\u0448\\u043d\\u044b\\u0439 \\u0447\\u0435\\u043b\\u043e\\u0432\\u0435\\u043a \\u043f\\u0440\\u0435\\u043a\\u0440\\u0430\\u0441\\u043d\\u043e \\u0437\\u043d\\u0430\\u0435\\u0442, \\u043a\\u0430\\u043a \\u0432\\u0430\\u0436\\u043d\\u043e \\u0447\\u0438\\u0442\\u0430\\u0442\\u044c.\",\"link\":\"https:\\/\\/arhlib.ru\\/interesno\\/avtoritet-imeni\\/\"}]";

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody(json);

        mockWebServer.enqueue(response);

        List<Actual> actual = webservice.getActual().execute().body();

        assertEquals(0, actual.get(0).id);
        assertEquals("", actual.get(0).description);
    }
}
