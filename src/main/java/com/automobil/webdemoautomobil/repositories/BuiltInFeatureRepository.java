package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.AutocamperType;
import com.automobil.webdemoautomobil.models.BuiltInFeature;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class BuiltInFeatureRepository implements IRepository<BuiltInFeature> {

    private Connection connection;
    public BuiltInFeatureRepository() {
        connection = JDBCConnection.getDatabaseConnection();
    }



    public ArrayList<BuiltInFeature> getAllByAutocamperType(AutocamperType type) {
        try {
            ArrayList<BuiltInFeature> list = new ArrayList<>();
            String select = "SELECT * " +
                    "FROM built_in_feature bif " +
                    "JOIN type_features tf " +
                    "ON bif.id = tf.feature_id " +
                    "WHERE type_brand = ? and type_model = ?";
            PreparedStatement prep = connection.prepareStatement(select);

            prep.setString(1, type.getBrand());
            prep.setString(2, type.getModel());
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                list.add(load(rs));
            }
            return list;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }
    public ArrayList<BuiltInFeature> getAll() {
        try {
            ArrayList<BuiltInFeature> list = new ArrayList<>();
            String select = "SELECT * " +
                    "FROM built_in_feature";
            PreparedStatement prep = connection.prepareStatement(select);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                list.add(load(rs));
            }
            return list;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }
    public BuiltInFeature getById(int id) {
        try {
            String select = "SELECT * FROM" +
                    "FROM built_in_feature " +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(select);

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
               return load(rs);
            }
            return null;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }
    public ArrayList<BuiltInFeature> getByParameter(String parameter, String... columns){
        try {
            ArrayList<BuiltInFeature> list = new ArrayList<>();

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
                    "FROM built_in_feature\n" +
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

    @Override
    public BuiltInFeature create(BuiltInFeature feature) {
        try {
            String create = "INSERT INTO built_in_feature(id, name, description, picture) Values (DEFAULT, ?, ?, ?)";
            PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, feature.getName());
            prep.setString(2, feature.getDescription());
            prep.setString(3, feature.getIcon());
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            rs.next();
            feature.setId(rs.getInt(1));
            return feature;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(BuiltInFeature feature) {
        try {
            String update = "UPDATE built_in_feature " +
                    "SET name = ?," +
                    "description = ?," +
                    "picture = ? " +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(update);
            prep.setString(1, feature.getName());
            prep.setString(2, feature.getDescription());
            prep.setString(3, feature.getIcon());
            prep.setInt(4, feature.getId());
            prep.executeUpdate();
            return true;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        }
    }

    public boolean delete(BuiltInFeature feature) {
        try {
            String delete = "DELETE FROM built_in_feature WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(delete);
            prep.setInt(1, feature.getId());
            prep.executeUpdate();
            return true;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        }
    }



    private BuiltInFeature load(ResultSet rs) throws SQLException{
        BuiltInFeature bif = new BuiltInFeature();
        bif.setId(rs.getInt("id"));
        bif.setName(rs.getString("name"));
        bif.setDescription(rs.getString("description"));
        bif.setIcon(rs.getString("picture"));
        return bif;
    }
}
