package com.smirnov.bookreview.dao.impl;

import com.smirnov.bookreview.dao.ReviewDao;
import com.smirnov.bookreview.dao.mappers.ReviewMapper;
import com.smirnov.bookreview.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Calendar;
import java.util.List;

@Component
@Transactional
public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM REVIEWS WHERE REVIEW_ID = ?;";
    public static final String FIND_ALL_QUERY = "SELECT * FROM REVIEWS;";
    public static final String FIND_ALL_QUERY_WITH_LIMIT =
            "SELECT * FROM REVIEWS ORDER BY REVIEW_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    public static final String SAVE_QUERY = "INSERT INTO REVIEWS (RATING, REVIEW, " +
            "DATE_AND_TIME, USER_ID, BOOK_ID)" +
            " VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE REVIEWS\n" +
            "SET REVIEW_ID = ?, RATING = ?, REVIEW = ?," +
            "DATE_AND_TIME = ?, USER_ID = ?, BOOK_ID = ? " +
            "WHERE REVIEW_ID = ?;";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM REVIEWS WHERE REVIEW_ID = ?;";
    protected ReviewMapper reviewMapper;

    @Autowired
    public ReviewDaoImpl(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate,
                         ReviewMapper reviewMapper) {
        super(jdbcTemplate, transactionTemplate, FIND_BY_ID_QUERY, FIND_ALL_QUERY,
                SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
        this.reviewMapper = reviewMapper;
    }

    @Override
    public Review findByIntParam(String sql, Integer id) {

        return transactionTemplate.execute( status -> {
            Review review = jdbcTemplate.query(FIND_BY_ID_QUERY,
                    new Object[]{id}, reviewMapper)
                    .stream().findAny().orElse(null);
            review.setDate(getCalendar(id));

            return review;
        });
    }

    @Override
    protected List<Review> findAllEntities(String sql) {

        return transactionTemplate.execute( status -> {
            List<Review> reviews = jdbcTemplate.query(FIND_ALL_QUERY, reviewMapper);

            for(Review review : reviews) {
                review.setDate(getCalendar(review.getId()));
            }

            return reviews;
        });
    }

    @Override
    protected List<Review> findAllEntitiesWithinScope(String sql, int leftScope, int rightScope) {

        List<Review> reviews = jdbcTemplate.query(FIND_ALL_QUERY_WITH_LIMIT,
                new Object[] { leftScope, rightScope },
                new BeanPropertyRowMapper<>(Review.class));

        for(Review review : reviews) {
            review.setDate(getCalendar(review.getId()));
        }

        return reviews;
    }

    @Override
    protected void insert(String sql, Review review) {

        jdbcTemplate.update(SAVE_QUERY, review.getRating(), review.getBookReview(),
                review.getDate(), review.getUserId(), review.getBookId());
    }

    @Override
    protected void updateValue(String sql, Integer id, Review review) {

        jdbcTemplate.update(UPDATE_QUERY, review.getId(), review.getRating(), review.getBookReview(),
                review.getDate(), review.getUserId(), review.getBookId(), id);
    }

    @Override
    protected void deleteValue(String sql, Integer id) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcTemplate.update(DELETE_BY_ID_QUERY, id);
                } catch (Exception exception) {
                    status.setRollbackOnly();
                }
            }
        });
    }

    private Calendar getCalendar(int id) {
        String sql = "select DATE_AND_TIME from REVIEWS where REVIEW_ID = ?;";

        return jdbcTemplate.queryForObject(sql, new Object[] { id }, Calendar.class);
    }
}
