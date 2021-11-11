package com.smirnov.bookreview.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public double getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecommendedRetailPrice(double recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", recommendedRetailPrice=" + recommendedRetailPrice +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                year == book.year &&
                Double.compare(book.recommendedRetailPrice, recommendedRetailPrice) == 0 &&
                Objects.equals(name, book.name) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(description, book.description) &&
                Objects.equals(url, book.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorName, year, publisher, description, recommendedRetailPrice, url);
    }
}