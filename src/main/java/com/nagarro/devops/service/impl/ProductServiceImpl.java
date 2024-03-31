package com.nagarro.devops.service.impl;

import com.nagarro.devops.dto.CreateProductResDto;
import com.nagarro.devops.dto.ProductDto;
import com.nagarro.devops.dto.ProductsResponseDto;
import com.nagarro.devops.entity.Product;
import com.nagarro.devops.repository.ProductRepository;
import com.nagarro.devops.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductsResponseDto getAllProducts() {
        List<Product> productList = productRepository.findAll();

        List<ProductDto> productDtos = productList.stream()
                .map(product -> {return modelMapper.map(product,ProductDto.class);})
                .collect(Collectors.toList());

        return ProductsResponseDto.builder()
                .products(productDtos)
                .code(200)
                .message("all products fetched successfully")
                .build();
    }

    @Override
    public CreateProductResDto createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        productRepository.save(product);

        return CreateProductResDto.builder()
                .code(202)
                .message("Product saved successfully")
                .build();
    }

}
