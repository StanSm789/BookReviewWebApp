package com.smirnov.bookreview.models;

import java.util.Calendar;
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
public class Review {
    private final int id;
    private final int rating;
    private final String bookReview;
    private Calendar date;
    private final int userId;
    private final int bookId;
}

