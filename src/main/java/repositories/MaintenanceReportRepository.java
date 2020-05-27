package repositories;

import java.util.HashMap;
import models.Autocamper;
import models.AutocamperType;
import models.BuiltInFeature;
import models.MaintenanceReport;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;


public class MaintenanceReportRepository {
    Connection connection;

    public MaintenanceReportRepository() {
        connection = JDBCConnection.getDatabaseConnection();
    }

    public MaintenanceReport getById(int id) throws SQLException {
        String sql = "SELECT * \n" +
                "FROM maintenance m\n" +
                "WHERE maintenance_id = ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setInt(1, id);
        MaintenanceReport maintenance = new MaintenanceReport();
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            maintenance.setId(rs.getInt("id"));
            maintenance.setFuelGauge(rs.getInt("fuel"));
            maintenance.setMileage(rs.getInt("mileage"));
            maintenance.setCleaningPrice(rs.getInt("price"));
            maintenance.setMaintenanceNote(rs.getString("note"));
            HashMap<String, Integer> parts = new HashMap<>();
            parts.put("frame", rs.getInt("frame"));
            parts.put("wheels", rs.getInt("wheels"));
            parts.put("lights", rs.getInt("lights"));
            parts.put("oil", rs.getInt("oil"));
            parts.put("battery", rs.getInt("battery"));
            parts.put("interior", rs.getInt("interior"));
            parts.put("coolingflued", rs.getInt("coolingflued"));
            parts.put("brakes", rs.getInt("brakes"));
            parts.put("suspention", rs.getInt("suspention"));
            maintenance.setPartStatus(parts);
            maintenance.setRepairCost(rs.getInt("repair_cost"));
            maintenance.setDate(rs.getDate("maintenance_date").toLocalDate());
            maintenance.setAutocamperId(rs.getInt("autocamperID"));

        }
        return maintenance;
    }

    public MaintenanceReport create(MaintenanceReport maintenance) throws SQLException {
        String create = "INSERT INTO maintenance(" +
                "maintenance_id," +
                " fuel_gauge ," +
                " mileage," +
                "cleaning_price," +
                "maintenance_notes," +
                "frame," +
                "wheels," +
                "lights," +
                "oil," +
                "battery," +
                "interior," +
                "coolingflued," +
                "brakes," +
                "suspention," +
                "maintenance_date," +
                "fk_autocamper_id))" +
                " Values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, maintenance.getFuelGauge());
        prep.setInt(2, maintenance.getMileage());
        prep.setInt(3, maintenance.getCleaningPrice());
        prep.setString(4, maintenance.getMaintenanceNote());
        prep.setInt(5, maintenance.getPartStatus().get("frame"));
        prep.setInt(6, maintenance.getPartStatus().get("wheels"));
        prep.setInt(7, maintenance.getPartStatus().get("lights"));
        prep.setInt(8, maintenance.getPartStatus().get("oil"));
        prep.setInt(9, maintenance.getPartStatus().get("battery"));
        prep.setInt(10, maintenance.getPartStatus().get("interior"));
        prep.setInt(11, maintenance.getPartStatus().get("coolingflued"));
        prep.setInt(12, maintenance.getPartStatus().get("brakes"));
        prep.setInt(13, maintenance.getPartStatus().get("suspention"));
        prep.setInt(14, maintenance.getRepairCost());
        prep.setDate(15, Date.valueOf(maintenance.getDate()));
        prep.setInt(16, maintenance.getAutocamperId());
        prep.executeUpdate();
        ResultSet rs = prep.getGeneratedKeys();
        rs.next();
        maintenance.setId(rs.getInt(1));
        return maintenance;
    }

    public boolean update(MaintenanceReport maintenance) throws SQLException {
        try{
        String update = "UPDATE maintenance " +
                "SET maintenance_id = ?," +
                " fuel_gauge = ?," +
                " mileage = ?," +
                "cleaning_price = ?," +
                "maintenance_notes = ?," +
                "frame = ?," +
                "wheels = ?," +
                "lights = ?," +
                "oil = ?," +
                "battery = ?," +
                "interior = ?," +
                "coolingflued = ?," +
                "brakes = ?," +
                "suspention = ?," +
                "maintenance_date = ?," +
                "fk_autocamper_id = ?))" +
                "WHERE id = ?;";
        PreparedStatement prep = connection.prepareStatement(update);
        prep.setInt(1, maintenance.getFuelGauge());
        prep.setInt(2, maintenance.getMileage());
        prep.setInt(3, maintenance.getCleaningPrice());
        prep.setString(4, maintenance.getMaintenanceNote());
        prep.setInt(5, maintenance.getPartStatus().get("frame"));
        prep.setInt(6, maintenance.getPartStatus().get("wheels"));
        prep.setInt(7, maintenance.getPartStatus().get("lights"));
        prep.setInt(8, maintenance.getPartStatus().get("oil"));
        prep.setInt(9, maintenance.getPartStatus().get("battery"));
        prep.setInt(10, maintenance.getPartStatus().get("interior"));
        prep.setInt(11, maintenance.getPartStatus().get("coolingflued"));
        prep.setInt(12, maintenance.getPartStatus().get("brakes"));
        prep.setInt(13, maintenance.getPartStatus().get("suspention"));
        prep.setInt(14, maintenance.getRepairCost());
        prep.setDate(15, Date.valueOf(maintenance.getDate()));
        prep.setInt(16, maintenance.getAutocamperId());
        prep.setInt(17, maintenance.getId());
        prep.executeUpdate();
        return true;
    }catch(SQLException sql){
        sql.printStackTrace();
        return false;
    }

}

    public boolean delete(MaintenanceReport maintenance) throws SQLException {
        String delete = "DELETE FROM maintenance WHERE id = ?";
        PreparedStatement prep = connection.prepareStatement(delete);
        prep.setInt(1, maintenance.getId());
        prep.executeUpdate();
        return true;
    }
}