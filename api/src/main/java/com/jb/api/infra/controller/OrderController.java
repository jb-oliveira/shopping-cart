package com.jb.api.infra.controller;

import com.jb.api.application.getOrder.GetOrder;
import com.jb.api.application.getOrder.GetOrderOutputDTO;
import com.jb.api.domain.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/V1/orders")
public class OrderController {

    @Autowired
    private GetOrder getOrder;

    @GetMapping("/{code}")
    public ResponseEntity<GetOrderOutputDTO> getOrder(@PathVariable String code) throws BaseException {
        return ResponseEntity.ok(getOrder.execute(code));
    }

}
