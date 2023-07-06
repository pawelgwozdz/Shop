package com.pawelgwozdz.shop.dto;

import com.pawelgwozdz.shop.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeOrderStatusDTO {

    @NotNull
    private Long id;

    @NotNull
    private OrderStatus orderStatus;
}
