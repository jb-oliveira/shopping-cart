package com.jb.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaceOrderTest {

    @Test
    void shouldPlaceOrder() throws ApplicationException {
        List<PlaceOrderInputItemDTO> inputItems = new ArrayList<>();
        inputItems.add( new PlaceOrderInputItemDTO("1",2) );
        inputItems.add( new PlaceOrderInputItemDTO("2",1) );
        inputItems.add( new PlaceOrderInputItemDTO("3",3) );
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO("864.161.670-50", inputItems,"VALE20" );

        PlaceOrder placeOrder = new PlaceOrder();
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        assertEquals(outputDTO.getTotal(), 5672);

    }
    @Test
    void shouldNotAllowPlaceOrderWithExpiredCoupon() {
        List<PlaceOrderInputItemDTO> inputItems = new ArrayList<>();
        inputItems.add( new PlaceOrderInputItemDTO("1",2) );
        inputItems.add( new PlaceOrderInputItemDTO("2",1) );
        inputItems.add( new PlaceOrderInputItemDTO("3",3) );
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO("864.161.670-50", inputItems,"VALE20_EXPIRED" );

        PlaceOrder placeOrder = new PlaceOrder();
        assertThrows(ApplicationException.class, ()-> placeOrder.execute(inputDTO));
    }
}
