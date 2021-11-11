package com.smirnov.bookreview.dao.mappers;

import com.smirnov.bookreview.models.Book;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {

       Book book = new Book();
       book.setId(resultSet.getInt("BOOK_ID"));
       book.setName(resultSet.getString("BOOK_NAME"));
       book.setAuthorName(resultSet.getString("AUTHOR_NAME"));
       book.setYear(resultSet.getInt("PUBLISHING_YEAR"));
       book.setPublisher(resultSet.getString("PUBLISHER"));
       book.setDescription(resultSet.getString("DESCRIPTION"));
       book.setRecommendedRetailPrice(resultSet.getDouble("RETAIL_PRICE"));
       book.setUrl(resultSet.getString("URL"));

       return book;
    }

}