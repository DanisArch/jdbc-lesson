package com.sql.config;

import com.sql.utils.ConfigurationReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String url=ConfigurationReader.get("db.base_url");
    private static final String username=ConfigurationReader.get("db.userName");
    private static final String password=ConfigurationReader.get("db.password");

    private ConnectionManager() {
    }

    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
