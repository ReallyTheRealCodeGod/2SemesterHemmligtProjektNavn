package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Season;
import com.automobil.webdemoautomobil.models.VariablePrices;
import com.automobil.webdemoautomobil.utility.JDBCConnection;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;

import java.sql.*;
import java.util.ArrayList;

public class VariablePricesRepository{
    Connection conn;

    public VariablePricesRepository() throws SQLException {
        this.conn = JDBCConnection.getInstance().getConnection();
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

    private ArrayList<Season> getSeasons(){
        ArrayList<Season> list = new ArrayList<>();
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM season");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                Season s = new Season();
                s.setName(rs.getString("season_name"));
                s.setSurchargePercentage(rs.getInt("charge_percentage"));
                s.setStartDate(rs.getDate("start_date").toLocalDate());
                s.setEndDate(rs.getDate("end_date").toLocalDate());
                list.add(s);
            }
            return list;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
    public void addSeason(Season season){
        try {
            PreparedStatement prep = conn.prepareStatement("INSERT INTO season (season_name,  charge_percentage, start_date, end_date) values (?, ?, ?, ?)");
            prep.setString(1, season.getName());
            prep.setInt(2, season.getSurchargePercentage());
            prep.setDate(3, Date.valueOf(season.getStartDate()));
            prep.setDate(4, Date.valueOf(season.getEndDate()));
            prep.executeUpdate();
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }

    public void deleteSeason(String name){
        try {
            PreparedStatement prep = conn.prepareStatement("DELETE FROM season where season_name = ?");
            prep.setString(1, name);
            prep.executeUpdate();
        }catch(SQLException sql){
            sql.printStackTrace();
        }

    }
}
