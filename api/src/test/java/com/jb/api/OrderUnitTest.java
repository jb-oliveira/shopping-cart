package com.jb.api;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Order;
import com.jb.api.domain.exception.ApplicationException;
import com.jb.api.domain.exception.InvalidCpfException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderUnitTest {

    @DisplayName("Não deve permitir emitir um pedido com cpf invalido")
    @Test
    void shouldNotAllowCreateOrderWithInvalidCPF() {
        Order order = new Order("111.111.111-11");
        assertThrows(InvalidCpfException.class, order::validate);
    }

    @DisplayName("Deve criar um pedido com 3 itens")
    @Test
    void shouldCreateOrderWhitThreeItems() throws ApplicationException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.validate();
        Double total = order.getTotal();
        assertEquals(7090, total);
    }

    @DisplayName("Deve criar um pedido com cupom")
    @Test
    void shouldCreateOrderWithCoupon() throws ApplicationException {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        order.validate();
        Double total = order.getTotal();
        assertEquals(5672, total);
    }

    @DisplayName("Deve não permitir criar pedido com cupom expirado.")
    @Test
    void shouldNotCreateOrderWithExpiredCoupon()  {
        Order order = new Order("864.161.670-50");
        order.addItem("Guitarra", 1000.0, 2);
        order.addItem("Amplificador", 5000.0, 1);
        order.addItem("Cabo", 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2020, 10, 10)));
        assertThrows(ApplicationException.class, order::validate);
    }
}
