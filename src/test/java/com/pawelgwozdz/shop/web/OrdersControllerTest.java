package com.pawelgwozdz.shop.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawelgwozdz.shop.dto.AddOrderDTO;
import com.pawelgwozdz.shop.dto.ChangeOrderStatusDTO;
import com.pawelgwozdz.shop.dto.DeleteOrderDto;
import com.pawelgwozdz.shop.dto.OrderDTO;
import com.pawelgwozdz.shop.utils.TestUtilsForOrdersController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersControllerTest {

    private static final String API_PATH = "/api/v1/order";
    private static final String UPDATE_STATUS_PATH = "/update-status";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Order(1)
    @Test
    void createShouldReturnHttpOkAndId() throws Exception {
        AddOrderDTO addOrderDTO = TestUtilsForOrdersController.createAddOrderDTO();
        ResultMatcher expected = jsonPath("orderId").value(1);

        mockMvc.perform(MockMvcRequestBuilders.put(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addOrderDTO)))
                .andExpect(status().isOk())
                .andExpect(expected);
    }

    @Order(2)
    @Test
    void updateShouldReturnHttp200() throws Exception {
        ChangeOrderStatusDTO changeOrderStatusDTO = TestUtilsForOrdersController.createChangeOrderStatusDTO(1);

        mockMvc.perform(MockMvcRequestBuilders.post(API_PATH + UPDATE_STATUS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changeOrderStatusDTO)))
                .andExpect(status().isOk());
    }

    @Order(3)
    @Test
    void getOrderShouldReturn200AndCorrectResponse() throws Exception {
        //GIVEN
        OrderDTO expected = TestUtilsForOrdersController.createOrderDTO(1l);

        //WHEN
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(API_PATH)
                        .queryParam("id", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN
        OrderDTO result = objectMapper.readValue(response.getContentAsString(), OrderDTO.class);
        assertTrue(result.equals(expected));
    }

    @Order(4)
    @Test
    void cancelShouldReturn200() throws Exception {
        DeleteOrderDto deleteOrderDTO = TestUtilsForOrdersController.createDeleteOrderDTO(1l);

        mockMvc.perform(MockMvcRequestBuilders.delete(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteOrderDTO)))
                .andExpect(status().isOk());
    }
}
