package com.pawelgwozdz.shop.dto;

import com.pawelgwozdz.shop.utils.TestUtilsForOrdersController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDTOTest {

    @Test
    void testOrderDTOShouldEquals() {
        OrderDTO orderDTO = TestUtilsForOrdersController.createOrderDTO(1);
        OrderDTO orderDTO1 = TestUtilsForOrdersController.createOrderDTO(1);

        assertEquals(orderDTO, orderDTO1);
    }

    @Test
    void testOrderDTOShouldNotEquals() {
        OrderDTO orderDTO = TestUtilsForOrdersController.createOrderDTO(1);
        OrderDTO orderDTO1 = TestUtilsForOrdersController.createOrderDTO(2);

        assertNotEquals(orderDTO, orderDTO1);
    }
}