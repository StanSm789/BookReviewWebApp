package com.smirnov.bookreview.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Review {
    private int id;

    @NotEmpty(message = "Rating should not be empty")
    @Min(value = 1, message = "Rating should be greater than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int rating;

    @NotEmpty(message = "Book review should not be empty")
    private String bookReview;

    private Calendar date;

    private int userId;

    private int bookId;

    public Review() {
    }

    public Review(int id, int rating, String bookReview, Calendar date, int userId, int bookId) {
        this.id = id;
        this.rating = rating;
        this.bookReview = bookReview;
        this.date = date;
        this.userId = userId;
        this.bookId = bookId;
    }
    
}
