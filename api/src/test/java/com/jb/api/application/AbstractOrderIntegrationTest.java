package com.jb.api.application;

import com.jb.api.application.placeOrder.PlaceOrderInputItemDTO;
import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.repository.SequenceGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public abstract class AbstractOrderIntegrationTest {
    protected List<PlaceOrderInputItemDTO> inputItems;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void deleteOrdersAndCreateItems() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ccca.order_item", "ccca.order");
        String sequenceName = SequenceGenerator.ORDER_ANUAL_SEQUENCE + "_" + LocalDate.now().getYear();
        jdbcTemplate.execute("ALTER SEQUENCE " + sequenceName + " RESTART WITH 1;");

        inputItems = new ArrayList<>();
        inputItems.add(new PlaceOrderInputItemDTO(1L, 2));
        inputItems.add(new PlaceOrderInputItemDTO(2L, 1));
        inputItems.add(new PlaceOrderInputItemDTO(3L, 3));
    }

}
