package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Season;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class VariablePricesRepository{
    Connection conn;

    public VariablePricesRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
    }

    public VariablePrices getPrices(){
        VariablePrices varPriceToReturn = new VariablePrices();
        try {
            PreparedStatement getSingleVarPrice = conn.prepareStatement
                    ("SELECT * FROM variable_prices WHERE id = 1");
            ResultSet rs = getSingleVarPrice.executeQuery();

            while (rs.next()) {
                varPriceToReturn.setExcessKilometerPrice(rs.getInt("excessKilometer_price"));
                varPriceToReturn.setFuelPrice(rs.getInt("fuel_price"));
                varPriceToReturn.setDropOffKilometerPrice(rs.getInt("dropOffKilometer_price"));
                varPriceToReturn.setPickUpKilometerPrice(rs.getInt("pickUpKilometer_price"));
                varPriceToReturn.setCleaningMaxPrice(rs.getInt("cleaning_max_price"));
                varPriceToReturn.setCleaningMinPrice(rs.getInt("cleaning_min_price"));
                varPriceToReturn.setSeasons(getSeasons());
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return varPriceToReturn;
    }
    public boolean editPrices(VariablePrices variablePrices){
        try {
            String sql = "UPDATE variable_prices SET  excessKilometer_price = ?, fuel_price = ?, dropOffKilometer_price = ?," +
                    " pickUpKilometer_price = ?, cleaning_max_price = ?, cleaning_min_price = ? WHERE id=1";
            PreparedStatement updateVarPrice = conn.prepareStatement(sql);

            updateVarPrice.setInt(1, variablePrices.getExcessKilometerPrice());
            updateVarPrice.setInt(2, variablePrices.getFuelPrice());
            updateVarPrice.setInt(3, variablePrices.getDropOffKilometerPrice());
            updateVarPrice.setInt(4, variablePrices.getPickUpKilometerPrice());
            updateVarPrice.setInt(5, variablePrices.getCleaningMaxPrice());
            updateVarPrice.setInt(6, variablePrices.getCleaningMinPrice());

            updateVarPrice.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

    private Season[] getSeasons(){
        ArrayList<Season> list = new ArrayList<>();
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM season");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                Season s = new Season();
                s.setName(rs.getString("name"));
                s.setSurchargePercentage(rs.getInt("surcharge_percentage"));
                s.setStartDate(rs.getDate("start_date").toLocalDate());
                s.setEndDate(rs.getDate("end_date").toLocalDate());
                list.add(s);
            }
            return list.toArray(new Season[list.size()]);
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }

    /*tænker det ikke rigtig den her er nødvendigt siden priserne kun har en række;
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
    }*/

    /*
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
    }*/

    }
}
