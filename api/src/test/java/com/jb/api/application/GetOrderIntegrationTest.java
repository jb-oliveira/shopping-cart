package com.jb.api.application;

import com.jb.api.application.exception.InvalidOrderException;
import com.jb.api.application.getOrder.GetOrder;
import com.jb.api.application.getOrder.GetOrderOutputDTO;
import com.jb.api.application.placeOrder.PlaceOrder;
import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.config.ApplicationConfig;
import com.jb.api.config.DatabaseConfig;
import com.jb.api.config.ExternalApisConfig;
import com.jb.api.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({ExternalApisConfig.class, DatabaseConfig.class, ApplicationConfig.class})
@ComponentScan( { "com.jb.api.infra", "com.jb.api.config" })
public class GetOrderIntegrationTest extends AbstractOrderIntegrationTest {

    @Autowired
    private PlaceOrder placeOrder;

    @Autowired
    private GetOrder getOrder;


    @Test
    @DisplayName("Deve consultar um pedido")
    void shoulConsultOrder() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        PlaceOrderOutputDTO placeOrderOutputDTO = placeOrder.execute(inputDTO);

        GetOrderOutputDTO getDTO =  getOrder.execute(placeOrderOutputDTO.getCode());
        assertEquals(5672 + 310, getDTO.getTotal());
        assertEquals(310, getDTO.getFreight());
        assertEquals( 3 , getDTO.getItems().size());

    }
    @Test
    @DisplayName("Deve rejeitar um pedido com codigo invalido")
    void shoulNotAllowGetOrderFromInvalidCode() {
        Assertions.assertThrows(InvalidOrderException.class, () -> getOrder.execute(""));

    }
}
