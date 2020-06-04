package com.automobil.webdemoautomobil.repositories;

import com.automobil.webdemoautomobil.models.Bill;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;

public class BillRepository implements IRepository<Bill>{
    Connection connection;
    public BillRepository() throws SQLException {
        connection = JDBCConnection.getInstance().getConnection();
    }

    public Bill getById(int id){
        try{
            String select = "SELECT * FROM bill" +
                    " WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(select);

            prep.setInt(1,id);
            ResultSet rs = prep.executeQuery();
            Bill bill = null;
            while(rs.next()) {
                bill = load(rs);
            }
            return bill;
        }catch(SQLException sql) {
            sql.printStackTrace();
            return null;
        }
    }

    public  ArrayList<Bill> getByParameter(String parameter, String... columns){
        try{
            ArrayList<Bill> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for(String s: columns){
                if(sb.length() != 0){
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            String select = "SELECT * FROM bill" +
                    "WHERE" + sb + " LIKE %?% ";

            PreparedStatement prep = connection.prepareStatement(select);
            prep.setString(1, parameter);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                list.add(load(rs));
            }
            return list;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public ArrayList<Bill> getAll() {
        try{
            ArrayList<Bill> list = new ArrayList<>();

            String select = "SELECT * FROM bill";

            PreparedStatement prep = connection.prepareStatement(select);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                list.add(load(rs));
            }
            return list;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public Bill create(Bill bill) {
        try {
            String create = "INSERT INTO bill(id, billing_date, cus_first_name, cus_last_name," +
                    " rental_cost, accessory_cost, " +
                    "postal_code, street_name, street_nr, apartment_floor) " +
                    "Values (DEFAULT, ?,?,?,?,?,?,?,?,?)";
            PreparedStatement prep = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            prep.setDate(1, Date.valueOf(bill.getBillingDate()));
            prep.setString(2, bill.getCustomerFirstName());
            prep.setString(3, bill.getCustomerLastName());
            prep.setInt(4, bill.getRentalCost());
            prep.setInt(5, bill.getAccessoryCost());
            prep.setInt(6, bill.getPostalCode());
            prep.setString(7, bill.getStreetName());
            prep.setString(8, bill.getStreetNr());
            prep.setString(9, bill.getApartmentFloor());
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();
            System.out.println(bill);
            rs.next();
            bill.setId(rs.getInt(1));
            return bill;
        }catch(SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }

    public boolean update(Bill bill) {
        try {
            String update = "UPDATE bill " +
                    "SET billing_date = ?," +
                    "cus_first_name = ?, " +
                    "cus_last_name = ?, " +
                    "postal_code = ?, " +
                    "street_name = ?, " +
                    "street_nr = ?, " +
                    "apartment_floor = ?, " +
                    "rental_cost = ?, " +
                    "accessory_cost = ?, " +
                    "WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(update);
            prep.setDate(1, Date.valueOf(bill.getBillingDate()));
            prep.setString(2, bill.getCustomerFirstName());
            prep.setString(3, bill.getCustomerLastName());
            prep.setInt(4, bill.getPostalCode());
            prep.setString(5, bill.getStreetName());
            prep.setString(6, bill.getStreetNr());
            prep.setString(7, bill.getApartmentFloor());
            prep.setInt(8, bill.getRentalCost());
            prep.setInt(9, bill.getAccessoryCost());
            prep.setInt(10, bill.getId());
            prep.executeUpdate();
            return true;
        }catch(SQLException sql){
            sql.printStackTrace();
            return false;
        }
    }

    public boolean delete(Bill bill) {
        try{
            String delete = "DELETE FROM bill WHERE id = ?";
            PreparedStatement prep = connection.prepareStatement(delete);
            prep.setInt(1, bill.getId());
            prep.executeUpdate();
            return true;
        }catch(SQLException sql){
            sql.printStackTrace();
            return false;
        }
    }

    private Bill load(ResultSet rs) throws SQLException{
        Bill bill = new Bill();
        bill.setId(rs.getInt("id"));
        bill.setBillingDate(rs.getDate("billing_date").toLocalDate());
        bill.setCustomerFirstName(rs.getString("cus_first_name"));
        bill.setCustomerLastName(rs.getString("cus_last_name"));

        bill.setRentalCost(rs.getInt("rental_cost"));
        bill.setAccessoryCost(rs.getInt("accessory_cost"));

        bill.setPostalCode(rs.getInt("postal_code"));
        bill.setStreetName(rs.getString("street_name"));
        bill.setStreetNr(rs.getString("street_nr"));
        bill.setApartmentFloor(rs.getString("apartment_floor"));
        return bill;
    }
}

