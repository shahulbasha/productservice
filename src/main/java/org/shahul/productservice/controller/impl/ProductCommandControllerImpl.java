package org.shahul.productservice.controller.impl;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.shahul.productservice.controller.ProductCommandController;
import org.shahul.productservice.eventsourcecommand.CreateProductCommand;
import org.shahul.productservice.model.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductCommandControllerImpl implements ProductCommandController {

    private final CommandGateway commandGateway;
    @Autowired
    public ProductCommandControllerImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public String createProduct(ProductRequestDTO productRequestDTO) {
        Assert.notNull(productRequestDTO,"cannot be null");
        CreateProductCommand createProductCommand = new CreateProductCommand(UUID.randomUUID().toString(),productRequestDTO.getTitle()
                ,productRequestDTO.getPrice(),productRequestDTO.getQuantity());
        return commandGateway.sendAndWait(createProductCommand);
    }
}
