package com.jb.api.config;

import com.jb.api.domain.gateway.ZipCodeCalculatorApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalApisConfig {

    @Bean
    public ZipCodeCalculatorApi zipCodeCalculatorApi() {
        return ((origin, destiny) -> 1000.0);
    }


}
