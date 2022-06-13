package com.gmail.yurii.ecommerce.service;

import com.gmail.yurii.ecommerce.domain.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface WineService {

    List<Wine> findAll();

    Page<Wine> findAll(Pageable pageable);

    Page<Wine> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

    Page<Wine> findByBrand(String brand, Pageable pageable);

    Page<Wine> findByColor(String color, Pageable pageable);

    Page<Wine> findByColorIn(List<String> colors, Pageable pageable);

    Page<Wine> findByBrandOrWineTitle(String brand, String wineTitle, Pageable pageable);

    Page<Wine> findByBrandInAndColorIn(List<String> brands, List<String> colors, Pageable pageable);

    Page<Wine> findByBrandInOrColorIn (List<String> brands, List<String> colors, Pageable pageable);

    Page<Wine> findByBrandIn (List<String> brands, Pageable pageable);

    BigDecimal minWinePrice();

    BigDecimal maxWinePrice();

    void saveProductInfoById(String wineTitle, String brand, Integer year, String country, String color,
                             String grape, String combination, String decantation, String description,
                             String filename, Integer price, String volume, String type, Long id);


    Wine save(Wine wine);
}