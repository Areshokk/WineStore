package com.gmail.yurii.ecommerce.service;

import com.gmail.yurii.ecommerce.domain.Order;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.service.Impl.OrderServiceImpl;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order save(Order order);

    List<Order> findOrderByUser(User user);
}
