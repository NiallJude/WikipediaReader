package com.nialljude.dev.app;


import java.io.IOException;
import com.nialljude.dev.api.WikipediaAPICaller;
import com.nialljude.dev.files.JSONManager;

public class Main {

    public static void main(String[] args) {
        // Starts the application.
        Main main = new Main();
        try {
        main.start();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Follows entire thread of execution for the application.
     * Inline comments summarise each stage.
     *
     * @author Niall Jude Collins
     */
    private void start() throws IOException {
        // Call GitHub API
        System.out.println("Querying Wikipedia for page contents...\n");
        runWikipediaAPICall();
        // Read JSON file
        getPageInformationFromJSON();
    }

    private void getPageInformationFromJSON() throws IOException {
        // Read JSON and get 10 projects in a list
        JSONManager JSONManager = new JSONManager();
        JSONManager.readJSONFile();
    }

    /**
     * Run the Wikipedia API call to get the content of a Wiki page.
     * No return - but this will place Wikiresponse.json raw output
     * on the root of the project for ingestion and conversion
     * to Java Objects.
     *
     * @author Niall Jude Collins
     */
    private void runWikipediaAPICall() {
        // Call Wikipedia API
        WikipediaAPICaller api = new WikipediaAPICaller();
        api.runAPICall();
    }
}