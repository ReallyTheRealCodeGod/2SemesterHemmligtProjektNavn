package com.automobil.webdemoautomobil.repositories;


import com.automobil.webdemoautomobil.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import com.automobil.webdemoautomobil.utility.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IRepository<Customer>{

    private Connection conn;

    public CustomerRepository(){
        this.conn = JDBCConnection.getDatabaseConnection();
    }

    public Customer getById(int id){
        Customer customerToReturn = new Customer();
        try {
            PreparedStatement getSingleCustomer = conn.prepareStatement
                    ("SELECT * FROM customer WHERE id = ?");
            getSingleCustomer.setInt(1, id);
            ResultSet rs = getSingleCustomer.executeQuery();

            while (rs.next()) {
                customerToReturn.setId(rs.getInt(1));
                customerToReturn.setLastName(rs.getString(2));
                customerToReturn.setFirstName(rs.getString(3));
                customerToReturn.setEmail(rs.getString(4));
                customerToReturn.setCprNr(rs.getInt(5));
                customerToReturn.setPostalCode(rs.getInt(6));
                customerToReturn.setStreetName(rs.getString(7));
                customerToReturn.setHouseNr(rs.getString(8));
                customerToReturn.setFloor(rs.getString(9));
                customerToReturn.setCardNr(rs.getInt(10));
                customerToReturn.setCardCVV(rs.getInt(11));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return customerToReturn;
    }

    public  ArrayList<Customer> getByParameter(String parameter, String... columns){
        try {
            ArrayList<Customer> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String s : columns) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(s);
                i++;
            }

            String select = "SELECT * FROM customer" +
                    "WHERE" + sb + " LIKE %?% ";

            PreparedStatement prep = conn.prepareStatement(select);
            prep.setString(1, parameter);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Customer sampleCustomer = new Customer();
                sampleCustomer.setId(rs.getInt(1));
                sampleCustomer.setLastName(rs.getString(2));
                sampleCustomer.setFirstName(rs.getString(3));
                sampleCustomer.setEmail(rs.getString(4));
                sampleCustomer.setPhoneNr(rs.getString(5));
                sampleCustomer.setCprNr(rs.getInt(6));
                sampleCustomer.setPostalCode(rs.getInt(7));
                sampleCustomer.setStreetName(rs.getString(8));
                sampleCustomer.setHouseNr(rs.getString(9));
                sampleCustomer.setFloor(rs.getString(10));
                sampleCustomer.setCardNr(rs.getInt(1));
                sampleCustomer.setCardCVV(rs.getInt(12));

                list.add(sampleCustomer);
            }
            return list;
        }catch (SQLException sql){
            sql.printStackTrace();
            return null;
        }

    }

    public  ArrayList<Customer> getAll(){
        ArrayList<Customer> allCustomers = new ArrayList<Customer>();
        try {
            PreparedStatement getAllCustomers = conn.prepareStatement
                    ("SELECT * FROM customer");
            ResultSet rs = getAllCustomers.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Customer sampleCustomer = new Customer();
                    sampleCustomer.setId(rs.getInt(1));
                    sampleCustomer.setLastName(rs.getString(2));
                    sampleCustomer.setFirstName(rs.getString(3));
                    sampleCustomer.setEmail(rs.getString(4));
                    sampleCustomer.setPhoneNr(rs.getString(5));
                    sampleCustomer.setCprNr(rs.getInt(6));
                    sampleCustomer.setPostalCode(rs.getInt(7));
                    sampleCustomer.setStreetName(rs.getString(8));
                    sampleCustomer.setHouseNr(rs.getString(9));
                    sampleCustomer.setFloor(rs.getString(10));
                    sampleCustomer.setCardNr(rs.getInt(1));
                    sampleCustomer.setCardCVV(rs.getInt(12));
                    allCustomers.add(sampleCustomer);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allCustomers;
    }

    public Customer create(Customer customer){
        try {
            PreparedStatement createCustomer = conn.prepareStatement
                    ("INSERT INTO customer " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" + Statement.RETURN_GENERATED_KEYS);
            createCustomer.setString(1, customer.getLastName());
            createCustomer.setString(2,customer.getFirstName());
            createCustomer.setString(3, customer.getEmail());
            createCustomer.setString(4, customer.getPhoneNr());
            createCustomer.setInt(5, customer.getCprNr());
            createCustomer.setInt(6, customer.getPostalCode());
            createCustomer.setString(7, customer.getStreetName());
            createCustomer.setString(8, customer.getHouseNr());
            createCustomer.setString(9, customer.getFloor());
            createCustomer.setInt(10, customer.getCardNr());
            createCustomer.setInt(11, customer.getCardCVV());
            createCustomer.executeUpdate();
            ResultSet rs = createCustomer.getGeneratedKeys();
            rs.next();
            customer.setId(rs.getInt(1));
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return customer;
    }

    public boolean update(Customer customer){
        try {
            PreparedStatement updateCustomer = conn.prepareStatement
                    ("UPDATE customer SET  " +
                    "last_name = ?, first_name = ?, " +
                    "email = ?, phone_nr = ?, " +
                    "cpr_nr = ?, postalcode = ?, " +
                    "street_name = ?, street_nr = ?, " +
                    "appartment_floor = ?, card_nr = ?, " +
                    "cvv = ? " +
                    "WHERE id = ?");
            updateCustomer.setString(1, customer.getLastName());
            updateCustomer.setString(2, customer.getFirstName());
            updateCustomer.setString(3, customer.getEmail());
            updateCustomer.setString(4, customer.getPhoneNr());
            updateCustomer.setInt(5, customer.getCprNr());
            updateCustomer.setInt(6, customer.getPostalCode());
            updateCustomer.setString(7, customer.getStreetName());
            updateCustomer.setString(8, customer.getHouseNr());
            updateCustomer.setString(9, customer.getFloor());
            updateCustomer.setInt(10, customer.getCardNr());
            updateCustomer.setInt(11, customer.getCardCVV());
            updateCustomer.setInt(12, customer.getId());
            updateCustomer.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }

    public boolean delete(Customer customer){
        try {
            //create prepared DELETE statement, which deletes the customer with the given id

            PreparedStatement deleteCustomer = conn.prepareStatement
                    ("DELETE FROM customer WHERE id = ?");
            deleteCustomer.setInt(1, customer.getId());
            deleteCustomer.executeUpdate();
            return true;
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return false;
    }
}


