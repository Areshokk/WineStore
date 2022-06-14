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

    private final WineService wineService;

    @Autowired
    public MenuController(WineService wineService) {
        this.wineService = wineService;
    }

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

    @GetMapping("color/{color}")
    public String findByColor(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @PathVariable("color") String color,
            Model model
    ) {
        Page<Wine> colors = wineService.findByColor(color, pageable);
        int[] pagination = ControllerUtils.computePagination(colors);
        getMinmaxWinePrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/color/" + color);
        model.addAttribute("page", colors);

        return "menu";
    }

    @GetMapping("search")
    public String searchByParameters(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam(value = "color", required = false, defaultValue = "") List<String> color,
            @RequestParam(value = "brands", required = false, defaultValue = "") List<String> brands,
            @RequestParam(value = "startingPrice", required = false, defaultValue = "0") Integer startingPrice,
            @RequestParam(value = "endingPrice", required = false, defaultValue = "0") Integer endingPrice,
            Model model
    ) {
        StringBuilder urlBuilder = new StringBuilder();
        Page<Wine> winesSearch = null;
        getMinmaxWinePrice(model);

        if (color.isEmpty() && brands.isEmpty()) {
            Page<Wine> priceRange = wineService.findByPriceBetween(startingPrice, endingPrice, pageable);
            int[] pagination = ControllerUtils.computePagination(priceRange);

            model.addAttribute("pagination", pagination);
            model.addAttribute("url", "/menu/search?startingPrice=" + startingPrice + "&endingPrice=" + endingPrice);
            model.addAttribute("page", priceRange);

            return "menu";
        }

        if (color.isEmpty()) {
            winesSearch = wineService.findByBrandIn(brands, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(brands);
        } else if (brands.isEmpty()) {
            winesSearch = wineService.findByColorIn(color, pageable);
            urlBuilder = ControllerUtils.getUrlBuilder(color);
        } else if (!color.isEmpty() && !brands.isEmpty()) {
            winesSearch = wineService.findByBrandInAndColorIn(brands, color, pageable);
            List<String> urlArray = new ArrayList<String>(brands);
            urlArray.addAll(color);
            urlBuilder = ControllerUtils.getUrlBuilder(urlArray);
        }

        int[] pagination = ControllerUtils.computePagination(winesSearch);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/search" + urlBuilder);
        model.addAttribute("page", winesSearch);

        return "menu";
    }

    private void getMinmaxWinePrice(Model model) {
        BigDecimal minWinePrice = wineService.minWinePrice();
        BigDecimal maxWinePrice = wineService.maxWinePrice();

        model.addAttribute("minWinePrice", minWinePrice);
        model.addAttribute("maxWinePrice", maxWinePrice);
    }
}