package com.automobil.webdemoautomobil.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {

    private static Connection conn;
    @Value("db.user")
    public static String user;
    @Value("db.url")
    private static String url;
    @Value("db.password")
    public static String password;

    // singleton for getting an instance of the database connection
    public static Connection getDatabaseConnection(){
        if(conn == null) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("C:\\Users\\123al\\Desktop\\Programming-Design\\coding\\Skole\\Mandatory\\2SemesterHemmligtProjektNavn\\src\\main\\resources\\application.properties"));

                user = prop.getProperty("db.user");
                url = prop.getProperty("db.url");
                password = prop.getProperty("db.password");

                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Message to the developer");
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
