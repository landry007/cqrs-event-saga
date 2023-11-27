package com.pkf.productservice.command.api.aggregates;

import com.pkf.productservice.command.api.commands.CreateProductCommand;
import com.pkf.productservice.command.api.events.CreateProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        // add validation and business rules here

        CreateProductEvent createProductEvent = new CreateProductEvent();

        BeanUtils.copyProperties(createProductCommand, createProductEvent);

        AggregateLifecycle.apply(createProductEvent);
    }

    @EventSourcingHandler
    public void on(CreateProductEvent createProductEvent){
        this.productId = createProductEvent.getProductId();
        this.name = createProductEvent.getName();
        this.description = createProductEvent.getDescription();
        this.price = createProductEvent.getPrice();
        this.quantity = createProductEvent.getQuantity();
    }
}
