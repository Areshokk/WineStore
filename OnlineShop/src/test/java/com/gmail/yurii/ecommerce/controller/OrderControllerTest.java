package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Order;
import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.repos.OrderRepository;
import com.gmail.yurii.ecommerce.repos.UserRepository;
import com.gmail.yurii.ecommerce.service.Impl.MailSender;
import com.gmail.yurii.ecommerce.service.OrderService;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderController orderController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void getOrderTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        User user = new User();
        Wine wine = new Wine();

        user.setWineList(wines);
        user.getWineList().add(wine);

        assertNotNull(user.getWineList());
        assertEquals(1, user.getWineList().size());
    }

    @Test
    public void postOrderTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        User user = new User();
        Wine wine = new Wine();

        user.setWineList(wines);
        user.getWineList().add(wine);

        userService.save(user);

        Order order = new Order(user);
        order.setId(1);
        order.setFirstName("John");
        order.setWineList(user.getWineList());

        orderService.save(order);

        assertNotNull(user);
        assertNotNull(user.getWineList());
        assertEquals(1, user.getWineList().size());
        assertNotNull(order);
        assertEquals(1, order.getId());
        assertEquals("John", order.getFirstName());
        assertEquals(1, order.getWineList().size());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void finalizeOrderTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        User user = new User();
        Wine wine = new Wine();
        Order order = new Order(user);

        user.setWineList(wines);
        user.getWineList().add(wine);
        order.setWineList(user.getWineList());

        when(orderService.findAll()).thenReturn(Collections.singletonList(order));

        assertNotNull(user);
        assertNotNull(user.getWineList());
        assertEquals(1, user.getWineList().size());
        assertNotNull(order);
        assertEquals(1, order.getWineList().size());
    }
}
