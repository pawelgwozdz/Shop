package com.pawelgwozdz.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddOrderProductDTO {

    @NotNull
    private Long id;

    @NotNull
    private int amount;
}
