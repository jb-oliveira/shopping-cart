package com.jb.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
public class ExternalApisConfig {

    @Bean
    public ZipCodeCalculatorApi zipCodeCalculatorApi(){
        return ((origin, destiny) -> 1000.0);
    }


}
