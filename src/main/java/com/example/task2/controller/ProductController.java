package com.example.task2.controller;


import com.example.task2.DTO.ProductDTO;
import com.example.task2.entity.Product;
import com.example.task2.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@AllArgsConstructor
@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {
   private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path="{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") UUID productId){

        return ResponseEntity.ok(productService.getProductById(productId));

    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){

        if (!productService.isProductNameAvailable(productDTO.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("There's already a product with that name");
        }

        return ResponseEntity.ok(productService.addProduct(productDTO));

    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") UUID productId){
        return  ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @PutMapping(path="{productId}")
    public  ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") UUID productId,@RequestBody ProductDTO productDTO){

        return ResponseEntity.ok(productService.updateProduct(productId,productDTO)) ;
    }
}
