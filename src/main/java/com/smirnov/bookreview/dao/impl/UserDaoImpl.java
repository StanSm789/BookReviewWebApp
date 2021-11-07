package com.smirnov.bookreview.dao.impl;

import com.smirnov.bookreview.dao.UserDao;
import com.smirnov.bookreview.dao.mappers.UserMapper;
import com.smirnov.bookreview.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM USERS WHERE USER_ID = ?;";
    public static final String FIND_ALL_QUERY = "SELECT * FROM USERS;";
    public static final String FIND_ALL_QUERY_WITH_LIMIT =
            "SELECT * FROM USERS ORDER BY USER_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    public static final String SAVE_QUERY = "INSERT INTO USERS (USER_NAME, USER_EMAIL, " +
            "USER_PASSWORD, USER_TYPE) VALUES (?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE USERS\n" +
            "SET USER_ID = ?, USER_NAME = ?, USER_EMAIL = ?," +
            "USER_PASSWORD = ?, USER_TYPE = ?\n" +
            "WHERE USER_ID = ?;";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM USERS WHERE USER_ID = ?;";
    protected UserMapper userMapper;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate,
                       UserMapper userMapper) {
        super(jdbcTemplate, transactionTemplate, FIND_BY_ID_QUERY, FIND_ALL_QUERY,
                SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
        this.userMapper = userMapper;
    }

    @Override
    public User findByIntParam(String sql, Integer id) {

        return transactionTemplate.execute( status -> {
            User user = jdbcTemplate.query(FIND_BY_ID_QUERY,
                    new Object[]{id}, userMapper)
                    .stream().findAny().orElse(null);

            return user;
        });
    }

    @Override
    protected List<User> findAllEntities(String sql) {

        return transactionTemplate.execute( status -> {
            List<User> users = jdbcTemplate.query(FIND_ALL_QUERY, userMapper);

            return users;
        });
    }

    @Override
    protected List<User> findAllEntitiesWithinScope(String sql, int leftScope, int rightScope) {

        return transactionTemplate.execute( status -> {
            List<User> users = jdbcTemplate.query(FIND_ALL_QUERY_WITH_LIMIT,
                    new Object[] { leftScope, rightScope }, userMapper);

            return users;
        });
    }

    @Override
    protected void insert(String sql, User user) {

        jdbcTemplate.update(SAVE_QUERY, user.getName(), user.getEmail(), user.getPassword(), user.getType());
    }

    @Override
    protected void updateValue(String sql, Integer id, User user) {

        jdbcTemplate.update(UPDATE_QUERY, user.getId(), user.getName(), user.getEmail(),
                user.getPassword(), user.getType(), id);
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