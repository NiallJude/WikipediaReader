package com.nialljude.dev.wikipedia;

import java.util.Map;

public class Query {

    private Map<String, Pages> pages;

    public Query() {
    }

    public Query(Map<String, Pages> pages) {
        this.pages = pages;
    }

    public Map<String, Pages> getPages() {
        return pages;
    }

    public void setPages(Map<String, Pages> pages) {
        this.pages = pages;
    }
}
