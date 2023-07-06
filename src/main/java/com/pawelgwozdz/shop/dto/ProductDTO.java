package com.pawelgwozdz.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer amount;

    @Override
    @JsonIgnore
    public boolean equals(Object obj) {
        ProductDTO other = null;
        if (obj.getClass().equals(getClass())) {
            other = (ProductDTO) obj;
        } else {
            return false;
        }
        if (!id.equals(other.getId())
                || !id.equals(other.id)
                || !name.equals(other.getName())
                || !description.equals(other.getDescription())
                || !price.equals(other.getPrice())
                || !amount.equals(other.getAmount())) {
            return false;
        }
        return true;
    }
}
