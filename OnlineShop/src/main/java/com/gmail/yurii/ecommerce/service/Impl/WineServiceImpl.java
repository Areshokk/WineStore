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
    /**
     * Implementation of the {@link WineRepository} interface
     * for working with wines with a database.
     */
    private final WineRepository wineRepository;

    /**
     * Constructor for initializing the main variables of the order service.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param wineRepository implementation of the {@link WineRepository} interface
     *                        for working with wines with a database.
     */
    @Autowired
    public WineServiceImpl(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    /**
     * Return list of all wines.
     *
     * @return list of {@link Wine}.
     */
    @Override
    public List<Wine> findAll() {
        return wineRepository.findAll();
    }

    /**
     * Returns list of wines.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findAll(Pageable pageable) {
        return wineRepository.findAll(pageable);

    }

    /**
     * Returns list of wines in which the price is in the range between of starting price and ending price.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable) {
        return wineRepository.findByPriceBetween(startingPrice, endingPrice, pageable);
    }

    /**
     * Returns list of wines which has the same wine manufacturer with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brand wine manufacturer to return.
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByBrand(String brand, Pageable pageable) {
        return wineRepository.findByBrand(brand, pageable);
    }

    /**
     * Returns list of wines which has the same color with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param color wine color to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByColor(String color, Pageable pageable) {
        return wineRepository.findByColor(color, pageable);
    }

    /**
     * Returns list of wines which has the same colors with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param colors wine colors to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByColorIn(List<String> colors, Pageable pageable) {
        return wineRepository.findByColorIn(colors, pageable);
    }

    /**
     * Returns list of wines which has the same wine manufacturer or wine title
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brand      wine manufacturer to return.
     * @param wineTitle  wine title to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByBrandOrWineTitle(String brand, String wineTitle, Pageable pageable) {
        return wineRepository.findByBrandOrWineTitle(brand, wineTitle, pageable);
    }

    /**
     * Returns list of wines which has the same wine manufacturers and colors
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brands wine manufacturers to return.
     * @param colors   colors to return.
     * @param pageable  object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByBrandInAndColorIn(List<String> brands, List<String> colors, Pageable pageable) {
        return wineRepository.findByBrandInAndColorIn(brands, colors, pageable);
    }

    /**
     * Returns list of wines which has the same wine manufacturers and colors
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brands wine manufacturers to return.
     * @param colors   colors to return.
     * @param pageable  object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByBrandInOrColorIn(List<String> brands, List<String> colors, Pageable pageable) {
        return wineRepository.findByBrandInOrColorIn(brands, colors, pageable);
    }

    /**
     * Returns list of wines which has the same wine manufacturers
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brands wine manufacturers to return.
     * @param pageable  object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    @Override
    public Page<Wine> findByBrandIn(List<String> brands, Pageable pageable) {
        return wineRepository.findByBrandIn(brands, pageable);
    }

    /**
     * Returns minimum price of wine.
     *
     * @return minimum price {@link Wine}.
     */
    @Override
    public BigDecimal minWinePrice() {
        return wineRepository.minWinePrice();
    }

    /**
     * Returns maximum price of wine from the database.
     *
     * @return maximum price {@link Wine}.
     */
    @Override
    public BigDecimal maxWinePrice() {
        return wineRepository.maxWinePrice();
    }

    /**
     * Save updated wine.
     *
     * @param wineTitle          wine title to update.
     * @param brand              wine manufacturer to update.
     * @param year                  the year the wine was released to update.
     * @param country               manufacturer country to update.
     * @param color         gender to update to update.
     * @param grape     grape to update.
     * @param combination  combination to update.
     * @param decantation    decantation to update.
     * @param description           wine description to update.
     * @param filename              wine image to update.
     * @param price                 wine price to update.
     * @param volume                wine volume to update.
     * @param type                  type of type to update.
     * @param id                    the unique code of the wine to update.
     */
    @Override
    public void saveProductInfoById(String wineTitle, String brand, Integer year, String country,
                                    String color, String grape, String combination,
                                    String decantation, String description, String filename,
                                    Integer price, String volume, String type, Long id
    ) {
        wineRepository.saveProductInfoById(wineTitle, brand, year, country, color, grape,
                combination, decantation, description, filename, price, volume, type, id);
    }

    /**
     * Save wine info.
     *
     * @param wine wine object to return.
     * @return The {@link Wine} class object which will be saved in the database.
     */
    @Override
    public Wine save(Wine wine) {
        return wineRepository.save(wine);
    }
}
