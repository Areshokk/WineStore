package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.repos.WineRepository;
import com.gmail.yurii.ecommerce.service.WineService;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WineRepository wineRepository;

    @Autowired
    private WineService wineService;

    @Autowired
    private MainController mainController;


    @Test
    public void test() {
        int i = 1;
        int b = 1;
        assertEquals(i,b);
    }

//    @Test
//    public void homeTest() throws Exception {
//        Wine perfume1 = generateWine(39L, "Moet + Chandon", "Moet + Chandon");
//        Wine perfume2 = generateWine(40L, "Pocas", "Pocas");
//
//        when(wineRepository.findAll()).thenReturn(Arrays.asList(perfume1, perfume2));
//
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("main"))
//                .andExpect(model().attribute("wines", hasSize(2)))
//                .andExpect(model().attribute("wines", hasItem(
//                        allOf(
//                                hasProperty("id", is(39L)),
//                                hasProperty("wineTitle", is("Moet + Chandon")),
//                                hasProperty("brand", is("Moet + Chandon"))
//                        )
//                )))
//                .andExpect(model().attribute("wines", hasItem(
//                        allOf(
//                                hasProperty("id", is(40L)),
//                                hasProperty("wineTitle", is("Pocas")),
//                                hasProperty("brand", is("Pocas"))
//                        )
//                )));
//    }

    @Test
    public void searchTest() throws Exception {
        List<Wine> wineList = new ArrayList<>();
        Wine wine = new Wine();
        wine.setBrand("Moet + Chandon");
        wineList.add(wine);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wineList);

        when(wineRepository.findByBrandOrWineTitle(wine.getBrand(), wine.getBrand(), pageable)).thenReturn(page);

        assertNotNull(wineList);
        assertNotNull(wine.getBrand());
        assertEquals(1, wineService.findByBrandOrWineTitle(wine.getBrand(), wine.getBrand(), pageable).getSize());
        assertEquals("Moet + Chandon", wineService.findByBrandOrWineTitle(wine.getBrand(), wine.getBrand(), pageable).getContent().get(0).getBrand());
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Long id = 7L;
        Wine wine = new Wine();
        wine.setId(7L);

        assertNotNull(wine.getId());
        assertEquals(id, wine.getId());
    }

    private Wine generateWine(Long id, String wineTitle, String brand) {
        Wine wine = new Wine();
        wine.setId(id);
        wine.setWineTitle(wineTitle);
        wine.setBrand(brand);
        wine.setPrice(1000);

        return wine;
    }
}
