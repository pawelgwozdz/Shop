package com.pawelgwozdz.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderDto {

    @NotNull
    private Long id;
}
