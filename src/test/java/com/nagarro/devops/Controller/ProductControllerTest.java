package com.nagarro.devops.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.devops.controller.ProductController;
import com.nagarro.devops.dto.CreateProductResDto;
import com.nagarro.devops.dto.ProductDto;
import com.nagarro.devops.dto.ProductsResponseDto;
import com.nagarro.devops.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void ProductController_CreateProduct_ReturnCreateResponseDto() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .productName("ProductName")
                .price(1000)
                .company("Company")
                .build();

        CreateProductResDto createProductResDto = CreateProductResDto.builder()
                        .message("Product saved successfully")
                        .code(202)
                        .product(productDto)
                        .build();

        when(productService.createProduct(any())).thenReturn(createProductResDto);

        ResultActions response = mockMvc.perform(post("/product/api/createproduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(jsonPath("$.message").value(createProductResDto.getMessage()))
                .andExpect(jsonPath("$.code").value(createProductResDto.getCode()))
                .andExpect(jsonPath("$.product").value(createProductResDto.getProduct()));
    }

    @Test
    public void ProductController_GetAllProducts_ReturnProductsResponseDto() throws Exception {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add( ProductDto.builder()
                .productName("ProductName1")
                .price(1000)
                .company("Company1")
                .build());
        productDtoList.add( ProductDto.builder()
                .productName("ProductName2")
                .price(2000)
                .company("Company2")
                .build());

        ProductsResponseDto productsResponseDto = ProductsResponseDto.builder()
                .message("all products fetched successfully")
                .code(200)
                .products(productDtoList)
                .build();

        when(productService.getAllProducts()).thenReturn(productsResponseDto);

        ResultActions response = mockMvc.perform(get("/product/api/getallproducts"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value(productsResponseDto.getMessage()))
                .andExpect(jsonPath("$.code").value(productsResponseDto.getCode()))
                .andExpect(jsonPath("$.products.length()").value(productsResponseDto.getProducts().size()));
    }

}
