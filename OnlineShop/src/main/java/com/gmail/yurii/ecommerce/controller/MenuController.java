package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    /**
     * Service object for working with products.
     */
    private final WineService wineService;

    /**
     * Constructor for initializing the main variables of the product controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param wineService Service object for working with products.
     */
    @Autowired
    public MenuController(WineService wineService) {
        this.wineService = wineService;
    }

    /**
     * Returns all products to the menu page with pagination.
     * URL request {"/menu"}, method GET.
     *
     * @param pageable object that specifies the information of the requested page.
     * @param model    class object {@link Model}.
     * @return menu page with model attributes.
     */
    @GetMapping
    public String mainMenu(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable, Model model) {
        Page<Wine> page = wineService.findAll(pageable);
        int[] pagination = ControllerUtils.computePagination(page);
        getMinmaxWinePrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu");
        model.addAttribute("page", page);

        return "menu";
    }

    /**
     * Returns list of wines to the menu page with pagination, which has the same wine manufacturer
     * with the value of the input parameter.
     * URL request {"/menu/{brand}"}, method GET.
     *
     * @param pageable object that specifies the information of the requested page.
     * @param brand wine manufacturer.
     * @param model    class object {@link Model}.
     * @return menu page with model attributes.
     */
    @GetMapping("{brand}")
    public String findByBrand(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @PathVariable String brand,
            Model model
    ) {
        Page<Wine> wineList = wineService.findByBrand(brand, pageable);
        int[] pagination = ControllerUtils.computePagination(wineList);
        getMinmaxWinePrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/" + brand);
        model.addAttribute("page", wineList);

        return "menu";
    }

    /**
     * Returns list of wines to the menu page with pagination, which has the same gender
     * with the value of the input parameter.
     * URL request {"/menu/gender/{gender}"}, method GET.
     *
     * @param pageable      object that specifies the information of the requested page.
     * @param color wine gender.
     * @param model         class object {@link Model}.
     * @return menu page with model attributes.
     */
    @GetMapping("gender/{gender}")
    public String findByColor(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @PathVariable("gender") String color,
            Model model
    ) {
        Page<Wine> gender = wineService.findByColor(color, pageable);
        int[] pagination = ControllerUtils.computePagination(gender);
        getMinmaxWinePrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/gender/" + color);
        model.addAttribute("page", gender);

        return "menu";
    }

    /**
     * Returns list of perfumes to the menu page with pagination, by selected parameters.
     * URL request {"/menu/search"}, method GET.
     *
     * @param pageable      object that specifies the information of the requested page.
     * @param gender        wine gender.
     * @param brands     wine manufacturers.
     * @param startingPrice the starting price of the product that the user enters.
     * @param endingPrice   the ending price of the product that the user enters.
     * @param model         class object {@link Model}
     * @return menu page with model attributes.
     */
    @GetMapping("search")
    public String searchByParameters(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam(value = "gender", required = false, defaultValue = "") List<String> gender,
            @RequestParam(value = "brands", required = false, defaultValue = "") List<String> brands,
            @RequestParam(value = "startingPrice", required = false, defaultValue = "0") Integer startingPrice,
            @RequestParam(value = "endingPrice", required = false, defaultValue = "0") Integer endingPrice,
            Model model
    ) {
        StringBuilder urlBuilder = new StringBuilder();
        Page<Wine> perfumesSearch = null;
        getMinmaxWinePrice(model);

        if (gender.isEmpty() && brands.isEmpty()) {
            Page<Wine> priceRange = wineService.findByPriceBetween(startingPrice, endingPrice, pageable);
            int[] pagination = ControllerUtils.computePagination(priceRange);

            model.addAttribute("pagination", pagination);
            model.addAttribute("url", "/menu/search?startingPrice=" + startingPrice + "&endingPrice=" + endingPrice);
            model.addAttribute("page", priceRange);

            return "menu";
        }

        if (gender.isEmpty()) {
            perfumesSearch = wineService.findByBrandIn(brands, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(brands);
        } else if (brands.isEmpty()) {
            perfumesSearch = wineService.findByColorIn(gender, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(gender);
        } else if (!gender.isEmpty() && !brands.isEmpty()) {
            perfumesSearch = wineService.findByBrandInAndColorIn(brands, gender, pageable);
            List<String> urlArray = new ArrayList<String>(brands);
            urlArray.addAll(gender);
            urlBuilder = ControllerUtils.getUrlBuilder(urlArray);
        }

        int[] pagination = ControllerUtils.computePagination(perfumesSearch);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/search" + urlBuilder);
        model.addAttribute("page", perfumesSearch);

        return "menu";
    }

    private void getMinmaxWinePrice(Model model) {
        BigDecimal minWinePrice = wineService.minWinePrice();
        BigDecimal maxWinePrice = wineService.maxWinePrice();

        model.addAttribute("minWinePrice", minWinePrice);
        model.addAttribute("maxWinePrice", maxWinePrice);
    }
}