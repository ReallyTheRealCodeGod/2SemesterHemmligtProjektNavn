package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class RentalRepository implements IRepository<Rental>{
    Connection conn;

    public RentalRepository() throws SQLException {
        this.conn = JDBCConnection.getInstance().getConnection();
    }

    public Rental getById(int id) {
        Rental rentalToReturn = new Rental();

        try {
            PreparedStatement getSingleRental = conn.prepareStatement
                    ("SELECT * FROM rental WHERE id = ?");
            getSingleRental.setInt(1, id);
            ResultSet rs = getSingleRental.executeQuery();

            while (rs.next()) {
                rentalToReturn = load(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalToReturn;
    }

    public  ArrayList<Rental> getByParameter(String parameter, String... columns){
        ArrayList<Rental> rentalList = new ArrayList<Rental>();

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(String s: columns){
            if(sb.length() != 0){
                sb.append(", ");
            }
            sb.append(s);
            i++;
        }

        try {
            PreparedStatement getAllRentals = conn.prepareStatement(
                    "SELECT * FROM rental" +
                    "WHERE "+sb+" LIKE %?%");
            getAllRentals.setString(1,parameter);
            ResultSet rs = getAllRentals.executeQuery();

            while (rs.next()) {
                rentalList.add(load(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rentalList;
    }

    public  ArrayList<Rental> getAll(){
        ArrayList<Rental> rentalList = new ArrayList<Rental>();
        try {
            PreparedStatement getAllRentals = conn.prepareStatement
                    ("SELECT * FROM rental");
            ResultSet rs = getAllRentals.executeQuery();

            while (rs.next()) {
                rentalList.add(load(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rentalList;
    }

    public Rental create(Rental rental){
        try {
            String sql ="INSERT INTO rental " +
                    "(id, " +
                    "start_date, " +
                    "end_date, " +
                    "lon_pickUp_loc, " +
                    "lat_pickUp_loc, " +
                    "lon_dropOff_loc, " +
                    "lat_dropOff_loc, " +
                    "fk_autocamper_id, " +
                    "fk_customer_id, " +
                    "fk_maintenance_id) " +
                    " VALUES " +
                    " (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";
            PreparedStatement createRental = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            createRental.setDate(1, Date.valueOf(rental.getStartDate()));
            createRental.setDate(2,Date.valueOf(rental.getEndDate()));
            createRental.setLong(3,rental.getLongPickUpLoc());
            createRental.setLong(4,rental.getLatPickUpLoc());
            createRental.setLong(5, rental.getLongDropOffLoc());
            createRental.setLong(6, rental.getLatDropOffLoc());
            createRental.setInt(7, rental.getAutocamperId());
            createRental.setInt(8, rental.getCustomerId());
            createRental.executeUpdate();
            ResultSet rs = createRental.getGeneratedKeys();
            rs.next();
            rental.setId((rs.getInt(1)));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rental;
    }

    public boolean update(Rental rental){
        try {
            PreparedStatement updateRental = conn.prepareStatement
                    ("UPDATE rental SET " +
                            "start_date = ?, " +
                            "end_date = ?, " +
                            "lon_pickUp_loc = ?, " +
                            "lat_pickUp_loc = ?, " +
                            "lon_dropOff_loc = ?, " +
                            "lat_dropOff_loc = ?, " +
                            "fk_autocamper_id = ?, " +
                            "fk_customer_id = ?, " +
                            "fk_maintenance_id = ?" +

                            "WHERE rental_id = ?");
            updateRental.setDate(1, Date.valueOf(rental.getStartDate()));
            updateRental.setDate(2,Date.valueOf(rental.getEndDate()));
            updateRental.setLong(3,rental.getLongPickUpLoc());
            updateRental.setLong(4,rental.getLatPickUpLoc());
            updateRental.setLong(5, rental.getLongDropOffLoc());
            updateRental.setLong(6, rental.getLatDropOffLoc());
            updateRental.setInt(7, rental.getAutocamperId());
            updateRental.setInt(8, rental.getCustomerId());
            updateRental.setInt(9, rental.getMaintenanceId());
            updateRental.setInt(10, rental.getId());
            updateRental.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Rental rental){
        try {
            PreparedStatement deleteRental = conn.prepareStatement
                    ("DELETE FROM rental WHERE id = ?");
            deleteRental.setInt(1, rental.getId());
            deleteRental.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

    private Rental load(ResultSet rs) throws SQLException{
        Rental rental = new Rental();
        rental.setId(rs.getInt(1));
        rental.setStartDate(rs.getDate(2).toLocalDate());
        rental.setEndDate(rs.getDate(3).toLocalDate());
        rental.setLongPickUpLoc(rs.getLong(4));
        rental.setLatPickUpLoc(rs.getLong(5));
        rental.setLongDropOffLoc(rs.getLong(6));
        rental.setLatDropOffLoc(rs.getLong(7));
        rental.setAutocamperId(rs.getInt(8));
        rental.setMaintenanceId(rs.getInt(9));
        rental.setCustomerId(rs.getInt(10));
        return rental;
    }

}
