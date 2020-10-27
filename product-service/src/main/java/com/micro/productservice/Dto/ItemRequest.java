package com.micro.productservice.Dto;


import lombok.Data;

@Data
public class ItemRequest {
    private String name;

    private String description;

    private Long categoryId;

    private Long price;

    private Long userId;
}
