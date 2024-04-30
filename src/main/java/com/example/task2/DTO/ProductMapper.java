package com.example.task2.DTO;

import com.example.task2.entity.OrderItem;
import com.example.task2.entity.Product;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.mapstruct.Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);

    default OrderItemDTO map(OrderItem orderItem) {
        return OrderMapper.INSTANCE.orderItemToOrderItemDTO(orderItem);
    }

    default List<OrderItemDTO> map(List<OrderItem> orderItems) {
        if (orderItems != null){
          return   orderItems.stream()
                    .map(this::map)
                    .collect(Collectors.toList());

        }
        else {
            return Collections.emptyList();
        }

    }
}
