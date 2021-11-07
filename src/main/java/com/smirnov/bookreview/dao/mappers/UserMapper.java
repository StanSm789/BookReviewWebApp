package com.smirnov.bookreview.dao.mappers;

import com.smirnov.bookreview.models.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        return User.builder().withId(resultSet.getInt("USER_ID"))
                .withName(resultSet.getString("USER_NAME"))
                .withEmail(resultSet.getString("USER_EMAIL"))
                .withPassword(resultSet.getString("USER_PASSWORD"))
                .withType(resultSet.getString("USER_TYPE"))
                .build();
    }

}
