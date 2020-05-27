package com.automobil.webdemoautomobil.utility;

import com.automobil.webdemoautomobil.repositories.AutocamperRepository;
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
}
