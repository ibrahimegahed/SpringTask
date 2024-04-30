package com.example.task2.service;

import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.UserDTO;
import com.example.task2.entity.Order;
import com.example.task2.entity.User;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {

    public OrderDTO getOrderById(UUID orderId);

    public List<OrderDTO> getAllOrders();

    public UserDTO getUserByOrderId(UUID orderId);

    public OrderDTO addOrder(OrderDTO orderDTO);

    public OrderDTO updateOrder(UUID orderId,OrderDTO order);

    public String deleteOrder(UUID orderId);

}
