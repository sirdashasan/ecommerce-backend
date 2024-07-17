package com.workintech.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Long userId;
    private Date orderDate;
    private Double price;

    @JsonProperty("product_id")
    private List<Long> productIds;

}
