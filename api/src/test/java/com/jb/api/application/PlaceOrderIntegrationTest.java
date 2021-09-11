package com.jb.api.application;

import com.jb.api.config.ExternalApisConfig;
import com.jb.api.config.DatabaseConfig;
import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.exception.DomainException;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.infra.repository.jpa.OrderEntityRepository;
import com.jb.api.infra.repository.jpa.OrderItemEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import({ExternalApisConfig.class, DatabaseConfig.class})
@ComponentScan("com.jb.api.config")
public class PlaceOrderIntegrationTest {

    List<PlaceOrderInputItemDTO> inputItems;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    PlaceOrder placeOrder;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    OrderItemEntityRepository orderItemEntityRepository;


    @BeforeEach
    void createItems() {
        orderItemEntityRepository.deleteAll();
        orderEntityRepository.deleteAll();
        orderEntityRepository.deleteAll();
        itemRepository.deleteAll();
        itemRepository.save(new Item("1", "Guitarra", 1000.0, 100, 50, 15, 3.0));
        itemRepository.save(new Item("2", "Amplificador", 5000.0, 50, 50, 50, 22.0));
        itemRepository.save(new Item("3", "Cabo", 30.0, 10, 10, 10, 1.0));
        couponRepository.deleteAll();
        couponRepository.save(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        couponRepository.save(new Coupon("VALE20_EXPIRED", 20.0, LocalDate.of(2020, 10, 10)));

        inputItems = new ArrayList<>();
        inputItems.add(new PlaceOrderInputItemDTO(1l, 2));
        inputItems.add(new PlaceOrderInputItemDTO(2l, 1));
        inputItems.add(new PlaceOrderInputItemDTO(3l, 3));
    }


    @DisplayName("Deve criar um pedido")
    @Test
    void shouldPlaceOrder() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        // items prices + freight
        assertEquals(5672 + 310, outputDTO.getTotal());

    }

    @DisplayName("Deve criar pedido com frete")
    @Test
    void shouldPlaceOrderWithFreight() throws DomainException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        assertEquals(310, outputDTO.getFreight());

    }

    @DisplayName("Não deve deixar fazer um pedido com cupom expirado")
    @Test
    void shouldNotAllowPlaceOrderWithExpiredCoupon() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20_EXPIRED");

        assertThrows(DomainException.class, () -> placeOrder.execute(inputDTO));
    }

    @DisplayName("Não deve permitir criar pedido com itens invalidos")
    @Test
    void shouldNotAllowPlaceOrderWithInvalidItem() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputItems.add(new PlaceOrderInputItemDTO(-1l, -1));
        inputDTO.setItems(inputItems);
        assertThrows(DomainException.class, () -> placeOrder.execute(inputDTO));
    }
}