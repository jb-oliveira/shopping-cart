package com.jb.api.domain.entity;

import com.jb.api.domain.exception.BaseException;
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
        assertThrows(InvalidCpfException.class, () -> new Order(Cpf.parseCpf("111.111.111-11"), 1L) );
    }

    @DisplayName("Deve criar um pedido com 3 itens")
    @Test
    void shouldCreateOrderWhitThreeItems() throws BaseException {
        Order order = new Order(Cpf.parseCpf("864.161.670-50"), 1L);
        order.addItem(1L, 1000.0, 2);
        order.addItem(2L, 5000.0, 1);
        order.addItem(3L, 30.0, 3);
        order.validate();
        Double total = order.getTotal();
        assertEquals(7090, total);
    }

    @DisplayName("Deve criar um pedido com cupom")
    @Test
    void shouldCreateOrderWithCoupon() throws BaseException {
        Order order = new Order( Cpf.parseCpf("864.161.670-50"), 1L);
        order.addItem(1L, 1000.0, 2);
        order.addItem(2L, 5000.0, 1);
        order.addItem(3L, 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        order.validate();
        Double total = order.getTotal();
        assertEquals(5672, total);
    }

    @DisplayName("Deve não permitir criar pedido com cupom expirado.")
    @Test
    void shouldNotCreateOrderWithExpiredCoupon() throws InvalidCpfException {
        Order order = new Order(Cpf.parseCpf("864.161.670-50"), 1L);
        order.addItem(1L, 1000.0, 2);
        order.addItem(2L, 5000.0, 1);
        order.addItem(3L, 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2020, 10, 10)));
        assertThrows(BaseException.class, order::validate);
    }

    @DisplayName("Deve criar um pedido calculando o codigo.")
    @Test
    void shouldCreateOrderCalculatingCode() throws InvalidCpfException {
        Order order = new Order(Cpf.parseCpf("864.161.670-50"), 1L);
        order.addItem(1L, 1000.0, 2);
        order.addItem(2L, 5000.0, 1);
        order.addItem(3L, 30.0, 3);
        order.addCoupon(new Coupon("VALE20", 20.0, LocalDate.of(2020, 10, 10)));
        assertEquals("2021000000001", order.getOrderCode().getValue() );
    }
}
