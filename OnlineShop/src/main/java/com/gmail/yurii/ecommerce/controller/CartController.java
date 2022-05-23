package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    /**
     * Service object for working with customer shopping cart.
     */
    private final UserService userService;

    /**
     * Constructor for initializing the main variables of the cart controller.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param userService service object for working with user shopping cart.
     */
    @Autowired
    public CartController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns customer shopping cart.
     * URL request {"/cart"}, method GET.
     *
     * @param userSession requested Authenticated customer.
     * @param model       class object {@link Model}.
     * @return cart page with model attributes.
     */
    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.findByUsername(userSession.getUsername());
        model.addAttribute("wines", userFromDB.getWineList());

        return "cart";
    }

    /**
     * Adds a product to the customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/add"}, method POST.
     *
     * @param wine     the product to add to the cart.
     * @param userSession request Authenticated customer.
     * @return redirect to cart page.
     */
    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("add") Wine wine,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());
        user.getWineList().add(wine);
        userService.save(user);

        return "redirect:/cart";
    }

    /**
     * Remove product from customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/remove"}, method POST.
     *
     * @param wine     the product to be removed from the customer shopping cart.
     * @param userSession request Authenticated customer.
     * @return redirect to cart page.
     */
    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam(value = "wineId") Wine wine,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());

        if (wine != null) {
            user.getWineList().remove(wine);
        }

        userService.save(user);

        return "redirect:/cart";
    }
}