package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
import com.automobil.webdemoautomobil.repositories.AutocamperTypeRepository;
import com.automobil.webdemoautomobil.repositories.CustomerRepository;
import com.automobil.webdemoautomobil.repositories.RentalRepository;
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
    public CustomerRepository getCustomer(){
        return new CustomerRepository();
    }
}
