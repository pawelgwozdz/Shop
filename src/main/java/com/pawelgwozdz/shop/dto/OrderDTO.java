package com.pawelgwozdz.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pawelgwozdz.shop.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private BigDecimal wholePrice;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Long customerID;

    @NotNull
    private List<ProductDTO> orderedProducts;

    @Override
    @JsonIgnore
    public boolean equals(Object obj) {
        OrderDTO other = null;
        if (obj.getClass().equals(getClass())) {
            other = (OrderDTO) obj;
        } else {
            return false;
        }
        if (!orderDate.equals(other.getOrderDate())
                || !id.equals(other.id)
                || !wholePrice.equals(other.getWholePrice())
                || !status.equals(other.status)
                || !(customerID == other.getCustomerID())) {
            return false;
        }
        for (ProductDTO productDTO : orderedProducts) {
            if(!other.getOrderedProducts().contains(productDTO)) {
                return false;
            }
        }
        return true;
    }
}
