package com.gmail.yurii.ecommerce.service;

import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.service.Impl.UserServiceImpl;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean addUser(User user);

    boolean activateUser(String code);

    void sendMessage(User user);

    List<User> findAll();

    void userSave(String username, Map<String, String> form, User user);

    void updateProfile(User user, String password, String email);

    User findByUsername(String username);

    User findByActivationCode(String code);

    User save(User user);
}