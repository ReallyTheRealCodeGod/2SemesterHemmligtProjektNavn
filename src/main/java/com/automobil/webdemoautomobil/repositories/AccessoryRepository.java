package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Accessory;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class AccessoryRepository implements IRepository<Accessory>{

    private Connection connection;

    public AccessoryRepository() throws SQLException {
        connection = JDBCConnection.getInstance().getConnection();
    }





    public Accessory getById(int id){
	 try{
        String select = "SELECT * FROM Accessory access" +
                "JOIN accessory_type acctype " +
                "ON acctype.id = access.fk_accessory_type_id " +
                "WHERE acctype.id = ?";
        PreparedStatement prep = connection.prepareStatement(select);
        Accessory accessory = new Accessory();
        
        prep.setInt(1,id);
        ResultSet rs = prep.executeQuery();
        while(rs.next()) {
            accessory = load(rs);
        }
        return accessory;
     }catch(SQLException sql) {
         sql.printStackTrace();
         return null;
     }
    }

    public ArrayList<Accessory> getByParameter(String parameter, String... columns){
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

        String select = "SELECT * FROM accessory access\n" +
                "JOIN Accessory_type acctype\n" +
                "ON acctype.id = access.fk_accessory_type_id " +
                "WHERE " + sb + " LIKE ?";

        PreparedStatement prep = connection.prepareStatement(select);
        prep.setString(1, parameter);
        ResultSet rs = prep.executeQuery();

        while(rs.next()) {
            list.add(load(rs));
        }
        return list;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public ArrayList<Accessory> getAll() {
	 try{
        ArrayList<Accessory> list = new ArrayList<>();

        String select = "SELECT * FROM accessory_type acctype\n" +
                "JOIN Accessory access\n" +
                "ON acctype.id = access.fk_accessory_type_id";
        
        PreparedStatement prep = connection.prepareStatement(select);
        ResultSet rs = prep.executeQuery();

        while(rs.next()) {
            list.add(load(rs));
        }
        return list;
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
     }catch(SQLException sql){
         if(sql.getErrorCode() == 1452){
             accessory = addType(accessory);
             create(accessory);
         }else {
             sql.printStackTrace();
             return null;
         }
	 }
        return accessory;
    }

    public Accessory addType(Accessory accessory){
        try {
            PreparedStatement prep = connection.prepareStatement("INSERT INTO accessory_type (name, description, price) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, accessory.getName());
            prep.setString(2, accessory.getDescription());
            prep.setInt(3, accessory.getPrice());
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            rs.next();
            accessory.setTypeId(rs.getInt(1));
            return accessory;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
        }

        //returns a list of all types as well as the amount of occurences in the database
    public HashMap<Accessory, Integer> getAllTypes(){
        ArrayList<Accessory> acc = getAll();
        HashMap<Integer, Integer> amounts = new HashMap();
        HashMap<Accessory, Integer> map = new HashMap<>();

        //counts occurrences of any given accessory
        for(Accessory a: acc){
            amounts.putIfAbsent(a.getTypeId(), 0);
            amounts.put(a.getTypeId(), amounts.get(a.getTypeId()) + 1);
        }
        //creates a unique list of accessories mapped to their respective amounts
        Set<Integer> keys = amounts.keySet();
        for(Accessory a: acc){
            if(keys.contains(a.getTypeId())) {
                map.put(a, amounts.get(a.getTypeId()));
                keys.remove(a.getTypeId());
            }
        }
        return map;
    }

    public boolean update(Accessory accessory) {
	 try {
         String update = "UPDATE accessory " +
                 " SET fk_accessory_type_id = ?," +
                 "fk_rental_id = ? " +
                 "WHERE id = ?";
         PreparedStatement prepAcc = connection.prepareStatement(update);
         prepAcc.setInt(1, accessory.getTypeId());
         prepAcc.setInt(2, accessory.getRentalId());
         prepAcc.setInt(3, accessory.getId());
         prepAcc.executeUpdate();
         return true;
     }catch(SQLException sql){
             sql.printStackTrace();
             return false;
         }
    }

    public boolean updateType(Accessory accessory)throws SQLException{
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
        prepAccType.executeUpdate();
        return true;
    }

    public boolean delete(Accessory accessory) {
	 try{
        String delete = "DELETE FROM accessory WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.setInt(1, accessory.getId());
        prep.executeUpdate();
        return true;
	 }catch(SQLException sql){
	     sql.printStackTrace();
	     return false;
	 }
	 }

	 private Accessory load(ResultSet rs) throws SQLException{
         Accessory accessory = new Accessory();
         accessory.setId(rs.getInt("id"));
         accessory.setName(rs.getString("name"));
         accessory.setPrice(rs.getInt("price"));
         accessory.setDescription(rs.getString("description"));
         accessory.setTypeId(rs.getInt("fk_accessory_type_id"));
         accessory.setRentalId(rs.getInt("fk_rental_id"));
         return accessory;
     }
}
