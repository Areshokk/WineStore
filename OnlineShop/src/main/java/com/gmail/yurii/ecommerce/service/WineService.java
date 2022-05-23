package com.gmail.yurii.ecommerce.service;

import com.gmail.yurii.ecommerce.domain.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface WineService {
    /**
     * Return list of all wines.
     *
     * @return list of {@link Wine}.
     */
    List<Wine> findAll();

    /**
     * Returns list of wines.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findAll(Pageable pageable);

    /**
     * Returns list of wines in which the price is in the range between of starting price and ending price.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

    /**
     * Returns list of wines which has the same wine manufacturer with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brand wine manufacturer to return.
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByBrand(String brand, Pageable pageable);

    /**
     * Returns list of wines which has the same color with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param color wine color to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByColor(String color, Pageable pageable);

    /**
     * Returns list of wines which has the same colors with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param colors wine colors to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByColorIn(List<String> colors, Pageable pageable);

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
    Page<Wine> findByBrandOrWineTitle(String brand, String wineTitle, Pageable pageable);

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
    Page<Wine> findByBrandInAndColorIn(List<String> brands, List<String> colors, Pageable pageable);

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
    Page<Wine> findByBrandInOrColorIn (List<String> brands, List<String> colors, Pageable pageable);

    /**
     * Returns list of wines which has the same wine manufacturers
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brands wine manufacturers to return.
     * @param pageable  object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByBrandIn (List<String> brands, Pageable pageable);

    /**
     * Returns minimum price of wine.
     *
     * @return minimum price {@link Wine}.
     */
    BigDecimal minWinePrice();

    /**
     * Returns maximum price of wine from the database.
     *
     * @return maximum price {@link Wine}.
     */
    BigDecimal maxWinePrice();

    /**
     * Save updated wine.
     *
     * @param wineTitle          wine title to update.
     * @param brand              wine manufacturer to update.
     * @param year                  the year the wine was released to update.
     * @param country               manufacturer country to update.
     * @param color         color to update to update.
     * @param grape    grape to update.
     * @param combination  combination to update.
     * @param decantation    decantation to update.
     * @param description           wine description to update.
     * @param filename              wine image to update.
     * @param price                 wine price to update.
     * @param volume                wine volume to update.
     * @param type                  type of tyoe to update.
     * @param id                    the unique code of the wine to update.
     */
    void saveProductInfoById(String wineTitle, String brand, Integer year, String country, String color,
                             String grape, String combination, String decantation, String description,
                             String filename, Integer price, String volume, String type, Long id);

    /**
     * Save wine info.
     *
     * @param wine wine object to return.
     * @return The {@link Wine} class object which will be saved in the database.
     */
    Wine save(Wine wine);
}