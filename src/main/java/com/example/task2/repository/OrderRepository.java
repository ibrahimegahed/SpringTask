package com.example.task2.repository;

import com.example.task2.entity.Order;
import com.example.task2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository  extends JpaRepository<Order, UUID> {

}
