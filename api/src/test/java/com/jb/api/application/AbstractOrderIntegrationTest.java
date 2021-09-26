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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    ItemRepository itemRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void createItems() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ccca.item", "ccca.order_item", "ccca.order", "ccca.coupon");
        String sequenceName = SequenceGenerator.ORDER_ANUAL_SEQUENCE + "_" + LocalDate.now().getYear();
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


}
