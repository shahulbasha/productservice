package org.shahul.productservice.eventsourcecommand;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.shahul.productservice.database.ProductLookupEntity;
import org.shahul.productservice.database.ProductLookupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        ProductLookupEntity productLookupEntity = new ProductLookupEntity();
        BeanUtils.copyProperties(productCreatedEvent,productLookupEntity);
        productLookupRepository.save(productLookupEntity);
    }
}
