package Repositories;

import models.Autocamper;
import models.AutocamperType;
import models.BuiltInFeature;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class AutocamperRepository{
    Connection connection;

    AutocamperRepository(){
        connection = JDBCConnection.getDatabaseConnection();
    }

    public Autocamper getById(int id) throws SQLException {
        String select = "SELECT * \n" +
                    "FROM autocamper auto\n" +
                    "JOIN autocamper_type autotype\n" +
                    "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;" +
                "WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(select);
        prep.setInt(1, id);

        Autocamper autocamper = new Autocamper();
        ResultSet rs = prep.executeQuery();
        while(rs.next()) {
            autocamper.setId(rs.getInt("id"));
            autocamper.setStatus(rs.getInt("status"));
            AutocamperType type = new AutocamperType();

            autocamper.setId(rs.getInt("id"));
            autocamper.setStatus(rs.getInt("status"));
            autocamper.setPhoto(rs.getString("photo"));

            type.setModel(rs.getString("model"));
            type.setBrand(rs.getString("brand"));
            type.setBuiltInFeatures(null);
            type.setPrice(rs.getInt("price"));
            type.setHorsePower(rs.getInt("horsePower"));
            
            type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
            type.setFuelType("fuelType");
            type.setStandingHeight(rs.getInt("standingHeight"));
            type.setMaxSpeed(rs.getInt("maxSpeed"));
            type.setHeight(rs.getInt("height"));
            type.setLength(rs.getInt("length"));
            type.setWidth(rs.getInt("width"));
            type.setArea(rs.getInt("area"));
            type.setDescription(rs.getString("description"));
            autocamper.setType(type);
        }
        return autocamper;
    }
    public Autocamper[] getByParameter(String parameter, String... columns) throws SQLException{
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
            autocamper.setPhoto(rs.getString("photo"));

            type.setModel(rs.getString("model"));
            type.setBrand(rs.getString("brand"));
            type.setBuiltInFeatures(null);
            type.setPrice(rs.getInt("price"));
            type.setHorsePower(rs.getInt("horsePower"));
            
            type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
            type.setFuelType("fuelType");
            type.setStandingHeight(rs.getInt("standingHeight"));
            type.setMaxSpeed(rs.getInt("maxSpeed"));
            type.setHeight(rs.getInt("height"));
            type.setLength(rs.getInt("length"));
            type.setWidth(rs.getInt("width"));
            type.setArea(rs.getInt("area"));
            type.setDescription(rs.getString("description"));
            type.setBuiltInFeatures(getBuiltInFeatures(type));
            autocamper.setType(type);

            list.add(autocamper);
        }
        return list.toArray(new Autocamper[list.size()]);

    }
    public Autocamper[] getAll() throws SQLException {
        ArrayList<Autocamper> list = new ArrayList<>();

        String sql = "SELECT * \n" +
                "FROM autocamper auto\n" +
                "JOIN autocamper_type autotype\n" +
                "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;";
        PreparedStatement prep = connection.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();

        while(rs.next()) {
            Autocamper autocamper = new Autocamper();
            AutocamperType type = new AutocamperType();
            autocamper.setId(rs.getInt("id"));
            autocamper.setStatus(rs.getInt("status"));
            autocamper.setPhoto(rs.getString("photo"));

            type.setModel(rs.getString("model"));
            type.setBrand(rs.getString("brand"));
            type.setBuiltInFeatures(null);
            type.setPrice(rs.getInt("price"));
            type.setHorsePower(rs.getInt("horsePower"));
            type.setFuelEfficiency(rs.getInt("fuelEfficiency"));
            type.setFuelType("fuelType");
            type.setStandingHeight(rs.getInt("standingHeight"));
            type.setMaxSpeed(rs.getInt("maxSpeed"));
            type.setHeight(rs.getInt("height"));
            type.setLength(rs.getInt("length"));
            type.setWidth(rs.getInt("width"));
            type.setArea(rs.getInt("area"));
            type.setDescription(rs.getString("description"));
            type.setBuiltInFeatures(getBuiltInFeatures(type));
            autocamper.setType(type);

            list.add(autocamper);
        }
        return list.toArray(new Autocamper[list.size()]);
    }

    public Autocamper create(Autocamper autocamper) throws SQLException {
        String create = "INSERT INTO autocamper(id, mileage, current_status, picture, fk_brand, fk_model) Values (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, autocamper.getMileage());
        prep.setInt(2, autocamper.getStatus());
        prep.setString(3, autocamper.getPhoto());
        prep.setString(4, autocamper.getType().getBrand());
        prep.setString(5, autocamper.getType().getModel());
        prep.executeUpdate();
        ResultSet rs = prep.getGeneratedKeys();
        rs.next();
        autocamper.setId(rs.getInt(1));
        return autocamper;

    }

    public boolean update(Autocamper autocamper) throws SQLException {
        String update = "UPDATE autocamper " +
                "SET mileage = ?," +
                "current_status = ?," +
                "photo = ?" +
                "fk_brand = ?" +
                "fk_model = ?;" +
                "WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(update);
        prep.setInt(1, autocamper.getMileage());
        prep.setInt(2, autocamper.getStatus());
        prep.setString(3, autocamper.getPhoto());
        prep.setString(4,autocamper.getType().getBrand());
        prep.setString(5,autocamper.getType().getModel());
        prep.setInt(6, autocamper.getId());
        prep.executeUpdate();
        return true;
    }

    public boolean delete(Autocamper autocamper) throws SQLException {
        String delete = "DELETE FROM autocamper WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.setInt(1, autocamper.getId());
        prep.executeUpdate();
        return true;
    }

    private BuiltInFeature[] getBuiltInFeatures(AutocamperType autoType) throws SQLException {
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

        while(rs.next()){
            BuiltInFeature bif = new BuiltInFeature();
            bif.setId(rs.getInt("id"));
            bif.setName(rs.getString("name"));
            bif.setDescription(rs.getString("description"));
            bif.setPhoto(rs.getString("photo"));
            list.add(bif);
        }
        return list.toArray(new BuiltInFeature[list.size()]);
    }
}
