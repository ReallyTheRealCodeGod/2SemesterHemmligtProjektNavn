package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoInitConfig {

    @Bean
    public JDBCConnection getConn(){
        return new JDBCConnection();
    }

    @Bean
    public AutocamperRepository getAuto(){
        return new AutocamperRepository();
    }

    @Bean
    public AutocamperTypeRepository getAutoType(){
        return new AutocamperTypeRepository();
    }

    @Bean
    public RentalRepository getRental(){
        return new RentalRepository();
    }

    @Bean
    public VariablePricesRepository getPrices(){ return new VariablePricesRepository();}

    @Bean
    public CustomerRepository getCustomer(){
        return new CustomerRepository();
    }

    @Bean
    public MaintenanceReportRepository getMaintenanceReport(){
        return new MaintenanceReportRepository();
    }

    @Bean
    public BuiltInFeatureRepository getBuiltInFeatures(){
        return new BuiltInFeatureRepository();
    }

    @Bean
    public AccessoryRepository getAccessory(){
        return new AccessoryRepository();
    }
}
