package com.micro.productservice.Dto;

import com.micro.productservice.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long id;

    private String name;

    private String description;

    private Category category;

    private Long price;

}
