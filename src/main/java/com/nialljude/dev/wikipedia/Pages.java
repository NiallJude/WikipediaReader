package com.nialljude.dev.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pages {

    @JsonProperty("pageid")
    private int pageID;

    @JsonProperty("title")
    private String title;

    @JsonProperty("extract")
    private String extract;

    public Pages(Integer pageID) {
        this.pageID = pageID;
    }

    public Pages() {
    }

    public int getPageID() {
        return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nContent: " + extract;
    }
}
