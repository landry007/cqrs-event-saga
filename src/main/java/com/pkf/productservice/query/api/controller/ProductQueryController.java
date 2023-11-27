package com.pkf.productservice.query.api.controller;

import com.pkf.productservice.command.api.dto.ProductDto;
import com.pkf.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam("name") String name) {

        GetProductsQuery getProductsQuery = new GetProductsQuery();
        getProductsQuery.setName(name);
        List<ProductDto> productDtoList = queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductDto.class)).join();

        return ResponseEntity.ok(productDtoList);
    }
}
