package com.jb.api.infra.controller;

import com.jb.api.application.getOrder.GetOrder;
import com.jb.api.application.getOrder.GetOrderOutputDTO;
import com.jb.api.application.placeOrder.PlaceOrder;
import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.domain.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/V1/orders")
public class OrderController {

    @Autowired
    private GetOrder getOrder;

    @Autowired
    private PlaceOrder placeOrder;

    @GetMapping("/{code}")
    public ResponseEntity<GetOrderOutputDTO> getOrder(@PathVariable String code) throws BaseException {
        return ResponseEntity.ok(getOrder.execute(code));
    }

    @PostMapping("/")
    public ResponseEntity<PlaceOrderOutputDTO> placeOrder(@RequestBody PlaceOrderImputDTO inputDTO) throws BaseException {
        return ResponseEntity.ok( placeOrder.execute(inputDTO) );
    }
}
