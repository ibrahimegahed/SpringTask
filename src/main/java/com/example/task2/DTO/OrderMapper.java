package com.example.task2.DTO;

import com.example.task2.entity.Order;
import com.example.task2.entity.OrderItem;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(source = "user.userId", target = "userId")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "userId", target = "user.userId")
    Order orderDTOToOrder(OrderDTO orderDTO);

    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "orderItemId", target = "orderItemId")
    OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

    @Mapping(source = "productId", target = "product.productId")
    OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItem> orderItemDTOListToOrderItemList(List<OrderItemDTO> orderItemDTOList);

    List<OrderDTO> orderListToOrderDTOList(List<Order> orderList);

    List<Order> orderDTOListToOrderList(List<OrderDTO> orderDTOList);
}
