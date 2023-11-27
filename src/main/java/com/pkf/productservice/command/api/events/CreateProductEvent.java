package com.pkf.productservice.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductEvent {

    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

}
