package com.example.task2.service;

import com.example.task2.DTO.ProductDTO;
import com.example.task2.DTO.ProductMapper;
import com.example.task2.entity.Product;
import com.example.task2.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@AllArgsConstructor
@Service
public class ProductService implements IProductService{
   private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {


        try {
            Product product= ProductMapper.INSTANCE.productDTOToProduct(productDTO);
            return ProductMapper.INSTANCE.productToProductDTO(productRepository.save(product));
        } catch (Exception ex) {
            return null;

        }

    }

    @Override
    public ProductDTO getProductById(UUID productId) {
        Optional<Product> productOptional=productRepository.findById(productId);
        if (productOptional.isEmpty())
            throw new IllegalStateException("no Product exists with that id");
        Product product=productOptional.get();
        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList;
        try{
            productList = productRepository.findAll();
        }
        catch(Exception ex){
            return  null;
        }
        List<ProductDTO> ProductDTOList=new ArrayList<ProductDTO>();
        for (Product product:productList){
            ProductDTOList.add(ProductMapper.INSTANCE.productToProductDTO(product));
        }
        return ProductDTOList;
    }

    @Override
    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO) {
        Product oldProduct= productRepository.getReferenceById(productId);
        if(productDTO.getName()!=null && !productDTO.getName().isEmpty()){
            oldProduct.setName(productDTO.getName());
        }
        return ProductMapper.INSTANCE.productToProductDTO(productRepository.save(oldProduct));
    }

    @Override
    public String deleteProduct(UUID productId) {
        try {
            productRepository.deleteById(productId);
        }
        catch (Exception Ex){
            return Ex.getMessage();
        }
        return "product deleted successfully";
    }

    @Override
    public boolean isProductNameAvailable(String productName) {
        return productRepository.findProductByName(productName).isEmpty();
    }
}
