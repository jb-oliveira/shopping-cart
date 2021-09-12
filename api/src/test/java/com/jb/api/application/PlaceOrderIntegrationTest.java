package com.jb.api.application;

import com.jb.api.application.placeOrder.PlaceOrder;
import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderInputItemDTO;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.config.ApplicationConfig;
import com.jb.api.config.DatabaseConfig;
import com.jb.api.config.ExternalApisConfig;
import com.jb.api.domain.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import({ExternalApisConfig.class, DatabaseConfig.class, ApplicationConfig.class})
@ComponentScan( { "com.jb.api.infra", "com.jb.api.config" })
public class PlaceOrderIntegrationTest extends AbstractOrderIntegrationTest {

    @Autowired
    private PlaceOrder placeOrder;



    @Test
    @DisplayName("Deve criar um pedido")
    void shouldPlaceOrder() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        // items prices + freight
        assertEquals(5672 + 310, outputDTO.getTotal());

    }

    @Test
    @DisplayName("Deve criar pedido com frete")
    void shouldPlaceOrderWithFreight() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        assertEquals(310, outputDTO.getFreight());

    }

    @Test
    @DisplayName("Não deve deixar fazer um pedido com cupom expirado")
    void shouldNotAllowPlaceOrderWithExpiredCoupon() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20_EXPIRED");
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        assertThrows(DomainException.class, () -> placeOrder.execute(inputDTO));
    }

    @Test
    @DisplayName("Não deve permitir criar pedido com itens invalidos")
    void shouldNotAllowPlaceOrderWithInvalidItem() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputItems.add(new PlaceOrderInputItemDTO(-1L, -1));
        inputDTO.setItems(inputItems);
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        assertThrows(DomainException.class, () -> placeOrder.execute(inputDTO));
    }

    @Test
    @DisplayName("Deve fazer um pedido calculando o codigo")
    void shouldCalculateCode() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        placeOrder.execute(inputDTO);
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        assertEquals(outputDTO.getCode(), String.format("%d%09d", inputDTO.getIssueDate().getYear(), 2));
    }
}
