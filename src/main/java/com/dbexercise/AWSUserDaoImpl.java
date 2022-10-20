package com.dbexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AWSUserDaoImpl extends UserDaoAbstract{

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();

        //jdbc사용, 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");

        //db접속
        Connection conn = DriverManager.getConnection(env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD"));
        return conn;
    }
}
