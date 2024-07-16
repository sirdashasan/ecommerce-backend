package com.workintech.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @JsonProperty("store_id")
    private Long storeId;
    @JsonProperty("category_id")
    private Long categoryId;
    private Double rating;
    @JsonProperty("sell_count")
    private Integer sellCount;
    private String images;
}
