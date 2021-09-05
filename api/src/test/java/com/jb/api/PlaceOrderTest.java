package com.jb.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderTest {

    @Test
    void shouldPlaceOrder() throws ApplicationException {
        List<PlaceOrderInputItemDTO> inputItems = new ArrayList<>();
        inputItems.add( new PlaceOrderInputItemDTO("Guitarra",1000.0,2) );
        inputItems.add( new PlaceOrderInputItemDTO("Amplificador",5000.0,1) );
        inputItems.add( new PlaceOrderInputItemDTO("Cabo",30.0,3) );
        PlaceOrderImputDTO inputDTO = new PlaceOrderImputDTO("864.161.670-50", inputItems,"VALE20" );

        PlaceOrder placeOrder = new PlaceOrder();
        PlaceOrderOutputDTO outputDTO = placeOrder.execute(inputDTO);
        Assertions.assertEquals(outputDTO.getTotal(), 5672);

    }
}
