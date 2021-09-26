package com.jb.api.infra;

import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderInputItemDTO;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.domain.repository.SequenceGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest
{

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanTables(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ccca.order_item", "ccca.order" );
        String sequenceName = SequenceGenerator.ORDER_ANUAL_SEQUENCE + "_" + LocalDate.now().getYear();
        jdbcTemplate.execute("ALTER SEQUENCE " + sequenceName + " RESTART WITH 1;");
    }

    @Test
    @DisplayName("Teste de requisição /orders/code com codigo invalido. deve retornar 404 not found")
    public void orderNotFoundTest() {
        webTestClient.get()
                .uri("/api/V1/orders/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Deve adicionar um pedido.")
    public void getOrderTest() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        List<PlaceOrderInputItemDTO>  inputItems = new ArrayList<>();
        inputItems.add(new PlaceOrderInputItemDTO(1L, 2));
        inputItems.add(new PlaceOrderInputItemDTO(2L, 1));
        inputItems.add(new PlaceOrderInputItemDTO(3L, 3));
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");
        inputDTO.setIssueDate(LocalDate.of(2021, 1, 1));

        PlaceOrderOutputDTO result = new PlaceOrderOutputDTO(String.format("%d%09d", inputDTO.getIssueDate().getYear(), 1), 5672.0 + 310.0, 310.0);

        webTestClient.post().uri("/api/V1/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(inputDTO), PlaceOrderImputDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PlaceOrderOutputDTO.class)
                .isEqualTo(result);
    }
}
