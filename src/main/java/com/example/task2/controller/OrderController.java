package com.example.task2.controller;

import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.UserDTO;
import com.example.task2.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path="{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));

    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return  ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}/user")
    public ResponseEntity<UserDTO> getUserByOrderId(@PathVariable UUID orderId){
        return  ResponseEntity.ok(orderService.getUserByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO){

        return ResponseEntity.ok(orderService.addOrder(orderDTO));

    }

    @PutMapping(path="{orderId}")
    public  ResponseEntity<OrderDTO> updateOrder(@PathVariable("orderId") UUID orderId, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.updateOrder(orderId,orderDTO));

    }

    @DeleteMapping(path = "{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") UUID orderId){
        return  ResponseEntity.ok(orderService.deleteOrder(orderId));
    }

}
