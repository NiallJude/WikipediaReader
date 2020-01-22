package com.nialljude.dev.api;

import com.nialljude.dev.app.ApplicationLogger;
import com.nialljude.dev.app.Main;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class WikipediaAPICaller {

    private final String fileName = "WikiResponse.json";
    private final String pageID = "21721040";

    /**
     * Call the Wikipedia API - construct the URI in stages.
     * Searches for a page contents by pageID
     *
     * @author - Niall Jude Collins
     */
    public void runAPICall() {
        ApplicationLogger applicationLogger = new ApplicationLogger();
        Logger logger = applicationLogger.getLogger();
        // Initialise a closable HTTPClient and new StringBuffer
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer result;
        logger.info("Inititalised a HTTPClient and StringBuffer");

        // Variables to build Wiki query
        // To match 'https://en.wikipedia.org/w/api.php?action=query&prop=extracts&pageids=21721040&explaintext&format=json'
        String scheme = "https";
        String host = "en.wikipedia.org";
        String path = "/w/api.php";
        String customQuery = "action=query&prop=extracts&pageids=[PLACEHOLDER]&explaintext&format=json";
        customQuery = customQuery.replace("[PLACEHOLDER]", pageID);

        logger.info("Setting the following variables for URI assembly:\n" +
                "Scheme: "+scheme
                + "\nHost: "+host
                + "\nPath: "+path
                + "\nCustom Query: "+customQuery);

        // Get StringBuffer result from API Call
        result = getAPIResponse(httpClient, scheme, host, path, customQuery);

        // Write response to a file in JSON
        writeResponse(result, fileName);
        // Close Client Resource
        closeResource(httpClient);
    }

    /**
     * Close the HttpClient resource.
     * It will not be needed again.
     *
     * @author - Niall Jude Collins
     */
    private void closeResource(CloseableHttpClient httpClient) {
        try {
            httpClient.close();
        } catch (IOException ex) {
            System.out.println("Error closing httpClient");
            ex.printStackTrace();
        }
    }

    /**
     * Write the String Buffer content to a file.
     * This will be analysed and converted into
     * Project objects.
     *
     * @param result   - Results of the API Call (to write).
     * @param fileName - The name of the file this will create.
     * @author - Niall Jude Collins
     */
    private void writeResponse(StringBuffer result, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            writer.append(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Call the Wikipedia API for the content of a given page.
     * Request JSON format.
     * Captures the response and returns it.
     *
     * @param httpclient - The HTTPClient from Apache that will handle the call.
     * @param scheme     - HTTP or HTTPS?
     * @param host       - The website (en.wikipedia.org)
     * @param path       - /w/api.php? - The sub directories of en.wikipedia.org to query
     * @return result - The StringBuffer output of the response.
     * @throws URISyntaxException - URI May not have been correctly set up.
     * @throws IOException        - The Read/Write of the data may fail (unlikely).
     * @throws RuntimeException   - Thrown in the result of a non-200 HTTP code.
     * @author - Niall Jude Collins
     */
    private StringBuffer getAPIResponse(CloseableHttpClient httpclient, String scheme, String host, String path, String customQuery) {
        // Initialise response and result
        HttpResponse response;
        StringBuffer result = new StringBuffer();

        try {
            URI uri = getUri(scheme, host, path, customQuery);
            System.out.println("URL:\n" + uri);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("accept", "application/json");
            response = httpclient.execute(httpGet);

            // Verify the healthy error code
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            // Convert the inputStream object to a StringBuffer
            result = processResponse(response);
        } catch (URISyntaxException ex) {
            printResponse("Invalid URI.");
            ex.printStackTrace();
        } catch (IOException ex) {
            printResponse("Apache encountered an IO Exception.");
            ex.printStackTrace();
        }
        // return the response body
        return result;
    }

    /**
     * Process the response and return.
     *
     *
     * @param response - The HTTP Response from the API.
     * @throws IOException
     * @author - Niall Jude Collins
     */
    private StringBuffer processResponse(HttpResponse response) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;

        while ((line = rd.readLine()) != null)
            result.append(line);

        return result;
    }

    /**
     * Process the response and return.
     *
     * @param scheme      - HTTP or HTTPS?
     * @param host        - The website (en.wikipedia.org)
     * @param path        - /w/api.php? - The sub directories of en.wikipedia.org to query
     * @param customQuery - The query block after the scheme, host & path
     * @return URI - The object with which to query the API.
     * @throws URISyntaxException
     * @author - Niall Jude Collins
     */
    public URI getUri(String scheme, String host, String path, String customQuery) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setCustomQuery(customQuery)
                .build();
    }

    /**
     * Simply print a given String.
     *
     * @param string - String to print.
     * @author - Niall Jude Collins
     */
    private void printResponse(String string) {
        System.out.println(string);
    }


}
