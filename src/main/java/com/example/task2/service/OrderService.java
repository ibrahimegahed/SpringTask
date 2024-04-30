package com.example.task2.service;

import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.OrderMapper;
import com.example.task2.DTO.UserDTO;
import com.example.task2.DTO.UserMapper;
import com.example.task2.entity.Order;
import com.example.task2.entity.OrderItem;
import com.example.task2.entity.User;
import com.example.task2.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@AllArgsConstructor
@Service
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO getOrderById(UUID orderId) {
        Optional<Order> orderOptional=orderRepository.findById(orderId);
        if (orderOptional.isEmpty())
            throw new IllegalStateException("no order exists with that id");
        Order order=orderOptional.get();
        return OrderMapper.INSTANCE.orderToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> OrderList;
        try{
            OrderList= orderRepository.findAll();
        }
        catch(Exception ex){
            return  null;
        }
        return OrderMapper.INSTANCE.orderListToOrderDTOList(OrderList);
    }

    @Override
    public UserDTO getUserByOrderId(UUID orderId) {
        Optional<Order> orderOptional= orderRepository.findById(orderId);
        if (orderOptional.isEmpty())
            throw new IllegalStateException("no order exists with that id");
        Order order=orderOptional.get();
       return UserMapper.INSTANCE.userToUserDTO(order.getUser());


    }

    @Transactional
    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        try {

            Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
            // Save the order entity
            Order savedOrder = orderRepository.save(order);
            for(OrderItem orderItem:savedOrder.getOrderItems()){
                orderItem.setOrder(savedOrder);
            }
            return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);

        } catch (Exception ex) {
            return null;

        }
    }

    @Transactional
    @Override
    public OrderDTO updateOrder(UUID orderId,OrderDTO orderDTO) {

        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        Optional<Order> oldOrderOptional= orderRepository.findById(orderId);
        if (oldOrderOptional.isEmpty())
            throw new IllegalStateException("no order exists with that id");
        //Order oldOrder= orderRepository.getReferenceById(orderId);
        Order oldOrder=oldOrderOptional.get();
        orderRepository.flush();
        oldOrder.getOrderItems().clear();
        oldOrder.getOrderItems().addAll(order.getOrderItems());
       // oldOrder.setOrderItems(order.getOrderItems());
        Order updatedOrder=orderRepository.save(oldOrder);
        for(OrderItem orderItem:updatedOrder.getOrderItems()){
            orderItem.setOrder(updatedOrder);
        }
        return  OrderMapper.INSTANCE.orderToOrderDTO(updatedOrder);

    }

    @Override
    public String deleteOrder(UUID orderId) {
        if(orderRepository.findById(orderId).isEmpty())
            return "There's no order by that Id";

        try {
            orderRepository.deleteById(orderId);
        }
        catch (Exception Ex){
            return Ex.getMessage();
        }
        return "order deleted successfully";
    }

}
