package com.nagarro.devops.controller;

import com.nagarro.devops.dto.CreateProductResDto;
import com.nagarro.devops.dto.ProductDto;
import com.nagarro.devops.dto.ProductsResponseDto;
import com.nagarro.devops.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/product/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getallproducts")
    public ResponseEntity<?> getAllProducts(){
        ProductsResponseDto productsResponseDto = productService.getAllProducts();

        return new ResponseEntity<>(productsResponseDto, HttpStatus.OK);
    }

    @PostMapping("/createproduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        HashMap<String, Object> map = new HashMap<>();

        if(productDto.getProductName() == null || productDto.getCompany() == null){
            map.put("message", "Please provide all the fields");
            map.put("code", 400);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        CreateProductResDto createProductResDto = productService.createProduct(productDto);
        return new ResponseEntity<>(createProductResDto, HttpStatus.ACCEPTED);
    }

}
