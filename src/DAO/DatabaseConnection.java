package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/aims"; // Thay "aims" bằng tên database của bạn
    private static final String USER = "postgres"; // Thay bằng username của bạn
    private static final String PASSWORD = "admin"; // Thay bằng mật khẩu của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
