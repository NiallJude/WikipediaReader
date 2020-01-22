package api;

import com.nialljude.dev.api.WikipediaAPICaller;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.mockito.Mockito;

public class WikipediaAPICallerTest extends Mockito {

    private WikipediaAPICaller wikipediaAPICaller;
    private String host;
    private String scheme;
    private String path;
    private String customQuery;

    @Before
    public void setUp(){
        wikipediaAPICaller = new WikipediaAPICaller();
        host = "test.com";
        scheme = "https";
        path = "/test";
        customQuery = "test=test";

    }

    @Test
    public void testThatGetURIReturnsANonNullObject(){
        URI actual = null;
        try {
            actual = wikipediaAPICaller.getUri(scheme, host, path, customQuery);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        assertNotNull(actual);
    }

    @Test
    public void testThatGetURIReturnsACorrectURI(){
        URI actual = null;
        String expected = "https://test.com/test?test=test";
        try {
            actual = wikipediaAPICaller.getUri(scheme, host, path, customQuery);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        assertEquals(actual.toString(), expected);
    }

    @Test
    public void testThatAPICallRuns() throws ClientProtocolException, IOException {

        //given:
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        //and:
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        wikipediaAPICaller.runAPICall();
        File testResponse = new File("WikiResponse.json");
        boolean exists = testResponse.exists();
        assertTrue(exists);
    }
}
