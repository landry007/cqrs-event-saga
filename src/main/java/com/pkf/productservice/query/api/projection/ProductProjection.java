package com.pkf.productservice.query.api.projection;

import com.pkf.productservice.command.api.data.ProductRepository;
import com.pkf.productservice.command.api.dto.ProductDto;
import com.pkf.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductDto> handle(GetProductsQuery productsQuery){

        String name = productsQuery.getName();

        System.out.println(" ********** name name name :: ******** " + name);

       return productRepository.findAll().parallelStream().map(product -> ProductDto.builder()
               .productId(product.getProductId())
               .name(product.getName())
               .description(product.getDescription())
               .price(product.getPrice())
               .quantity(product.getQuantity())
               .build()
        ).collect(Collectors.toList());
    }
}
