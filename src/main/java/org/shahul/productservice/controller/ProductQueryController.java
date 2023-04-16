package org.shahul.productservice.controller;

import org.shahul.productservice.model.ProductInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public interface ProductQueryController {
    @GetMapping
    List<ProductInfoDTO> getProducts();
}
