package com.smirnov.bookreview.dao.mappers;

import com.smirnov.bookreview.models.Review;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ReviewMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {

        Review review = new Review();
        review.setId(resultSet.getInt("REVIEW_ID"));
        review.setRating(resultSet.getInt("RATING"));
        review.setBookReview(resultSet.getString("REVIEW"));
        //review.setDate((Calendar) resultSet.getObject("DATE_AND_TIME"));
        review.setUserId(resultSet.getInt("USER_ID"));
        review.setBookId(resultSet.getInt("BOOK_ID"));

        return review;
    }

}
