package com.smirnov.bookreview.dao.impl;

import com.smirnov.bookreview.dao.BookDao;
import com.smirnov.bookreview.dao.mappers.BookMapper;
import com.smirnov.bookreview.dao.mappers.UserMapper;
import com.smirnov.bookreview.models.Book;
import com.smirnov.bookreview.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.List;

@Component
@Transactional
public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM BOOKS WHERE BOOK_ID = ?;";
    public static final String FIND_ALL_QUERY = "SELECT * FROM BOOKS;";
    public static final String FIND_ALL_QUERY_WITH_LIMIT =
            "SELECT * FROM BOOKS ORDER BY BOOK_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    public static final String SAVE_QUERY = "INSERT INTO BOOKS (BOOK_NAME, AUTHOR_NAME, " +
            "PUBLISHING_YEAR, PUBLISHER, DESCRIPTION, RETAIL_PRICE, URL)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE BOOKS\n" +
            "SET BOOK_ID = ?, BOOK_NAME = ?, AUTHOR_NAME = ?," +
            "PUBLISHING_YEAR = ?, PUBLISHER = ?, DESCRIPTION = ?," +
            "RETAIL_PRICE = ?, URL = ?\n" +
            "WHERE BOOK_ID = ?;";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM BOOKS WHERE BOOK_ID = ?;";
    protected BookMapper bookMapper;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate,
                       BookMapper bookMapper) {
        super(jdbcTemplate, transactionTemplate, FIND_BY_ID_QUERY, FIND_ALL_QUERY,
                SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
        this.bookMapper = bookMapper;
    }

    @Override
    public Book findByIntParam(String sql, Integer id) {

        return transactionTemplate.execute( status -> {
            Book book = jdbcTemplate.query(FIND_BY_ID_QUERY,
                    new Object[]{id}, bookMapper)
                    .stream().findAny().orElse(null);

            return book;
        });
    }

    @Override
    protected List<Book> findAllEntities(String sql) {

        return transactionTemplate.execute( status -> {
            List<Book> books = jdbcTemplate.query(FIND_ALL_QUERY, bookMapper);

            return books;
        });
    }

    @Override
    protected List<Book> findAllEntitiesWithinScope(String sql, int leftScope, int rightScope) {

            return jdbcTemplate.query(FIND_ALL_QUERY_WITH_LIMIT, new Object[] { leftScope, rightScope },
                    new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    protected void insert(String sql, Book book) {

        jdbcTemplate.update(SAVE_QUERY, book.getName(), book.getAuthorName(), book.getYear(), book.getPublisher(),
                book.getDescription(), book.getRecommendedRetailPrice(), book.getUrl());
    }

    @Override
    protected void updateValue(String sql, Integer id, Book book) {

        jdbcTemplate.update(UPDATE_QUERY, book.getId(), book.getName(), book.getAuthorName(),
                book.getYear(), book.getPublisher(), book.getDescription(),
                book.getRecommendedRetailPrice(), book.getUrl());
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

}