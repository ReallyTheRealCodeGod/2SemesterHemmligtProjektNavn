package Repositories;

import models.Autocamper;
import org.graalvm.compiler.virtual.phases.ea.PEReadEliminationBlockState;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class AutocamperRepository{
    Connection connection;

    AutocamperRepository(){
        connection = JDBCConnection.getDatabaseConnection();
    }

    public Autocamper getById(int id) throws SQLException {
        String sql = "SELECT * \n" +
                    "FROM autocamper auto\n" +
                    "JOIN autocamper_type autotype\n" +
                    "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;";
        PreparedStatement prep = connection.prepareStatement(sql);
        Autocamper autocamper = new Autocamper();
        ResultSet rs = prep.executeQuery();
        while(rs.next()) {
            autocamper.setId(rs.getInt("id"));
            autocamper.setStatus(rs.getInt("status"));
            autocamper.setBrand(rs.getString("brand"));
            autocamper.setModel(rs.getString("model"));
            autocamper.setPhoto(rs.getString("photo"));
            autocamper.setBuiltInFeatures(null);
            autocamper.setPrice(rs.getInt("price"));
            autocamper.setHorsePower(rs.getInt("horsePower"));
            autocamper.setMileage(rs.getInt("mileage"));
            autocamper.setFuelEfficiency(rs.getInt("fuelEfficiency"));
            autocamper.setFuelType("fuelType");
            autocamper.setStandingHeight(rs.getInt("standingHeight"));
            autocamper.setMaxSpeed(rs.getInt("maxSpeed"));
            autocamper.setHeight(rs.getInt("height"));
            autocamper.setLength(rs.getInt("length"));
            autocamper.setWidth(rs.getInt("width"));
            autocamper.setArea(rs.getInt("area"));
            autocamper.setDescription(rs.getString("description"));
        }
        return autocamper;
    }
    public Autocamper[] getByParameter(String parameter) throws SQLException{
        String select = "SELECT * \n" +
                "FROM autocamper auto\n" +
                "JOIN autocamper_type autotype\n" +
                "ON auto.fk_brand = autotype.brand AND auto.fk_model = autotype.model;" +
                "WHERE (SELECT column_name" +
                "    -> FROM information_schema.COLUMNS" +
                "    -> WHERE table_name = 'autocamper') LIKE ?";
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
            autocamper.setId(rs.getInt("id"));
            autocamper.setStatus(rs.getInt("status"));
            autocamper.setBrand(rs.getString("brand"));
            autocamper.setModel(rs.getString("model"));
            autocamper.setPhoto(rs.getString("photo"));
            autocamper.setBuiltInFeatures(null);
            autocamper.setPrice(rs.getInt("price"));
            autocamper.setHorsePower(rs.getInt("horsePower"));
            autocamper.setMileage(rs.getInt("mileage"));
            autocamper.setFuelEfficiency(rs.getInt("fuelEfficiency"));
            autocamper.setFuelType("fuelType");
            autocamper.setStandingHeight(rs.getInt("standingHeight"));
            autocamper.setMaxSpeed(rs.getInt("maxSpeed"));
            autocamper.setHeight(rs.getInt("height"));
            autocamper.setLength(rs.getInt("length"));
            autocamper.setWidth(rs.getInt("width"));
            autocamper.setArea(rs.getInt("area"));
            autocamper.setDescription(rs.getString("description"));
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
        prep.setString(4, autocamper.getBrand());
        prep.setString(5, autocamper.getModel());
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
        prep.setString(4,autocamper.getBrand());
        prep.setString(5,autocamper.getModel());
        prep.setInt(6, autocamper.getId());
        prep.executeUpdate();
        return true;
    }

    public boolean delete(Autocamper autocamper) throws SQLException {
        String delete = "DELETE FROM autocamper WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.executeUpdate();
        return true;
    }
}
