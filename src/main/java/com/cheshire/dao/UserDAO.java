package com.cheshire.dao;

import com.cheshire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAll(){
        return jdbcTemplate.query("SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?",
                new Object[]{ email },
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }
    public void addUser(User user) throws SQLException {
        jdbcTemplate.update("insert into users values(?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail());
    }
}
