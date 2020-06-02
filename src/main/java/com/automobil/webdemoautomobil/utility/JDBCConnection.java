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
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnection {

    @Value("${db.user}")
    private String user;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;

    private static JDBCConnection instance;
   // private static Connection conn;
    private Connection conn;

    private JDBCConnection(){
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            url = prop.getProperty("db.url");
            System.out.println(url);
            this.conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException | FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection(){
        return conn;
    }

    // singleton for getting an instance of the database connection
    public static JDBCConnection getInstance() throws SQLException{
        if(instance == null){
            instance = new JDBCConnection();
        } else if (instance.getConnection().isClosed()){
            instance = new JDBCConnection();
        }
        return instance;
    }


/*
    public JDBCConnection(){
        conn = JDBCConnection.getDatabaseConnection();
    }

    public static Connection getDatabaseConnection(){
        if(conn == null) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream("src/main/resources/application.properties"));
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

 */



}
