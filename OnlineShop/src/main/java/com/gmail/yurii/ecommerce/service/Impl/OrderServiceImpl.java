package com.gmail.yurii.ecommerce.service.Impl;

import com.gmail.yurii.ecommerce.domain.Order;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.repos.OrderRepository;
import com.gmail.yurii.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    /**
     * Implementation of the {@link OrderRepository} interface
     * for working with orders with a database.
     */
    private final OrderRepository orderRepository;

    /**
     * Constructor for initializing the main variables of the order service.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param orderRepository implementation of the {@link OrderRepository} interface
     *                        for working with orders with a database.
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Return list of all user orders.
     *
     * @return list of user {@link Order}.
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Save order info.
     *
     * @param order order object to return.
     * @return The {@link Order} class object which will be saved in the database.
     */
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Returns list of orders authenticated user.
     *
     * @param user name of authenticated user.
     * @return An object of type {@link List} is a list of orders of authenticated user.
     */
    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }
}
