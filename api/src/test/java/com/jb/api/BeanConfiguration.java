package com.jb.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BeanConfiguration {

    @Bean
    public PlaceOrder placeOrder(){
        return new PlaceOrder();
    }

}
