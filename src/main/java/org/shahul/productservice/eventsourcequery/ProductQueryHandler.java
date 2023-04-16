package org.shahul.productservice.eventsourcequery;

import org.axonframework.queryhandling.QueryHandler;
import org.shahul.productservice.database.ProductEntity;
import org.shahul.productservice.database.ProductRepository;
import org.shahul.productservice.model.ProductInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductInfoDTO> findProducts(FindProductQuery findProductQuery){
        List<ProductInfoDTO> products = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        productEntities.forEach(productEntity->{
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(productEntity,productInfoDTO);
            products.add(productInfoDTO);
        });
        return products;
    }
}
