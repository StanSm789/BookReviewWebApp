package com.smirnov.bookreview.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {
    private int id;

    @NotEmpty(message = "Book name should not be empty")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    private String authorName;

    @Min(value = 0, message = "Year should be greater than 0")
    private int year;

    private String publisher;

    private String description;

    //@NotEmpty(message = "Recommended retail price should not be empty")
    private double recommendedRetailPrice;

    private String url;

    public Book() {
    }

    public Book(int id, String name, String authorName,
                int year, String publisher, String description,
                double recommendedRetailPrice, String url) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.publisher = publisher;
        this.description = description;
        this.recommendedRetailPrice = recommendedRetailPrice;
        this.url = url;
    }

}
