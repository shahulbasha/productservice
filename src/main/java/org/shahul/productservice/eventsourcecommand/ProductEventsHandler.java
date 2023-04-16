package org.shahul.productservice.eventsourcecommand;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.shahul.productservice.database.ProductEntity;
import org.shahul.productservice.database.ProductRepository;
import org.shahul.productservice.eventsourcecommand.ProductCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent,productEntity);
        productRepository.save(productEntity);
    }
}
