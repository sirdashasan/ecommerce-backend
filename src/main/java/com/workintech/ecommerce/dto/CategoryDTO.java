package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotEmpty(message = "Code can not be null or empty")
    @Size(min = 3, max=50, message = "Code must be between 3 to 50")
    private String code;

    @NotEmpty(message = "Title can not be null or empty")
    @Size(min = 3, max=50, message = "Title must be between 3 to 50")
    private String title;

    @NotEmpty(message = "Img can not be null or empty")
    @Size(min = 10, max=200, message = "Img must be between 10 to 200")
    private String img;

    @NotNull(message = "Rating can not be null or empty")
    private Double rating;

    @NotEmpty(message = "Gender can not be null or empty")
    @Size(min = 1, max=30, message = "Gender must be between 3 to 30")
    private String gender;

}
