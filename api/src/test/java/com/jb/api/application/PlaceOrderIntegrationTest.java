package com.jb.api.application;

import com.jb.api.config.ApplicationConfig;
import com.jb.api.config.DatabaseConfig;
import com.jb.api.config.ExternalApisConfig;
import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.exception.DomainException;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.repository.SequenceGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import({ExternalApisConfig.class, DatabaseConfig.class, ApplicationConfig.class})
@ComponentScan( { "com.jb.api.infra", "com.jb.api.config" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaceOrderIntegrationTest {

    List<PlaceOrderInputItemDTO> inputItems;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    PlaceOrder placeOrder;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void createItems() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ccca.item", "ccca.order_item", "ccca.order", "ccca.coupon");
        String sequenceName =  SequenceGenerator.ORDER_ANUAL_SEQUENCE+ "_" + LocalDate.now().getYear();
        jdbcTemplate.execute("ALTER SEQUENCE " + sequenceName + " RESTART WITH 1;");
        itemRepository.save(new Item(1L, "Guitarra", 1000.0, 100, 50, 15, 3.0));
        itemRepository.save(new Item(2L, "Amplificador", 5000.0, 50, 50, 50, 22.0));
        itemRepository.save(new Item(3L, "Cabo", 30.0, 10, 10, 10, 1.0));
        couponRepository.save(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        couponRepository.save(new Coupon("VALE20_EXPIRED", 20.0, LocalDate.of(2020, 10, 10)));

        inputItems = new ArrayList<>();
        inputItems.add(new PlaceOrderInputItemDTO(1L, 2));
        inputItems.add(new PlaceOrderInputItemDTO(2L, 1));
        inputItems.add(new PlaceOrderInputItemDTO(3L, 3));
    }


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
