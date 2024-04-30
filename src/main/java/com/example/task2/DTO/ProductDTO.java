package com.example.task2.DTO;

import com.example.task2.entity.OrderItem;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

public class ProductDTO {
    private UUID productId;
    private String name;
    private List<OrderItemDTO> orderItems;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
