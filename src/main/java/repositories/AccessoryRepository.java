package repositories;

import models.Accessory;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class AccessoryRepository {
    Connection connection;
    public AccessoryRepository(){
        connection = JDBCConnection.getDatabaseConnection();
    }

    public Accessory getById(int id){
	 try{
        String select = "SELECT * FROM accessory_type acctype " +
                "JOIN Accessory access " +
                "ON acctype.id = access.fk_accessory_type_id " +
                "WHERE acctype.id = ?";
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
     }catch(SQLException sql) {
         sql.printStackTrace();
         return null;
     }
    }

    public Accessory[] getByParameter(String parameter, String... columns){
        try{
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
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public Accessory[] getAll() {
	 try{
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
     }catch(SQLException sql){
         sql.printStackTrace();
         return null;
     }
    }

    public Accessory create(Accessory accessory) {
	 try {
         String create = "INSERT INTO accessory(id, fk_accessory_type_id) Values (DEFAULT, ?)";
         PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
         prep.setInt(1, accessory.getTypeId());
         prep.executeUpdate();
         ResultSet rs = prep.getGeneratedKeys();
         rs.next();
         accessory.setId(rs.getInt(1));
         return accessory;
     }catch(SQLException sql){
             sql.printStackTrace();
             return null;
         }
    }

    public boolean update(Accessory accessory) {
	 try {
	     String update = "UPDATE accessory_type " +
                 "SET name = ?," +
                 "price = ?, " +
                 "description = ? " +
                 "WHERE id = ?";
         PreparedStatement prepAccType = connection.prepareStatement(update);
         prepAccType.setString(1, accessory.getName());
         prepAccType.setInt(2, accessory.getPrice());
         prepAccType.setString(3, accessory.getDescription());
         prepAccType.setInt(4, accessory.getTypeId());

         update = "UPDATE accessory " +
                 " SET fk_accessory_type_id = ?," +
                 "fk_rental_id = ? " +
                 "WHERE id = ?";
         PreparedStatement prepAcc = connection.prepareStatement(update);
         prepAcc.setInt(1, accessory.getTypeId());
         prepAcc.setInt(2, accessory.getRentalId());
         prepAcc.setInt(3, accessory.getId());
         prepAcc.executeUpdate();
         prepAccType.executeUpdate();
         return true;
     }catch(SQLException sql){
             sql.printStackTrace();
             return false;
         }
    }

    public boolean delete(Accessory accessory) {
	 try{
        String delete = "DELETE FROM accessory WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.setInt(1, accessory.getRentalId());
        prep.executeUpdate();
        return true;
	 }catch(SQLException sql){
	     sql.printStackTrace();
	     return false;
	 }
	 }
}
