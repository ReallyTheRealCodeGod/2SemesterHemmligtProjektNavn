package Repositories;

import models.Accessory;
import models.Accessory;
import utility.JDBCConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

public class AccessoryRepository {
    Connection connection;
    public AccessoryRepository(){
        connection = JDBCConnection.getDatabaseConnection();
    }

    public Accessory getById(int id) throws SQLException {
        String select = "SELECT * FROM accessory_type acctype\n" +
                "JOIN Accessory access\n" +
                "ON acctype.id = access.fk_accessory_type_id;" +
                "WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(select);
        Accessory accessory = new Accessory();
        
        prep.setInt(1,id);
        ResultSet rs = prep.executeQuery();
        while(rs.next()) {
            accessory.setId(rs.getInt("id"));
            accessory.setName(rs.getString("name"));
            accessory.setPrice(rs.getInt("price"));
            accessory.setDescription(rs.getString("description"));
            accessory.setRentalId(rs.getInt("fk_rental_id"));
        }
        return accessory;
    }
    public Accessory[] getByParameter(String parameter, String... columns) throws SQLException{
        ArrayList<Accessory> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(String s: columns){
            if(sb.length() != 0){
                sb.append(", ");
            }
            sb.append(s);
            i++;
        }

        String select = "SELECT * FROM accessory_type acctype\n" +
                "JOIN Accessory access\n" +
                "ON acctype.id = access.fk_accessory_type_id;" +
                "WHERE" + sb + " LIKE %?% ";

        PreparedStatement prep = connection.prepareStatement(select);
        prep.setString(1, parameter);
        ResultSet rs = prep.executeQuery();

        while(rs.next()) {
            Accessory accessory = new Accessory();
            accessory.setId(rs.getInt("id"));
            accessory.setName(rs.getString("name"));
            accessory.setPrice(rs.getInt("price"));
            accessory.setDescription(rs.getString("description"));
            accessory.setRentalId(rs.getInt("fk_rental_id"));

            list.add(accessory);
        }
        return list.toArray(new Accessory[list.size()]);

    }
    public Accessory[] getAll() throws SQLException {
        ArrayList<Accessory> list = new ArrayList<>();

        String select = "SELECT * FROM accessory_type acctype\n" +
                "JOIN Accessory access\n" +
                "ON acctype.id = access.fk_accessory_type_id";
        
        PreparedStatement prep = connection.prepareStatement(select);
        ResultSet rs = prep.executeQuery();

        while(rs.next()) {
            Accessory accessory = new Accessory();
            accessory.setId(rs.getInt("id"));
            accessory.setName(rs.getString("name"));
            accessory.setPrice(rs.getInt("price"));
            accessory.setDescription(rs.getString("description"));
            accessory.setRentalId(rs.getInt("fk_rental_id"));

            list.add(accessory);
        }
        return list.toArray(new Accessory[list.size()]);
    }

    public Accessory create(Accessory accessory) throws SQLException {
        String create = "INSERT INTO accessory(id, name, price, description) Values (DEFAULT, ?, ?, ?)";
        PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, accessory.getName());
        prep.setInt(2, accessory.getPrice());
        prep.setString(3, accessory.getDescription());
        prep.executeUpdate();
        ResultSet rs = prep.getGeneratedKeys();
        rs.next();
        accessory.setId(rs.getInt(1));
        return accessory;

    }

    public boolean update(Accessory accessory) throws SQLException {
        String update = "UPDATE accessory " +
                "SET name = ?," +
                "price = ?," +
                "description = ?" +
                "fk_rental_id = ?" +
                "WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(update);
        prep.setString(1, accessory.getName());
        prep.setInt(2, accessory.getPrice());
        prep.setString(3, accessory.getDescription());
        prep.setInt(4,accessory.getRentalId());
        prep.setInt(5, accessory.getId());
        prep.executeUpdate();
        return true;
    }

    public boolean delete(Accessory accessory) throws SQLException {
        String delete = "DELETE FROM accessory WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.setInt(1, accessory.getRentalId());
        prep.executeUpdate();
        return true;
    }
}
