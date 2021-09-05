package com.jb.api;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Test
    void shouldNotAllowCreateOrderWithInvalidCPF() {
        Order order = new Order("111.111.111-11");
        assertThrows(InvalidCpfException.class, order::validate);
    }

    @Test
    void shouldCreateOrderWhitThreeItems() throws ApplicationException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.validate();
        Double total = order.getTotal();
        assertEquals(total, 7090);
    }

    @Test
    void shouldCreateOrderWithCoupon() throws ApplicationException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        order.validate();
        Double total = order.getTotal();
        assertEquals(total, 5672);
    }

    @Test
    void shouldCreateOrderWithExpiredCoupon()  {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2020, 10, 10)));
        assertThrows(ApplicationException.class, order::validate);
    }
}
