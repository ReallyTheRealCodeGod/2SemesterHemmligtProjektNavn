package repositories;

import models.Season;
import models.VariablePrices;
import org.springframework.beans.factory.annotation.Value;
import utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class VariablePricesRepository {

    private Connection conn;
    @Value("db.user")
    private String username;
    @Value("db.password")
    private String password;
    @Value("db.url")
    private String url;

    public VariablePricesRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
    }

    public VariablePrices getById(int id){
        VariablePrices varPriceToReturn = new VariablePrices();
        try {
            PreparedStatement getSingleVarPrice = conn.prepareStatement
                    ("SELECT * FROM variable_prices WHERE variable_prices_id = ?");
            getSingleVarPrice.setInt(1, id);
            ResultSet rs = getSingleVarPrice.executeQuery();

            while (rs.next()) {
                varPriceToReturn.setExcessKilometerPrice(rs.getInt(1));
                varPriceToReturn.setFuelPrice(rs.getInt(2));
                varPriceToReturn.setDropOffKilometerPrice(rs.getInt(3));
                varPriceToReturn.setPickUpKilometerPrice(rs.getInt(4));
                varPriceToReturn.setCleaningMaxPrice(rs.getInt(5));
                varPriceToReturn.setCleaningMinPrice(rs.getInt(6));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return varPriceToReturn;
    }

    public void getByParameter(String parameter){

    }

    public VariablePrices[] getAll(){
        ArrayList<VariablePrices> priceList = new ArrayList<VariablePrices>();
        try {
            PreparedStatement getAllVariablePrices = conn.prepareStatement
                    ("SELECT * FROM variable_prices");
            ResultSet rs = getAllVariablePrices.executeQuery();

            while (rs.next()) {
                VariablePrices variablePrices = new VariablePrices();
                variablePrices.setVariablePricesId(rs.getInt(1));
                variablePrices.setExcessKilometerPrice(rs.getInt(2));
                variablePrices.setFuelPrice(rs.getInt(3));
                variablePrices.setDropOffKilometerPrice(rs.getInt(4));
                variablePrices.setPickUpKilometerPrice(rs.getInt(5));
                variablePrices.setCleaningMaxPrice(rs.getInt(6));
                variablePrices.setCleaningMinPrice(rs.getInt(7));
                priceList.add(variablePrices);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return priceList.toArray(new VariablePrices[priceList.size()]);
    }

    public VariablePrices create(VariablePrices variablePrices){
        try {
            PreparedStatement createVariablePrice = conn.prepareStatement
                    ("INSERT INTO variable_prices " +
                            "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) " + Statement.RETURN_GENERATED_KEYS);
            createVariablePrice.setInt(1, variablePrices.getExcessKilometerPrice());
            createVariablePrice.setInt(2,variablePrices.getFuelPrice());
            createVariablePrice.setInt(3, variablePrices.getDropOffKilometerPrice());
            createVariablePrice.setInt(4, variablePrices.getPickUpKilometerPrice());
            createVariablePrice.setInt(5, variablePrices.getCleaningMaxPrice());
            createVariablePrice.setInt(6, variablePrices.getCleaningMinPrice());
            createVariablePrice.executeUpdate();
            ResultSet rs = createVariablePrice.getGeneratedKeys();
            rs.next();
            variablePrices.setVariablePricesId(rs.getInt(1));
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return variablePrices;
    }

    public boolean update(VariablePrices variablePrices){
        try {
            PreparedStatement updateVarPrice = conn.prepareStatement
                    ("UPDATE variable_prices SET  " +
                            "excessKilometer_price = ?, " +
                            "fuel_price = ?, " +
                            "dropOffKilometer_price = ?, " +
                            "pickUpKilometer_price = ?, " +
                            "cleaning_max_price = ?, " +
                            "cleaning_min_price = ?, " +
                            "WHERE variable_prices_id = ?");

            updateVarPrice.setInt(1, variablePrices.getExcessKilometerPrice());
            updateVarPrice.setInt(2, variablePrices.getFuelPrice());
            updateVarPrice.setInt(3, variablePrices.getDropOffKilometerPrice());
            updateVarPrice.setInt(4, variablePrices.getPickUpKilometerPrice());
            updateVarPrice.setInt(5, variablePrices.getCleaningMaxPrice());
            updateVarPrice.setInt(6, variablePrices.getCleaningMinPrice());
            updateVarPrice.setInt(7, variablePrices.getVariablePricesId());

            updateVarPrice.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id){
        try {
            PreparedStatement deleteVarPrice = conn.prepareStatement
                    ("DELETE FROM variable_prices WHERE variable_prices_id = ?");
            deleteVarPrice.setInt(1, id);
            deleteVarPrice.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

    private Season[] getSeasons(){
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM seasons");
            ResultStprep.executeQuery();

            return
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
}
