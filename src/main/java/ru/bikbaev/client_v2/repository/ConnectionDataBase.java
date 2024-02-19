package ru.bikbaev.client_v2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    private static final  String URL = "jdbc:postgresql://158.160.144.211:5432/client_base";
    private static final  String USER = "artur";
    private static final  String PASSWORD = "artur";

    public static Connection getConnection() throws SQLException {
        try {
            // Загрузка драйвера JDBC для PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL JDBC Driver не найден. Включите его в classpath.");
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
