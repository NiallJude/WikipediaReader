package com.nialljude.dev.app;


import com.nialljude.dev.api.WikipediaAPICaller;
import com.nialljude.dev.files.JSONManager;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    private Map<Integer, String> finalMap;

    public static void main(String[] args) {
        // Starts the application.
        Main main = new Main();
        main.start();
    }

    /**
     * Follows entire thread of execution for the application.
     * Inline comments summarise each stage.
     *
     * @author Niall Jude Collins
     */
    private void start() {
        // Call Wikipedia API
        System.out.println("Querying Wikipedia for page contents...\n");
        runWikipediaAPICall();

        // Read JSON file and calculate words by requirements
        finalMap = getPageInformationFromJSON();

        // Display information
        displayInformation(finalMap);
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

    private Map<Integer, String> getPageInformationFromJSON() {
        // Read JSON and get 10 projects in a list
        JSONManager JSONManager = new JSONManager();
        finalMap = JSONManager.getMapOfFormattedValuesFromJSON();
        return finalMap;
    }

    private void displayInformation(Map<Integer, String> finalMap) {
        System.out.println("Top 5 Words: \n");
        for (Integer key : ((TreeMap<Integer, String>) finalMap).descendingKeySet()) {
            System.out.println("- " + key + " " + finalMap.get(key));
        }
    }
}
