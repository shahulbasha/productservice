package org.shahul.productservice.eventsourcecommand;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //validation and business logic
        Assert.notNull(createProductCommand,"Cannot be null command");
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(createProductCommand.getProductId(), createProductCommand.getTitle(),
                createProductCommand.getPrice(),createProductCommand.getQuantity());
        AggregateLifecycle.apply(productCreatedEvent);

    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.quantity = productCreatedEvent.getQuantity();
        this.price = productCreatedEvent.getPrice();
        this.title = productCreatedEvent.getTitle();
    }


}
