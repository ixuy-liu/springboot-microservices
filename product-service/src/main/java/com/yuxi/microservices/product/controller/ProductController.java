package com.yuxi.microservices.product.controller;

import com.yuxi.microservices.product.dto.ProductRequest;
import com.yuxi.microservices.product.dto.ProductResponse;
import com.yuxi.microservices.product.model.Product;
import com.yuxi.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
         return productService.getAllProduct();
    }

}
