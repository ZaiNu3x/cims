package group.intelliboys.cims.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_NAME = "cims";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_HOST+DB_NAME, DB_USER, DB_PASSWORD);
    }
}
