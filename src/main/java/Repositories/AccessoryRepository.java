package Repositories;

import models.Accessory;
import models.Autocamper;
import utility.JDBCConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessoryRepository {
    Connection conn;
    public AccessoryRepository(){
        conn = JDBCConnection.getDatabaseConnection();
    }

    /*public Accessory getById(int id){
        Accessory accessory = new Accessory();
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM accessory_type WHERE type_id = ?");
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Method[] methods = accessory.getSetters();
            Field[] fields = accessory.format();
            while(rs.next()) {

            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }catch(IllegalAccessException | InvocationTargetException IAE){
            System.out.println("we are not allowed access to a method being invoked");
        }
        return accessory;
    }*/
}
