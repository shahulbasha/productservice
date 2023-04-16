package org.shahul.productservice.controller.impl;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.shahul.productservice.controller.ProductQueryController;
import org.shahul.productservice.eventsourcequery.FindProductQuery;
import org.shahul.productservice.model.ProductInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProductQueryControllerImpl implements ProductQueryController {

    private final QueryGateway queryGateway;

    @Autowired
    public ProductQueryControllerImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public List<ProductInfoDTO> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        return queryGateway.query(findProductQuery,
                ResponseTypes.multipleInstancesOf(ProductInfoDTO.class)).join();
    }
}
