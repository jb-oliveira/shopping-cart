package com.jb.api.config;

import com.jb.api.domain.gateway.ZipCodeCalculatorApi;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ExternalApisConfig {

    @Bean
    public ZipCodeCalculatorApi zipCodeCalculatorApi(){
        return ((origin, destiny) -> 1000.0);
    }



}
