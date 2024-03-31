package com.nagarro.devops.service;

import com.nagarro.devops.dto.CreateProductResDto;
import com.nagarro.devops.dto.ProductDto;
import com.nagarro.devops.dto.ProductsResponseDto;

public interface ProductService {

    public ProductsResponseDto getAllProducts();
    public CreateProductResDto createProduct(ProductDto productDto);
}
