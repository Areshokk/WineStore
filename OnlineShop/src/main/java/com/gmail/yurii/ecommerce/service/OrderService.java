package com.gmail.yurii.ecommerce.service;

import com.gmail.yurii.ecommerce.domain.Order;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.service.Impl.OrderServiceImpl;

import java.util.List;

public interface OrderService {
    /**
     * Return list of all user orders.
     *
     * @return list of user {@link Order}.
     */
    List<Order> findAll();

    /**
     * Save order info.
     *
     * @param order order object to return.
     * @return The {@link Order} class object which will be saved in the database.
     */
    Order save(Order order);

    /**
     * Returns list of orders authenticated user.
     *
     * @param user name of authenticated user.
     * @return An object of type {@link List} is a list of orders of authenticated user.
     */
    List<Order> findOrderByUser(User user);
}
