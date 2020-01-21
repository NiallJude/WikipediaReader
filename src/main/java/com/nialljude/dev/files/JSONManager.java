package com.nialljude.dev.files;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

import com.nialljude.dev.wikipedia.Wikipedia;
import com.nialljude.dev.wikipedia.Pages;

public class JSONManager {

    private final String filepath = "Wikiresponse.json";
    private final String pageID = "21721040";
    private final int wordLength = 5;

    /**
     * Read the JSON File stored at ./Wikiresponse.json.
     *
     * @author Niall Jude Collins
     */
    public void readJSONFile() {
        // Get JSON String from the file
        String json = getJSONString();
        // Instantiate a page object
        Wikipedia page = new Wikipedia();
        // Now read the json String using the WikipediaApiHandler and Jackson
        ObjectMapper mapper = new ObjectMapper();
        // try/catch to secure IO Exception in Jackson

        try {
            page = mapper.readValue(json, Wikipedia.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Map<String, Pages> pagesMap = page.getQuery().getPages();
        System.out.println();
        System.out.println("Title: " + pagesMap.get(pageID).getTitle() + "\n");
        String content = pagesMap.get(pageID).getExtract();
        String[] words = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        countWords(words);

    }

    public void countWords(String[] words) {
        String[] splitWords = words;

        Map<String, Integer> occurrences = new HashMap<String, Integer>();

        for (String word : splitWords) {
            Integer oldCount = occurrences.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            if (word.length() >= wordLength-1) {
                occurrences.put(word, oldCount + 1);
            }
        }

        sortOccurrences(occurrences);
    }

    public void sortOccurrences(Map<String, Integer> occurrences) {
        Object[] array = occurrences.entrySet().toArray();
        Arrays.sort(array, new Comparator() {
            public int compare(Object object1, Object object2) {
                return ((Map.Entry<String, Integer>) object2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) object1).getValue());
            }
        });

        checkForMatchedFrequency(array);

        System.out.println("\nTop "+wordLength+" Words:\n");

        for (int loop=0; loop<5; loop++){
            System.out.println("- "+array[loop]);
        }

    }

    private void checkForMatchedFrequency(Object[] array) {
        int previousValue = 0;
        String previousKey = "";
        Map <String, Integer> finalMap = new HashMap<>();

        for (Object entry : array) {

            if ( finalMap.size() < 15) {
                if (((Map.Entry<String, Integer>) entry).getValue() == previousValue) {

                    String concatenatedMatch = previousKey + ", " + ((Map.Entry<String, Integer>) entry).getKey();

                    System.out.println(concatenatedMatch);

                    finalMap.put(concatenatedMatch, previousValue);
                } else {
                    finalMap.put(((Map.Entry<String, Integer>) entry).getKey(), ((Map.Entry<String, Integer>) entry).getValue());
                }
            }

            previousValue = ((Map.Entry<String, Integer>) entry).getValue();
            previousKey = ((Map.Entry<String, Integer>) entry).getKey();
        }

        System.out.println(finalMap.toString());
    }

    /**
     * Read the final file String contents into a string.
     * Return the results as a String.
     *
     * @return StringBuilder to String.
     * @author Niall Jude Collins
     */
    private String getJSONString() {
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
