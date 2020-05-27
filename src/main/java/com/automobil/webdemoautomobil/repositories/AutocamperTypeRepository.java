package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutocamperTypeRepository {
    private Connection conn;

    public AutocamperTypeRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
    }

    //finder ikke med id men med autocamperType
    public AutocamperType getByAutocamperType(AutocamperType autocamperType){
        try{
            PreparedStatement getSingleType = conn.prepareStatement
                    ("SELECT * FROM autocamper_type " +
                            "WHERE brand = ? AND model = ?");
            getSingleType.setString(1, autocamperType.getBrand());
            getSingleType.setString(2, autocamperType.getModel());

            ResultSet rs = getSingleType.executeQuery();
            AutocamperType aT = new AutocamperType();
            while (rs.next()){
                aT.setBrand(rs.getString(1));
                aT.setModel(rs.getString(2));
                aT.setPrice(rs.getInt(3));
                aT.setProductionYear(rs.getInt(4));
                aT.setWeight(rs.getInt(5));
                aT.setFuelCapacity(rs.getInt(6));
                aT.setHorsePower(rs.getInt(7));
                aT.setMaxSpeed(rs.getInt(8));
                aT.setStandingHeight(rs.getInt(9));
                aT.setArea(rs.getInt(10));
                aT.setHeight(rs.getInt(11));
                aT.setLength(rs.getInt(12));
                aT.setWidth(rs.getInt(13));
                aT.setDescription(rs.getString(14));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return autocamperType;
    }
    public AutocamperType[] getByParameter(String parameter, String... columns){
        ArrayList<AutocamperType> autoList = new ArrayList<AutocamperType>();
        try {

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for(String s: columns){
                if(sb.length() != 0){
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            PreparedStatement select = conn.prepareStatement
                    ("SELECT * FROM autocamper_type WHERE" +
                            sb + " LIKE %?%");
            select.setString(1, "parameter");
            ResultSet rs = select.executeQuery();

            while(rs.next()){
                AutocamperType autoType = new AutocamperType();

                autoType.setBrand(rs.getString(1));
                autoType.setModel(rs.getString(2));
                autoType.setPrice(rs.getInt(3));
                autoType.setProductionYear(rs.getInt(4));
                autoType.setWeight(rs.getInt(5));
                autoType.setFuelCapacity(rs.getInt(6));
                autoType.setHorsePower(rs.getInt(7));
                autoType.setMaxSpeed(rs.getInt(8));
                autoType.setStandingHeight(rs.getInt(9));
                autoType.setArea(rs.getInt(10));
                autoType.setHeight(rs.getInt(11));
                autoType.setLength(rs.getInt(12));
                autoType.setWidth(rs.getInt(13));
                autoType.setDescription(rs.getString(14));

                autoList.add(autoType);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return autoList.toArray(new AutocamperType[autoList.size()]);
    }
    public AutocamperType[] getAll() {
        ArrayList<AutocamperType> autoList = new ArrayList<AutocamperType>();

        try {
            PreparedStatement getAllTypes = conn.prepareStatement
                    ("SELECT * FROM autocamper_type");
            ResultSet rs = getAllTypes.executeQuery();

            while(rs.next()){
                AutocamperType autoType = new AutocamperType();

                autoType.setBrand(rs.getString(1));
                autoType.setModel(rs.getString(2));
                autoType.setPrice(rs.getInt(3));
                autoType.setProductionYear(rs.getInt(4));
                autoType.setWeight(rs.getInt(5));
                autoType.setFuelCapacity(rs.getInt(6));
                autoType.setHorsePower(rs.getInt(7));
                autoType.setMaxSpeed(rs.getInt(8));
                autoType.setStandingHeight(rs.getInt(9));
                autoType.setArea(rs.getInt(10));
                autoType.setHeight(rs.getInt(11));
                autoType.setLength(rs.getInt(12));
                autoType.setWidth(rs.getInt(13));
                autoType.setDescription(rs.getString(14));

                autoList.add(autoType);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return autoList.toArray(new AutocamperType[autoList.size()]);
    }

    public AutocamperType create(AutocamperType autocamperType){
        try{
            PreparedStatement createAutoType = conn.prepareStatement
                    ("INSERT INTO autocamper_type " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            createAutoType.setString(1, autocamperType.getBrand());
            createAutoType.setString(2, autocamperType.getModel());
            createAutoType.setInt(3, autocamperType.getPrice());
            createAutoType.setInt(4, autocamperType.getProductionYear());
            createAutoType.setInt(5, autocamperType.getWeight());
            createAutoType.setInt(6, autocamperType.getFuelCapacity());
            createAutoType.setInt(7, autocamperType.getHorsePower());
            createAutoType.setInt(8, autocamperType.getMaxSpeed());
            createAutoType.setInt(9, autocamperType.getStandingHeight());
            createAutoType.setInt(10, autocamperType.getArea());
            createAutoType.setInt(11, autocamperType.getHeight());
            createAutoType.setInt(12, autocamperType.getLength());
            createAutoType.setInt(13, autocamperType.getWidth());
            createAutoType.setString(14, autocamperType.getDescription());
            createAutoType.executeUpdate();
            return autocamperType;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(AutocamperType autocamperType) {
        try {
            PreparedStatement updateAutoType = conn.prepareStatement
                    ("UPDATE autocamper_type " +
                            "SET price = ?, " +
                            "production_year = ?, " +
                            "weight = ?, " +
                            "fuel_capacity = ?, " +
                            "horse_power = ?, " +
                            "max_speed = ?, " +
                            "standing_height = ?, " +
                            "area_sqm = ?, " +
                            "height = ?, " +
                            "lenght = ?, " +
                            "width = ?, " +
                            "description = ? " +
                            "WHERE brand = ? AND model = ?");
            updateAutoType.setInt(1,autocamperType.getPrice());
            updateAutoType.setInt(2, autocamperType.getProductionYear());
            updateAutoType.setInt(3, autocamperType.getWeight());
            updateAutoType.setInt(4, autocamperType.getFuelCapacity());
            updateAutoType.setInt(5, autocamperType.getHorsePower());
            updateAutoType.setInt(6, autocamperType.getMaxSpeed());
            updateAutoType.setInt(7, autocamperType.getStandingHeight());
            updateAutoType.setInt(8, autocamperType.getArea());
            updateAutoType.setInt(9, autocamperType.getHeight());
            updateAutoType.setInt(10, autocamperType.getLength());
            updateAutoType.setInt(11, autocamperType.getWidth());
            updateAutoType.setString(12, autocamperType.getDescription());
            updateAutoType.setString(13, autocamperType.getBrand());
            updateAutoType.setString(14, autocamperType.getModel());
            updateAutoType.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(AutocamperType autocamperType){
        try{
            PreparedStatement deleteAutoType = conn.prepareStatement
                    ("DELETE FROM autocamper_type " +
                            "WHERE brand = ? AND model = ?");
            deleteAutoType.setString(1, autocamperType.getBrand());
            deleteAutoType.setString(2, autocamperType.getModel());
            deleteAutoType.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
