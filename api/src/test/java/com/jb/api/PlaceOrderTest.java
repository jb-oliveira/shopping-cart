package com.jb.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaceOrderTest {

    List<PlaceOrderInputItemDTO> inputItems;
    ZipCodeCalculatorApi zipcodeCalculatorApi;

    @BeforeEach
    void createItems() {
        inputItems = new ArrayList<>();
        inputItems.add(new PlaceOrderInputItemDTO("1", 2));
        inputItems.add(new PlaceOrderInputItemDTO("2", 1));
        inputItems.add(new PlaceOrderInputItemDTO("3", 3));
        zipcodeCalculatorApi = (origin, destiny) -> 1000.0;
    }


    @Test
    void shouldPlaceOrder() throws ApplicationException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20");

        PlaceOrder placeOrder = new PlaceOrder(zipcodeCalculatorApi);
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        // items prices + freight
        assertEquals(5672 + 310, outputDTO.getTotal());

    }

    @Test
    void shouldPlaceOrderWithFreight() throws ApplicationException {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);

        PlaceOrder placeOrder = new PlaceOrder(zipcodeCalculatorApi);
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        assertEquals(310, outputDTO.getFreight());

    }

    @Test
    void shouldNotAllowPlaceOrderWithExpiredCoupon() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputDTO.setItems(inputItems);
        inputDTO.setCoupon("VALE20_EXPIRED");

        PlaceOrder placeOrder = new PlaceOrder(zipcodeCalculatorApi);
        assertThrows(ApplicationException.class, () -> placeOrder.execute(inputDTO));
    }

    @Test
    void shouldNotAllowPlaceOrderWithInvalidItem() {
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO();
        inputDTO.setCpf("864.161.670-50");
        inputItems.add(new PlaceOrderInputItemDTO("-1", -1));
        inputDTO.setItems(inputItems);

        PlaceOrder placeOrder = new PlaceOrder(zipcodeCalculatorApi);
        assertThrows(ApplicationException.class, () -> placeOrder.execute(inputDTO));
    }
}
