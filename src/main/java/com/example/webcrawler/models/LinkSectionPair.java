package com.example.webcrawler.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LinkSectionPair {
    private String link;
    private String section;
}
