package com.jb.api.infra;

import com.jb.api.application.AbstractOrderIntegrationTest;
import com.jb.api.application.getOrder.GetOrderOutputDTO;
import com.jb.api.application.placeOrder.PlaceOrder;
import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.domain.exception.BaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest extends AbstractOrderIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PlaceOrder placeOrder;

    @Test
    @DisplayName("Teste de requisição /orders/code com codigo invalido. deve retornar 404 not found")
    public void orderNotFoundTest() {
        ResponseEntity<GetOrderOutputDTO> forEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/V1/orders/-1", GetOrderOutputDTO.class);
        assertEquals(forEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Teste de requisição /orders/code que deve retornar um pedido recem criado.")
    public void getOrderTest() throws BaseException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);

        ResponseEntity<GetOrderOutputDTO> forEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/V1/orders/" + outputDTO.getCode(), GetOrderOutputDTO.class);
        assertNotNull(forEntity.getBody());
        assertEquals(forEntity.getBody().getTotal(), outputDTO.getTotal());
        assertEquals(forEntity.getBody().getFreight(), outputDTO.getFreight());
    }
}
