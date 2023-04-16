package org.shahul.productservice.controller;

import jakarta.validation.Valid;
import org.shahul.productservice.model.ProductRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public interface ProductCommandController {

    @PostMapping
    String createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO);
}
