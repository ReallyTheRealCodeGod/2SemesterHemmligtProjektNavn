package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Rental;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class RentalRepository implements IRepository<Rental>{
    Connection conn;

    public RentalRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
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
            PreparedStatement createRental = conn.prepareStatement
                    ("INSERT INTO rental VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                            + Statement.RETURN_GENERATED_KEYS);
            createRental.setInt(1, rental.getAccumulatedPrice());
            createRental.setDate(2, Date.valueOf(rental.getStartDate()));
            createRental.setDate(3,Date.valueOf(rental.getEndDate()));
            createRental.setLong(4,rental.getLongPickUpLoc());
            createRental.setLong(5,rental.getLatPickUpLoc());
            createRental.setLong(6, rental.getLongDropOffLoc());
            createRental.setLong(7, rental.getLatDropOffLoc());
            createRental.setInt(8, rental.getAutocamperId());
            createRental.setInt(9, rental.getCustomerId());
            createRental.setInt(10, rental.getMaintenanceId());
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
                            "acc_price = ?, " +
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
            updateRental.setInt(1, rental.getAccumulatedPrice());
            updateRental.setDate(2, Date.valueOf(rental.getStartDate()));
            updateRental.setDate(3,Date.valueOf(rental.getEndDate()));
            updateRental.setLong(4,rental.getLongPickUpLoc());
            updateRental.setLong(5,rental.getLatPickUpLoc());
            updateRental.setLong(6, rental.getLongDropOffLoc());
            updateRental.setLong(7, rental.getLatDropOffLoc());
            updateRental.setInt(8, rental.getAutocamperId());
            updateRental.setInt(9, rental.getCustomerId());
            updateRental.setInt(10, rental.getMaintenanceId());
            updateRental.setInt(11, rental.getId());
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
        rental.setAccumulatedPrice(rs.getInt(2));
        rental.setStartDate(rs.getDate(3).toLocalDate());
        rental.setEndDate(rs.getDate(4).toLocalDate());
        rental.setLongPickUpLoc(rs.getLong(5));
        rental.setLatPickUpLoc(rs.getLong(6));
        rental.setLongDropOffLoc(rs.getLong(7));
        rental.setLatDropOffLoc(rs.getLong(8));
        rental.setAutocamperId(rs.getInt(9));
        rental.setMaintenanceId(rs.getInt(10));
        rental.setCustomerId(rs.getInt(11));
        return rental;
    }

}
