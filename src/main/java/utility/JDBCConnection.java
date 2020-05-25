package utility;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
    private static Connection conn;
    @Value("user")
    public static String user;
    @Value("url")
    private static String url;
    @Value("password")
    public static String password;


    public static Connection getDatabaseConnection() {
        System.out.println(user);
        if(conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Message to the developer");
                e.printStackTrace();
            }
        }
        return conn;
    }
}
