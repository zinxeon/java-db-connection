package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    // method로 분리
    private Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();

        // DB 접속 ( ex sql workbeanch실행)
        Connection conn = DriverManager.getConnection(env.get("DB_HOST"),
                env.get("DB_USER"), env.get("DB_PASSWORD"));

        return conn;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB 접속 ( ex sql workbeanch실행)
//        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        Connection conn = makeConnection(); // makeConnection 분리하여 사용

        // Query문 작성
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // Query문 실행
        ps.executeUpdate();

        // 종료
        ps.close();
        conn.close();
        System.out.println("DB insert 성공");
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB 접속 ( ex sql workbeanch실행)
//        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        Connection conn = makeConnection(); // makeConnection 분리하여 사용
        // Query문 작성
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM users WHERE id = ?");
        ps.setString(1, id);

        // Query문 실행
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),
                rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();
        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add(new User("3", "Rara", "1234"));
//        User user = userDao.get("1");
//        System.out.println(user.getName());
    }
}
