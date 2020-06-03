package com.automobil.webdemoautomobil.repositories;

import java.time.LocalDate;
import java.util.HashMap;

import com.automobil.webdemoautomobil.models.MaintenanceReport;
import com.automobil.webdemoautomobil.utility.JDBCConnection;
import org.springframework.ui.Model;

import java.sql.*;
import java.util.ArrayList;


public class MaintenanceReportRepository implements IRepository<MaintenanceReport>{
    Connection connection;

    public MaintenanceReportRepository() throws SQLException {
        connection = JDBCConnection.getInstance().getConnection();
    }

    public MaintenanceReport getById(int id) {
        try {
            String sql = "SELECT * \n" +
                    "FROM maintenance \n" +
                    "WHERE id = ?;";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            rs.next();
            return load(rs);
        } catch (SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }

    public  ArrayList<MaintenanceReport> getAll() {
        ArrayList<MaintenanceReport> list = new ArrayList<>();
        try {
            String sql = "SELECT * \n" +
                    "FROM maintenance";
            PreparedStatement prep = connection.prepareStatement(sql);
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

    public  ArrayList<MaintenanceReport> getByParameter(String parameter, String... columns){
        ArrayList<MaintenanceReport> list = new ArrayList<>();
        try{

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for(String s: columns){
                if(sb.length() != 0){
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            String select = "SELECT * FROM customer" +
                    "WHERE" + sb + " LIKE %?% ";
            PreparedStatement prep = connection.prepareStatement(select);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                list.add(load(rs));
            }
            return list;
        }catch(SQLException sql){
        sql.printStackTrace();
        return null;}
}

    public MaintenanceReport create(MaintenanceReport maintenance){
        try {
            String create = "INSERT INTO maintenance(" +
                    "id," +
                    " fuel_gauge ," +
                    " mileage," +
                    "cleaning_price," +
                    "maintenance_notes," +
                    "cleaning_notes," +
                    "frame," +
                    "wheels," +
                    "lights," +
                    "oil," +
                    "battery," +
                    "interior," +
                    "coolingflued," +
                    "brakes," +
                    "suspention," +
                    "repair_cost," +
                    "maintenance_date," +
                    "fk_autocamper_id)" +
                    " Values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, maintenance.getFuelGauge());
            prep.setInt(2, maintenance.getMileage());
            prep.setInt(3, maintenance.getCleaningPrice());
            prep.setString(4, maintenance.getMaintenanceNote());
            prep.setString(5, maintenance.getCleaningNote());
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
            prep.setDate(15, Date.valueOf(LocalDate.now()));
            prep.setInt(16, maintenance.getAutocamperId());
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            rs.next();
            maintenance.setId(rs.getInt(1));
            return maintenance;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public boolean update(MaintenanceReport maintenance){
        try{
        String update = "UPDATE maintenance " +
                "SET id = ?," +
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
        prep.setString(5, maintenance.getCleaningNote());
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

    public boolean delete(MaintenanceReport maintenance){
        try{
            String delete = "DELETE FROM maintenance WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(delete);
            prep.setInt(1, maintenance.getId());
            prep.executeUpdate();
            return true;
        }catch(SQLException sql){
            sql.printStackTrace();
            return false;
        }
    }

    private MaintenanceReport load(ResultSet rs) throws SQLException{
        MaintenanceReport maintenance = new MaintenanceReport();
        maintenance.setId(rs.getInt("id"));
        maintenance.setFuelGauge(rs.getInt("fuel_gauge"));
        maintenance.setMileage(rs.getInt("mileage"));
        maintenance.setCleaningPrice(rs.getInt("cleaning_price"));
        maintenance.setMaintenanceNote(rs.getString("maintenance_notes"));
        maintenance.setCleaningNote(rs.getString("cleaning_notes"));
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
        maintenance.setAutocamperId(rs.getInt("fk_autocamper_id"));
        return maintenance;
    }
}