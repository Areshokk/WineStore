package com.gmail.yurii.ecommerce.service.Impl;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.repos.WineRepository;
import com.gmail.yurii.ecommerce.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    @Autowired
    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @Override
    public List<Wine> findAll() {
        return wineRepository.findAll();
    }

    @Override
    public Page<Wine> findAll(Pageable pageable) {
        return wineRepository.findAll(pageable);

    }

    @Override
    public Page<Wine> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable) {
        return wineRepository.findByPriceBetween(startingPrice, endingPrice, pageable);
    }

    @Override
    public Page<Wine> findByBrand(String brand, Pageable pageable) {
        return wineRepository.findByBrand(brand, pageable);
    }

    @Override
    public Page<Wine> findByColor(String color, Pageable pageable) {
        return wineRepository.findByColor(color, pageable);
    }

    @Override
    public Page<Wine> findByColorIn(List<String> colors, Pageable pageable) {
        return wineRepository.findByColorIn(colors, pageable);
    }

    @Override
    public Page<Wine> findByBrandOrWineTitle(String brand, String wineTitle, Pageable pageable) {
        return wineRepository.findByBrandOrWineTitle(brand, wineTitle, pageable);
    }

    @Override
    public Page<Wine> findByBrandInAndColorIn(List<String> brands, List<String> colors, Pageable pageable) {
        return wineRepository.findByBrandInAndColorIn(brands, colors, pageable);
    }

    @Override
    public Page<Wine> findByBrandInOrColorIn(List<String> brands, List<String> colors, Pageable pageable) {
        return wineRepository.findByBrandInOrColorIn(brands, colors, pageable);
    }

    @Override
    public Page<Wine> findByBrandIn(List<String> brands, Pageable pageable) {
        return wineRepository.findByBrandIn(brands, pageable);
    }

    @Override
    public BigDecimal minWinePrice() {
        return wineRepository.minWinePrice();
    }

    @Override
    public BigDecimal maxWinePrice() {
        return wineRepository.maxWinePrice();
    }

    @Override
    public void saveProductInfoById(String wineTitle, String brand, Integer year, String country,
                                    String color, String grape, String combination,
                                    String decantation, String description, String filename,
                                    Integer price, String volume, String type, Long id
    ) {
        wineRepository.saveProductInfoById(wineTitle, brand, year, country, color, grape,
                combination, decantation, description, filename, price, volume, type, id);
    }

    @Override
    public Wine save(Wine wine) {
        return wineRepository.save(wine);
    }
}
