package com.nialljude.dev.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Map;

import com.nialljude.dev.wikipedia.Wikipedia;
import com.nialljude.dev.wikipedia.Pages;

public class JSONManager {

    private final String filepath = "Wikiresponse.json";

    /**
     * Return a list of all projectsToSearch (usually 10 items long).
     *
     * @author Niall Jude Collins
     * @return projectsToSearch list.
     */
    public void readJSONFile() throws IOException {
        // Get JSON String from the file
        String json = getJSONString();
        // Now read the json String using the WikipediaApiHandler and Jackson
        ObjectMapper mapper = new ObjectMapper();
        Wikipedia page = mapper.readValue(json, Wikipedia.class);
        Map<String, Pages> pagesMap = page.getQuery().getPages();
        System.out.println();
        System.out.println("Title: "+pagesMap.get("21721040").getTitle()+"\n");
        String content = pagesMap.get("21721040").getExtract();
        System.out.println("Content: "+content);

    }

    /**
     * Read the Wikipedia.json file.
     * Return the results as a String.
     *
     * @author Niall Jude Collins
     * @return StringBuilder to String.
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
