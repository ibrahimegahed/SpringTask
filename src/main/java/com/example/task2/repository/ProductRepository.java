package com.example.task2.repository;

import com.example.task2.entity.Product;
import com.example.task2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Optional<Product> findProductByName(String productName);
}
