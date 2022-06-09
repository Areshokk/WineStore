package com.gmail.yurii.ecommerce.repos;

import com.gmail.yurii.ecommerce.domain.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Long> {
    /**
     * Returns list of wines from the database.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findAll(Pageable pageable);

    /**
     * Returns list of wines from the database in which the price is in the range between of starting price and ending price.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

    /**
     * Returns list of wines from the database which has the same wine manufacturer with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brand wine manufacturer to return.
     * @param pageable object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByBrand(String brand, Pageable pageable);

    /**
     * Returns list of wines from the database which has the same color with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param color wine color to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByColor(String color, Pageable pageable);

    /**
     * Returns list of wines from the database which has the same colors with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param color wine colors to return.
     * @param pageable      object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByColorIn(List<String> color, Pageable pageable);

    /**
     * Returns list of wines from the database which has the same wine manufacturer or wine title
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
     * Returns list of wines from the database which has the same wine manufacturers and colors
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
     * Returns list of wines from the database which has the same wine manufacturers and colors
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
     * Returns list of wines from the database which has the same wine manufacturers
     * with the value of the input parameter.
     * A {@link Page} is a sublist of a list of objects.
     *
     * @param brands wine manufacturers to return.
     * @param pageable  object that specifies the information of the requested page.
     * @return list of {@link Wine}.
     */
    Page<Wine> findByBrandIn (List<String> brands, Pageable pageable);

    /**
     * Returns minimum price of wine from the database.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @return minimum price {@link Wine}.
     */
    @Query(value = "SELECT min(price) FROM Wine ")
    BigDecimal minWinePrice();

    /**
     * Returns maximum price of wine from the database.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @return maximum price {@link Wine}.
     */
    @Query(value = "SELECT max(price) FROM Wine ")
    BigDecimal maxWinePrice();

    /**
     * Save updated wine to the database.
     * The @Modifying annotation declaring manipulating queries.
     * The @Transactional annotation - before the execution of the method marked with this annotation,
     * a transaction starts, after the method is executed, the transaction is committed,
     * and when a RuntimeException is thrown, it is rolled back.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @param wineTitle          wine title to update.
     * @param brand              wine manufacturer to update.
     * @param year                  the year the wine was released to update.
     * @param country               manufacturer country to update.
     * @param color         color to update to update.
     * @param grape     fragrance top notes to update.
     * @param combination  fragrance middle notes to update.
     * @param decantation    fragrance base notes to update.
     * @param description           wine description to update.
     * @param filename              wine image to update.
     * @param price                 wine price to update.
     * @param volume                wine volume to update.
     * @param type                  type of fragrance to update.
     * @param id                    the unique code of the wine to update.
     */
    @Modifying
    @Transactional
    @Query("update Wine p set p.wineTitle = ?1, p.brand = ?2, p.year = ?3, p.country = ?4, " +
            "p.color = ?5, p.grape = ?6, p.combination = ?7, p.decantation = ?8," +
            "p.description = ?9, p.filename = ?10, p.price = ?11, p.volume = ?12, p.type = ?13  where p.id = ?14")
    void saveProductInfoById(String wineTitle, String brand, Integer year, String country, String color,
                             String grape, String combination, String decantation, String description,
                             String filename, Integer price, String volume, String type, Long id);
}