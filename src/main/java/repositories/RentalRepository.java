package repositories;

import models.Rental;
import org.springframework.beans.factory.annotation.Value;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class RentalRepository {
    Connection conn;

    public RentalRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
    }

    public Rental getById(int id) {
        Rental rentalToReturn = new Rental();

        try {
            PreparedStatement getSingleRental = conn.prepareStatement
                    ("SELECT * FROM rental WHERE rental_id = ?");
            getSingleRental.setInt(1, id);
            ResultSet rs = getSingleRental.executeQuery();

            while (rs.next()) {
                rentalToReturn.setId(rs.getInt(1));
                rentalToReturn.setAccumulatedPrice(rs.getInt(2));
                rentalToReturn.setStartDate(rs.getDate(3).toLocalDate());
                rentalToReturn.setEndDate(rs.getDate(4).toLocalDate());
                rentalToReturn.setLongPickUpLoc(rs.getLong(5));
                rentalToReturn.setLatPickUpLoc(rs.getLong(6));
                rentalToReturn.setLongDropOffLoc(rs.getLong(7));
                rentalToReturn.setLatDropOffLoc(rs.getLong(8));
                rentalToReturn.setAutocamperId(rs.getInt(9));
                rentalToReturn.setMaintenanceId(rs.getInt(10));
                rentalToReturn.setCustomerId(rs.getInt(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalToReturn;
    }

    public Rental[] getBytParameter(String parameter, String... columns){
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
                Rental addRentalToList = new Rental();
                addRentalToList.setId(rs.getInt(1));
                addRentalToList.setAccumulatedPrice(rs.getInt(2));
                addRentalToList.setStartDate(rs.getDate(3).toLocalDate());
                addRentalToList.setEndDate(rs.getDate(4).toLocalDate());
                addRentalToList.setLongPickUpLoc(rs.getLong(5));
                addRentalToList.setLatPickUpLoc(rs.getLong(6));
                addRentalToList.setLongDropOffLoc(rs.getLong(7));
                addRentalToList.setLatDropOffLoc(rs.getLong(8));
                addRentalToList.setAutocamperId(rs.getInt(9));
                addRentalToList.setMaintenanceId(rs.getInt(10));
                addRentalToList.setCustomerId(rs.getInt(11));
                rentalList.add(addRentalToList);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rentalList.toArray(new Rental[rentalList.size()]);
    }

    public Rental[] getAll(){
        ArrayList<Rental> rentalList = new ArrayList<Rental>();
        try {
            PreparedStatement getAllRentals = conn.prepareStatement
                    ("SELECT * FROM rental");
            ResultSet rs = getAllRentals.executeQuery();

            while (rs.next()) {
                Rental addRentalToList = new Rental();
                addRentalToList.setId(rs.getInt(1));
                addRentalToList.setAccumulatedPrice(rs.getInt(2));
                addRentalToList.setStartDate(rs.getDate(3).toLocalDate());
                addRentalToList.setEndDate(rs.getDate(4).toLocalDate());
                addRentalToList.setLongPickUpLoc(rs.getLong(5));
                addRentalToList.setLatPickUpLoc(rs.getLong(6));
                addRentalToList.setLongDropOffLoc(rs.getLong(7));
                addRentalToList.setLatDropOffLoc(rs.getLong(8));
                addRentalToList.setAutocamperId(rs.getInt(9));
                addRentalToList.setMaintenanceId(rs.getInt(10));
                addRentalToList.setCustomerId(rs.getInt(11));
                rentalList.add(addRentalToList);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return rentalList.toArray(new Rental[rentalList.size()]);
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
            createRental.setInt(9, rental.getMaintenanceId());
            createRental.setInt(10, rental.getCustomerId());
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
            updateRental.setInt(9, rental.getMaintenanceId());
            updateRental.setInt(10, rental.getCustomerId());
            updateRental.setInt(11, rental.getId());
            updateRental.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        try {
            PreparedStatement deleteRental = conn.prepareStatement
                    ("DELETE FROM rental WHERE id = ?");
            deleteRental.setInt(1, id);
            deleteRental.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

}
