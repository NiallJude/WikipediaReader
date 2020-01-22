package com.nialljude.dev.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pages {

    @JsonProperty("pageid")
    private int pageID;

    @JsonProperty("title")
    private String title;

    @JsonProperty("extract")
    private String extract;

}