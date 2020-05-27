package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class AutocamperRepository{

    Connection connection;

    public AutocamperRepository(){
        connection = JDBCConnection.getDatabaseConnection();
    }

    public Autocamper getById(int id){
        try{
            String select = "SELECT * \n" +
                        "FROM autocamper auto\n" +
                        "JOIN autocamper_type autotype\n" +
                        "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model " +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(select);
            prep.setInt(1, id);

            Autocamper autocamper = new Autocamper();
            ResultSet rs = prep.executeQuery();
            while(rs.next()) {
                autocamper.setId(rs.getInt("id"));
                autocamper.setStatus(rs.getInt("current_status"));
                AutocamperType type = new AutocamperType();

                autocamper.setId(rs.getInt("id"));
                autocamper.setStatus(rs.getInt("current_status"));
                autocamper.setPicture(rs.getString("picture"));

                type.setModel(rs.getString("model"));
                type.setBrand(rs.getString("brand"));
                type.setBuiltInFeatures(null);
                type.setPrice(rs.getInt("price"));
                type.setHorsePower(rs.getInt("horse_power"));

                //type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
                //type.setFuelType("fuelType");
                type.setStandingHeight(rs.getInt("standing_height"));
                type.setMaxSpeed(rs.getInt("max_speed"));
                type.setHeight(rs.getInt("height"));
                type.setLength(rs.getInt("length"));
                type.setWidth(rs.getInt("width"));
                type.setArea(rs.getInt("area_sqm"));
                type.setDescription(rs.getString("description"));
                autocamper.setType(type);
            }
            return autocamper;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
    public Autocamper[] getByParameter(String parameter, String... columns){
        try{
            ArrayList<Autocamper> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for(String s: columns){
                if(sb.length() != 0){
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            String select = "SELECT * \n" +
                    "FROM autocamper auto\n" +
                    "JOIN autocamper_type autotype\n" +
                    "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;" +
                    "WHERE " + sb +" LIKE %?%";
            PreparedStatement prep = connection.prepareStatement(select);
            prep.setString(i, parameter);
            for(int a = 1; a < i; a++){
                prep.setString(a, columns[a]);
            }

            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                Autocamper autocamper = new Autocamper();
                AutocamperType type = new AutocamperType();

                autocamper.setId(rs.getInt("id"));
                autocamper.setStatus(rs.getInt("status"));
                autocamper.setPicture(rs.getString("picture"));

                type.setModel(rs.getString("model"));
                type.setBrand(rs.getString("brand"));
                type.setBuiltInFeatures(null);
                type.setPrice(rs.getInt("price"));
                type.setHorsePower(rs.getInt("horse_power"));

                //type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
                //type.setFuelType("fuelType");
                type.setStandingHeight(rs.getInt("standing_height"));
                type.setMaxSpeed(rs.getInt("max_speed"));
                type.setHeight(rs.getInt("height"));
                type.setLength(rs.getInt("length"));
                type.setWidth(rs.getInt("width"));
                type.setArea(rs.getInt("area_sqm"));
                type.setDescription(rs.getString("description"));
                type.setBuiltInFeatures(getBuiltInFeatures(type));
                autocamper.setType(type);

                list.add(autocamper);
            }
            return list.toArray(new Autocamper[list.size()]);
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
    public Autocamper[] getAll(){
        ArrayList<Autocamper> list = new ArrayList<>();
        try {
            String sql = "SELECT * \n" +
                    "FROM autocamper auto\n" +
                    "JOIN autocamper_type autotype\n" +
                    "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;";
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()){
                Autocamper autocamper = new Autocamper();
                AutocamperType type = new AutocamperType();
                autocamper.setId(rs.getInt("id"));
                autocamper.setStatus(rs.getInt("status"));
                autocamper.setPicture(rs.getString("picture"));

                type.setModel(rs.getString("model"));
                type.setBrand(rs.getString("brand"));
                type.setBuiltInFeatures(null);
                type.setPrice(rs.getInt("price"));
                type.setHorsePower(rs.getInt("horse_power"));
                //type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
                //type.setFuelType("fuelType");
                type.setStandingHeight(rs.getInt("standing_height"));
                type.setMaxSpeed(rs.getInt("max_speed"));
                type.setHeight(rs.getInt("height"));
                type.setLength(rs.getInt("length"));
                type.setWidth(rs.getInt("width"));
                type.setArea(rs.getInt("area_sqm"));
                type.setDescription(rs.getString("description"));
                type.setBuiltInFeatures(getBuiltInFeatures(type));
                autocamper.setType(type);

                list.add(autocamper);
            }
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
        return list.toArray(new Autocamper[list.size()]);
    }

    public Autocamper create(Autocamper autocamper){
        try{
            String create = "INSERT INTO autocamper(id, mileage, current_status, picture, fk_brand, fk_model) Values (DEFAULT, ?, ?, ?, ?, ?)";
            PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, autocamper.getMileage());
            prep.setInt(2, autocamper.getStatus());
            prep.setString(3, autocamper.getPicture());
            prep.setString(4, autocamper.getType().getBrand());
            prep.setString(5, autocamper.getType().getModel());
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            rs.next();
            autocamper.setId(rs.getInt(1));
            return autocamper;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public boolean update(Autocamper autocamper){
        try{
            String update = "UPDATE autocamper " +
                    "SET mileage = ?," +
                    "current_status = ?," +
                    "picture = ?" +
                    "fk_brand = ?" +
                    "fk_model = ?;" +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(update);
            prep.setInt(1, autocamper.getMileage());
            prep.setInt(2, autocamper.getStatus());
            prep.setString(3, autocamper.getPicture());
            prep.setString(4,autocamper.getType().getBrand());
            prep.setString(5,autocamper.getType().getModel());
            prep.setInt(6, autocamper.getId());
            prep.executeUpdate();
            return true;
        }catch(SQLException sql){
            sql.printStackTrace();
            return false;
        }
    }

    public boolean delete(Autocamper autocamper){
        try {
            String delete = "DELETE FROM autocamper WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(delete);
            prep.setInt(1, autocamper.getId());
            prep.executeUpdate();
            return true;
        }catch(SQLException sql){
        sql.printStackTrace();
        return false;
        }
    }

    private BuiltInFeature[] getBuiltInFeatures(AutocamperType autoType){
        try {
            ArrayList<BuiltInFeature> list = new ArrayList<>();

            String select = "SELECT * FROM" +
                    "FROM built_in_feature bif" +
                    "JOIN type_features tf" +
                    "ON bif.id = tf.feature_id" +
                    "WHERE type_brand = ? and type_model = ?";
            PreparedStatement prep = connection.prepareStatement(select);

            prep.setString(1, autoType.getBrand());
            prep.setString(2, autoType.getModel());
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                BuiltInFeature bif = new BuiltInFeature();
                bif.setId(rs.getInt("id"));
                bif.setName(rs.getString("name"));
                bif.setDescription(rs.getString("description"));
                bif.setIcon(rs.getString("picture"));
                list.add(bif);
            }
            return list.toArray(new BuiltInFeature[list.size()]);
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
}
