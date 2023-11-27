package com.pkf.productservice.command.api.controller;

import com.pkf.productservice.command.api.commands.CreateProductCommand;
import com.pkf.productservice.command.api.data.Product;
import com.pkf.productservice.command.api.dto.ProductDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final CommandGateway commandGateway ;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<CreateProductCommand> createProduct(@RequestBody ProductDto productDto){

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .description(productDto.getDescription())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();

        String product = commandGateway.sendAndWait(createProductCommand);
        System.out.println("##### Product created " + product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createProductCommand);
    }
}
