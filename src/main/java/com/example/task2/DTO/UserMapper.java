package com.example.task2.DTO;


import com.example.task2.entity.Order;
import com.example.task2.entity.User;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.mapstruct.Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "orders", target = "orders")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "orders", target = "orders")
    User userDTOToUser(UserDTO userDTO);

    default OrderDTO map(Order order) {
        return OrderMapper.INSTANCE.orderToOrderDTO(order);
    }

    default List<OrderDTO> map(List<Order> orders) {

        if(orders!=null){
            return orders.stream()
                    .map(this::map)
                    .collect(Collectors.toList());

        }
        else {
            return  Collections.emptyList();
        }

    }
}
