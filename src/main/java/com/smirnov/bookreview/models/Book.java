package com.smirnov.bookreview.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(setterPrefix = "with")
@ToString
@EqualsAndHashCode
public class Book {
    private final int id;
    private final String name;
    private final String authorName;
    private final int year;
    private final String publisher;
    private final String description;
    private final double recommendedRetailPrice;
    private final String url;
}