package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Autocamper;
import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.utility.JDBCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;


public class AutocamperRepository implements IRepository<Autocamper> {

    private Connection connection;

    @Autowired
    private AutocamperTypeRepository typeRepository;

    public AutocamperRepository() throws SQLException {
        connection = JDBCConnection.getInstance().getConnection();
    }
/*
    public AutocamperRepository() throws SQLException {
        connection = JDBCConnection.getDatabaseConnection();
    }

 */

    public Autocamper getById(int id) {
        try {
            String select = "SELECT * \n" +
                    "FROM autocamper " +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(select);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Autocamper auto = null;
            while(rs.next()){
                auto = load(rs);
            }
            return auto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Autocamper> getByParameter(String parameter, String... columns) {
        try {
            ArrayList<Autocamper> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String s : columns) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            String select = "SELECT * \n" +
                    "FROM autocamper " +
                    "WHERE " + sb + " LIKE ?";
            PreparedStatement prep = connection.prepareStatement(select);
            prep.setString(1, parameter);

            ResultSet rs = prep.executeQuery();

            while(rs.next()){
               list.add(load(rs));
            }
            return list;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }

    public ArrayList<Autocamper> getAll() {
        ArrayList<Autocamper> list = new ArrayList<>();
        try {
            String sql = "SELECT * " +
                    "FROM autocamper";
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while(rs.next()){
                list.add(load(rs));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
        return list;
    }

    public Autocamper create(Autocamper autocamper) {
        try {
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
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }

    public boolean update(Autocamper autocamper) {
        try {
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
            prep.setString(4, autocamper.getType().getBrand());
            prep.setString(5, autocamper.getType().getModel());
            prep.setInt(6, autocamper.getId());
            prep.executeUpdate();
            return true;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        }
    }

    public boolean delete(Autocamper autocamper) {
        try {
            String delete = "DELETE FROM autocamper WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(delete);
            prep.setInt(1, autocamper.getId());
            prep.executeUpdate();
            return true;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        }
    }

    private Autocamper load(ResultSet rs) throws SQLException{
        Autocamper autocamper = new Autocamper();
        autocamper.setId(rs.getInt("id"));
        autocamper.setStatus(rs.getInt("current_status"));
        autocamper.setPicture(rs.getString("picture"));
        autocamper.setType(typeRepository.getByBrandAndModel(rs.getString("fk_brand"),rs.getString("fk_model")));
        return autocamper;
    }
}
