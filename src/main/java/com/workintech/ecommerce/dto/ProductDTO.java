package com.workintech.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 3, max=45, message = "Name must be between 3 to 45")
    private String name;

    @NotEmpty(message = "Description can not be null or empty")
    @Size(min = 10, max=100, message = "Description must be between 10 to 100")
    private String description;

    @NotNull(message = "Price can not be null or empty")
    private Double price;

    @NotNull(message = "Stock can not be null or empty")
    private Integer stock;

    @JsonProperty("store_id")
    private Long storeId;
    @JsonProperty("category_id")
    private Long categoryId;

    @NotNull(message = "Rating can not be null or empty")
    private Double rating;

    @NotNull(message = "SellCount can not be null or empty")
    @JsonProperty("sell_count")
    private Integer sellCount;

    @NotEmpty(message = "Images can not be null or empty")
    @Size(min = 10, max=200, message = "Images must be between 10 to 200")
    private String images;

    @JsonProperty("order_id")
    private List<Long> orderIds;

}
