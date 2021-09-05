package com.jb.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void shouldNotAllowCreateOrderWithInvalidCPF() {
        assertThrows(InvalidCpfException.class, () ->{
            Order order = new Order("111.111.111-11");
            order.validate();
        });
    }

    @Test
    void shouldCreateOrderWhitThreeItems() throws InvalidCpfException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra",1000.0,2);
        order.addItem("Amplificador",5000.0,1);
        order.addItem("Cabo",30.0,3);
        order.validate();
        Double total = order.getTotal();
        assertEquals(total, 7090);
    }

    void shouldCreateOrderWithCoupon() throws InvalidCpfException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra",1000.0,2);
        order.addItem("Amplificador",5000.0,1);
        order.addItem("Cabo",30.0,3);
        order.addCoupon(new Coupon("VALE20", 20.0));
        order.validate();
        Double total = order.getTotal();
        assertEquals(total, 5672);
    }
}
