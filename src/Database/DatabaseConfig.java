package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String url = "jdbc:postgresql://localhost:5432/rmi";
    private static final String username = "postgres";
    private static final String password = "password";

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}