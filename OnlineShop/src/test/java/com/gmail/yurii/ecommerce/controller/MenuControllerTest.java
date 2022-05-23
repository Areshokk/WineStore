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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MenuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WineRepository wineRepository;

    @Autowired
    private WineService wineService;

    @Autowired
    private MenuController menuController;

    @Test
    public void mainMenuTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        Wine wine = new Wine();
        wines.add(wine);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wines);

        when(wineRepository.findAll(pageable)).thenReturn(page);

        assertNotNull(wines);
        assertEquals(1, wineService.findAll(pageable).getSize());
    }

    @Test
    public void findByBrandTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        Wine wine = new Wine();
        wine.setBrand("Moet + Chandon");
        wines.add(wine);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wines);

        when(wineRepository.findByBrand(wine.getBrand(), pageable)).thenReturn(page);

        assertNotNull(wines);
        assertEquals(1, wineService.findByBrand(wine.getBrand(), pageable).getSize());
        assertEquals("Moet + Chandon", wineService.findByBrand(wine.getBrand(), pageable)
                .getContent().get(0).getBrand());
    }

    @Test
    public void findByWineColorTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        Wine wine = new Wine();
        wine.setColor("червоне");
        wines.add(wine);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wines);

        when(wineRepository.findByColor(wine.getColor(), pageable)).thenReturn(page);

        assertNotNull(wines);
        assertEquals(1, wineService.findByColor(wine.getColor(), pageable).getSize());
        assertEquals("червоне", wineService.findByColor(wine.getColor(), pageable)
                .getContent().get(0).getColor());
    }

    @Test
    public void searchByParametersTest() throws Exception {
        List<Wine> wines = new ArrayList<>();
        Wine wine = new Wine();
        wine.setColor("червоне");
        wine.setBrand("Moet + Chandon");
        wine.setPrice(1000);
        wines.add(wine);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Wine> page = new PageImpl<>(wines);

        when(wineRepository.findByPriceBetween(900, 1100, pageable)).thenReturn(page);
        when(wineRepository.findByBrandIn(Collections.singletonList(wines.get(0).getBrand()), pageable)).thenReturn(page);
        when(wineRepository.findByBrandInAndColorIn(
                Collections.singletonList(wines.get(0).getBrand()),
                Collections.singletonList(wines.get(0).getColor()),
                pageable)).thenReturn(page);

        assertNotNull(wines);
        assertEquals(1000, wineService.findByPriceBetween(900, 1100, pageable)
                .getContent().get(0).getPrice());
        assertEquals("Moet + Chandon", wineService.findByBrandIn(Collections.singletonList(wines.get(0).getBrand()), pageable)
                .getContent().get(0).getBrand());
        assertEquals(1, wineService.findByBrandInAndColorIn(
                Collections.singletonList(wines.get(0).getBrand()),
                Collections.singletonList(wines.get(0).getColor()),
                pageable).getSize());
    }
}