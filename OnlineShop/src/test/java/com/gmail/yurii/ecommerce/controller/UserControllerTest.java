package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.domain.Role;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.repos.WineRepository;
import com.gmail.yurii.ecommerce.repos.UserRepository;
import com.gmail.yurii.ecommerce.service.WineService;
import com.gmail.yurii.ecommerce.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WineRepository wineRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WineService wineService;

    @Test
    public void getAllProductsTest() {
        List<Wine> wineList = new ArrayList<>();
        wineList.add(new Wine());

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wineList);

        when(wineRepository.findAll(pageable)).thenReturn(page);

        assertEquals(1, wineService.findAll(pageable).getSize());
    }

    @Test
    public void addProductTest() {
        Wine wine = new Wine();
        wine.setId(1L);
        wine.setBrand("test");
        wine.setWineTitle("test");

        when(wineRepository.save(wine)).thenReturn(wine);

        assertEquals(wine, wineService.save(wine));
        assertNotNull(wine.getId());
    }

    @Test
    public void userListTest() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void userSaveTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Bob");
        user.setRoles(Collections.singleton(Role.ADMIN));

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.save(user));
        assertNotNull(user.getId());
        assertTrue(user.getUsername().equals("Bob"));
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ADMIN)));
    }

    @Test
    public void updateProfileInfoTest() {
        User user = new User();
        user.setPassword("test");
        user.setEmail("test@test.com");

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user.getPassword(), "test");
        assertEquals(user.getEmail(), "test@test.com");
        assertEquals(user, userService.save(user));
    }
}