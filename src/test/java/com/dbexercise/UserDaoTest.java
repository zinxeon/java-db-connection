package com.dbexercise;


import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao();
//        User user = new User("2","EternityHwan","1123");
        AWSUserDaoImpl userDao = new AWSUserDaoImpl();
        User user = new User("3", "EternityHwan","1123");
        userDao.add(user);

        User selectedUser = userDao.get("1");
        Assertions.assertEquals("aa", selectedUser.getName());
    }
}