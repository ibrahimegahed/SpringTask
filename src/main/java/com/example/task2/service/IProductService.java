package com.example.task2.service;

import com.example.task2.DTO.ProductDTO;
import com.example.task2.entity.Product;
import com.example.task2.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
    public ProductDTO addProduct(ProductDTO productDTO);

    public ProductDTO getProductById(UUID productId);

    public List<ProductDTO> getAllProducts();

    public ProductDTO updateProduct(UUID productId,ProductDTO productDTO);

    public String deleteProduct(UUID productId);

    public boolean isProductNameAvailable(String username);
}
