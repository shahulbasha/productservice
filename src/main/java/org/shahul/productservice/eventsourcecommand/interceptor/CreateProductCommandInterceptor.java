package org.shahul.productservice.eventsourcecommand.interceptor;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.shahul.productservice.database.ProductLookupEntity;
import org.shahul.productservice.database.ProductLookupRepository;
import org.shahul.productservice.eventsourcecommand.CreateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    ProductLookupRepository productLookupRepository;

    @Autowired
    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return ((index, commandMessage) -> {
            if (commandMessage.getPayload() instanceof CreateProductCommand createProductCommand){
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                Assert.isNull(productLookupEntity,"Product id or title already exists for "+createProductCommand.getTitle());
            }
            return commandMessage;
        });
    }
}
