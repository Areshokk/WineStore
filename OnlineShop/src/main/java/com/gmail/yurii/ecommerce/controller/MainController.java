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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final WineService wineService;

    @Autowired
    public MainController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Wine> wines = wineService.findAll();
        model.addAttribute("wines", wines);

        return "main";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "user/userCabinet";
    }

    @GetMapping("/search")
    public String search(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam String filter,
            Model model
    ) {
        Page<Wine> page = wineService.findByBrandOrWineTitle(filter, filter, pageable);
        int[] pagination = ControllerUtils.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu");
        model.addAttribute("page", page);

        return "menu";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Wine wine, Model model) {
        model.addAttribute("wine", wine);

        return "product";
    }
}