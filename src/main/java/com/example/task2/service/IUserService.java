package com.example.task2.service;

import com.example.task2.DTO.OrderDTO;
import com.example.task2.DTO.UserDTO;
import com.example.task2.entity.Order;
import com.example.task2.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    public UserDTO addUser(User user);

    public UserDTO getUserById(UUID userId);

    public List<UserDTO> getAllUsers();

    public List<OrderDTO> getOrderByUserId(UUID userId);

    public UserDTO updateUser(UUID userId,User user);

    public String deleteUser(UUID userId);

    public boolean isUsernameAvailable(String username);
}
