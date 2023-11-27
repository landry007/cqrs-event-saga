package com.pkf.productservice.command.api.events;

import com.pkf.productservice.command.api.data.Product;
import com.pkf.productservice.command.api.data.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventHandler {

    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(CreateProductEvent event) {

        log.info("ProductEventHandler productId: {}", event.getProductId());

        Product product = new Product();

        BeanUtils.copyProperties(event, product);

        log.info("ProductEventHandler product: {}", product);

        productRepository.save(product);
    }
}

