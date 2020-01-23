package com.nialljude.dev.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nialljude.dev.app.Main;
import com.nialljude.dev.wikipedia.Pages;
import com.nialljude.dev.wikipedia.Wikipedia;
import lombok.NonNull;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class JSONManager {

    private final int wordLength = 4;
    // Instantiate logger
    private static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Read the JSON File stored at ./Wikiresponse.json.
     * Format information in accordance with the requirements
     * and return the results.
     *
     * @return finalMap - The final Map of sorted values ready to be displayed.
     * @author Niall Jude Collins
     */
    public Map<Integer, String> getMapOfFormattedValuesFromJSON() {
        // Vars
        Map<String, Integer> sortedMap;
        Map<String, Integer> occurrences;
        Map<Integer, String> finalMap;
        String title;
        logger.info("Fetching properties...");
        PropertyManager propertyManager = new PropertyManager();
        Properties properties = propertyManager.getProperties();

        logger.info("Instantiating a Wikipedia page POJO object");
        // Instantiate a Wikipedia page POJO object
        Wikipedia wikipediaPage = new Wikipedia();
        logger.info("Using Jackson object mapper to instantiate");
        // Use Jackson object mapper to instantiate
        wikipediaPage = getWikipediaPageObjectFromJackson(wikipediaPage, properties.getProperty("page.filePath"));
        logger.info("Creating a map of the Pages and find info we want by pageID");
        // Create a map of the Pages and find info we want by pageID
        Map<String, Pages> pagesMap = wikipediaPage.getQuery().getPages();
        title = pagesMap.get(properties.getProperty("page.pageID")).getTitle();
        logger.info("Printing the page Title: " + title);
        // Print page Title for the user
        printTitle(title);
        logger.info("PageID: " + properties.getProperty("page.pageID"));
        // Get the raw content from Pages.[PAGEID].Extract
        String content = pagesMap.get(properties.getProperty("page.pageID")).getExtract();
        logger.info("Cleaning the raw content via regular expression...");
        // Clean content via regular expression
        String[] words = getStringsCleanedForComparison(content);
        logger.info("Counting the occurrences of each word...");
        // Count the occurrences of each word
        occurrences = countWordOccurrences(words);
        logger.info("Sorting the map by occurrences...");
        // Sort by occurrences
        sortedMap = sortOccurrences(occurrences);
        logger.info("Checking and formatting words which match frequency...");
        // Check for words with matched frequency (and format if found)
        int wordsToDisplay = Integer.parseInt(properties.getProperty("page.wordsToDisplay"));
        finalMap = checkForMatchedFrequency(sortedMap, wordsToDisplay);
        logger.info("Returning finalMap for display.\n" + finalMap.toString());
        return finalMap;
    }

    private Wikipedia getWikipediaPageObjectFromJackson(Wikipedia wikipediaPage, String filepath) {
        ObjectMapper mapper = new ObjectMapper();

        // Get JSON String from the file
        String json = getJSONString(filepath);

        // try/catch to secure IO Exception risk in Jackson
        try {
            wikipediaPage = mapper.readValue(json, Wikipedia.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return wikipediaPage;
    }

    private void printTitle(String title) {
        System.out.println("\nTitle: " + title + "\n");
    }

    @NonNull
    private String[] getStringsCleanedForComparison(String content) {
        // Regex to delete all punctuation, numbers and Upper Casing for comparisons
        return content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
    }

    public Map<String, Integer> countWordOccurrences(String[] words) {
        String[] splitWords = words;

        Map<String, Integer> occurrences = new HashMap<String, Integer>();

        for (String word : splitWords) {
            Integer oldCount = occurrences.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            if (word.length() >= wordLength) {
                occurrences.put(word, oldCount + 1);
            }
        }
        return occurrences;
    }

    private Map<String, Integer> sortOccurrences(Map<String, Integer> occurrences) {

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        occurrences.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        return sortedMap;
    }

    /**
     * A method to find the most frequently occurring words
     * with words of the same frequency appearing comma
     * separated on the same line.
     *
     * @param sortedMap - The Tree
     * @return finalMap - The final map, of the five most common words.
     * @author Niall Jude Collins
     */
    private Map<Integer, String> checkForMatchedFrequency(Map<String, Integer> sortedMap, int wordsToDisplay) {
        Map<Integer, String> finalMap = new TreeMap<>();

        // Display sorted words with words of the same frequency
        // appearing on the same line (comma separated)
        sortedMap.forEach((key, value) -> {
            if (finalMap.containsKey(value) && finalMap.size() <= wordsToDisplay) {
                String concatenatedMatch = finalMap.get(value);
                finalMap.put(value, concatenatedMatch += ", " + key);
            } else if (finalMap.size() < wordsToDisplay) {
                finalMap.put(value, key);
            }
        });

        return finalMap;
    }

    /**
     * Read the final file String contents into a string. Return the results as a
     * String.
     *
     * @return StringBuilder to String.
     * @author Niall Jude Collins
     */
    private String getJSONString(String filepath) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream(filepath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}