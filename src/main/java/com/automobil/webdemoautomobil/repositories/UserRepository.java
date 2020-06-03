package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {
    Connection connection;
    public UserRepository() throws SQLException {
        connection = JDBCConnection.getInstance().getConnection();
    }

    public ArrayList<String> getAuthorities(){
        ArrayList<String> authorities = new ArrayList<>();
        try {
            PreparedStatement prep = connection.prepareStatement("SELECT authority FROM authorities");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                authorities.add(rs.getString("authority"));
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }
        return authorities;
    }
}
