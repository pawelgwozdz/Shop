package com.pawelgwozdz.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AddOrderDTO {

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private Long customerId;

    @NotNull
    private List<AddOrderProductDTO> products;
}
