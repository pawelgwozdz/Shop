package com.pawelgwozdz.shop.web;

import com.pawelgwozdz.shop.dto.*;
import com.pawelgwozdz.shop.facade.OrdersFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersFacade ordersFacade;

    @PutMapping()
    public AddOrderRespDTO create(@Valid @RequestBody AddOrderDTO addOrderDTO) {
        return ordersFacade.addOrder(addOrderDTO);
    }

    @PostMapping("/update-status")
    public void UpdateStatus(@Valid @RequestBody ChangeOrderStatusDTO statusDTO) {
        ordersFacade.changeStatus(statusDTO);
    }

    @DeleteMapping()
    public void cancel(@Valid @RequestBody DeleteOrderDto deleteOrderDto) {
        ordersFacade.cancelOrder(deleteOrderDto);
    }

    @GetMapping
    public OrderDTO get(@RequestParam Long id) {
        return ordersFacade.get(id);
    }

}
