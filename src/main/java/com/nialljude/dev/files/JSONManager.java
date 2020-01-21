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

    }

    private void checkForMatchedFrequency(Object[] array) {
        int previousValue = 0;
        int valueToAdd;
        int runs=0;
        Boolean removalHappened = false;
        String previousConcatenatedMatch;
        String stringToAdd;
        String concatenatedMatch;
        ArrayList<String> finalList = new ArrayList<String>();

        for (Object entry : array) {
            if ( finalList.size() < 8) {
                if (removalHappened) {
                    runs--;
                    removalHappened = false;
                }
                if (((Map.Entry<String, Integer>) entry).getValue() == previousValue) {
                    previousConcatenatedMatch = finalList.get(runs-1);
                    finalList.remove(runs-1);
                    String currentEntry = ((Map.Entry<String, Integer>) entry).getKey();
                    concatenatedMatch = previousConcatenatedMatch+", "+currentEntry;
                    finalList.add(concatenatedMatch);
                    runs++;
                    removalHappened = true;
                } else {
                    valueToAdd = ((Map.Entry<String, Integer>) entry).getValue();
                    stringToAdd = valueToAdd+" "+((Map.Entry<String, Integer>) entry).getKey();
                    finalList.add(stringToAdd);
                    runs++;
                }
            }
            previousValue = ((Map.Entry<String, Integer>) entry).getValue();
        }
        System.out.println("Top 8 Words: \n");
        for (String item : finalList){
            System.out.println(item.toString());
        }
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
