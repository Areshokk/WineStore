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
    public MainController(WineService wineService) {
        this.wineService = wineService;
    }

    /**
     * Returns all products to the main page.
     * URL request {"/"}, method GET.
     *
     * @return main page with model attributes.
     */
    @GetMapping("/")
    public String home(Model model) {
        List<Wine> wines = wineService.findAll();
        model.addAttribute("wines", wines);

        return "main";
    }

    /**
     * Returns page with web-store contact details.
     * URL request {"contacts"}, method GET.
     *
     * @return contacts page with model attributes..
     */
    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    /**
     * Returns user cabinet page.
     * URL request {"cabinet"}, method GET.
     *
     * @return userCabinet page with model attributes.
     */
    @GetMapping("/cabinet")
    public String userCabinet() {
        return "user/userCabinet";
    }

    /**
     * Returns "menu" page with a product that matches the wine title or brand.
     * URL request {"/search"}, method GET.
     *
     * @param filter    requested parameter for product search.
     * @param pageable  object that specifies the information of the requested page.
     * @param model     class object {@link Model}.
     * @return menu page with model attributes.
     */
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

    /**
     * Returns to page "/product/{id}" with a product that matches the input id parameter.
     * URL request {"/product/{id}"}, method GET.
     *
     * @param wine product "id" to be returned to the page.
     * @return product page with model attributes.
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Wine wine, Model model) {
        model.addAttribute("wine", wine);

        return "product";
    }
}