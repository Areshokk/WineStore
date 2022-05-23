package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.repos.UserRepository;
import com.gmail.yurii.ecommerce.service.Impl.MailSender;
import com.gmail.yurii.ecommerce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void getCartTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        wines.add(new Wine());
        wines.add(new Wine());

        User user = new User();
        user.setWineList(wines);

        assertNotNull(user.getWineList());
        assertEquals(2, user.getWineList().size());
    }

    @Test
    public void addToCartTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        User user = new User();

        user.setWineList(wines);
        user.getWineList().add(new Wine());

        userService.addUser(user);

        assertNotNull(user.getWineList());
        assertEquals(1, user.getWineList().size());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void removeFromCartTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        User user = new User();
        Wine wine = new Wine();

        user.setWineList(wines);
        user.getWineList().add(wine);

        userService.addUser(user);

        user.getWineList().remove(wine);

        userService.addUser(user);

        assertNotNull(user.getWineList());
        assertEquals(0, user.getWineList().size());

        Mockito.verify(userRepository, Mockito.times(2)).save(user);
    }
}
