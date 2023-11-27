package com.pkf.productservice.command.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

}
