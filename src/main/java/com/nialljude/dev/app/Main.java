package com.nialljude.dev.app;


import com.nialljude.dev.api.WikipediaAPICaller;
import com.nialljude.dev.files.JSONManager;

import java.io.IOException;
import java.util.logging.*;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    // A reference to the final map
    // for display of formatted information
    private Map<Integer, String> finalMap;
    // Logging variables.
    private static Logger logger = Logger.getLogger(ApplicationLogger.class.getName());
    private static ApplicationLogger applicationLogger;

    /**
     * A main method
     * @param args
     */
    public static void main(String[] args) {
        // Starts the application.
        Main main = new Main();
        applicationLogger = new ApplicationLogger();
        // Set up logger
        applicationLogger.setup();
        logger = applicationLogger.getLogger();
        main.start();
    }

    /**
     * Follows entire thread of execution for the application.
     * Inline comments summarise each stage.
     *
     * @author Niall Jude Collins
     */
    private void start() {
        logger.info("Starting project run...");
        // Call Wikipedia API
        logger.info("Calling the Wikipedia API");
        runWikipediaAPICall();

        logger.info("Getting the formatted page information from JSON");
        // Read JSON file and calculate words by requirements
        finalMap = getPageInformationFromJSON();

        logger.info("Displaying the formatted information...");
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

    /**
     * A method to get and return the processed
     * data from JSON in the format specified
     * @return finalMap - The final map, ready for display
     */
    private Map<Integer, String> getPageInformationFromJSON() {
        // Read JSON and get 10 projects in a list
        JSONManager JSONManager = new JSONManager();
        finalMap = JSONManager.getMapOfFormattedValuesFromJSON();
        return finalMap;
    }

    /**
     * Print the final Map to console for users
     * specifically the five most commonly occurring words,
     * their occurrences and any words with the same frequency
     * displayed on the same line (comma separated)
     *
     * @param finalMap
     */
    private void displayInformation(Map<Integer, String> finalMap) {
        System.out.println("Top 5 Words: \n");
        for (Integer key : ((TreeMap<Integer, String>) finalMap).descendingKeySet()) {
            logger.info("Displaying the following key to the user: "+finalMap.get(key));
            System.out.println("- " + key + " " + finalMap.get(key));
        }
    }
}
