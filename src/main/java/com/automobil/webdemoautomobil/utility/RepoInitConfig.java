package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class RepoInitConfig {

    @Bean
    public JDBCConnection getConn() throws SQLException {
        return JDBCConnection.getInstance();
    }

    @Bean
    public AutocamperRepository getAuto() throws SQLException {
        return new AutocamperRepository();
    }

    @Bean
    public AutocamperTypeRepository getAutoType() throws SQLException {
        return new AutocamperTypeRepository();
    }

    @Bean
    public RentalRepository getRental() throws SQLException {
        return new RentalRepository();
    }

    @Bean
    public VariablePricesRepository getPrices() throws SQLException { return new VariablePricesRepository();}

    @Bean
    public CustomerRepository getCustomer() throws SQLException {
        return new CustomerRepository();
    }

    @Bean
    public MaintenanceReportRepository getMaintenanceReport() throws SQLException {
        return new MaintenanceReportRepository();
    }

    @Bean
    public BuiltInFeatureRepository getBuiltInFeatures() throws SQLException {
        return new BuiltInFeatureRepository();
    }

    @Bean
    public AccessoryRepository getAccessory() throws SQLException {
        return new AccessoryRepository();
    }
}
