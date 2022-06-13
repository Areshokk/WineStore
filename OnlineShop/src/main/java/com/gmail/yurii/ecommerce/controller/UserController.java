package com.gmail.yurii.ecommerce.controller;

import com.gmail.yurii.ecommerce.domain.Wine;
import com.gmail.yurii.ecommerce.domain.Role;
import com.gmail.yurii.ecommerce.domain.User;
import com.gmail.yurii.ecommerce.service.WineService;
import com.gmail.yurii.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final WineService wineService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserController(UserService userService, WineService wineService) {
        this.userService = userService;
        this.wineService = wineService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("productlist")
    public String getAllProducts(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable, Model model) {
        Page<Wine> page = wineService.findAll(pageable);
        int[] pagination = ControllerUtils.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "productlist");
        model.addAttribute("page", page);

        return "admin/productList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("productlist/{wine}")
    public String editProduct(@PathVariable Wine wine, Model model) {
        model.addAttribute("wine", wine);

        return "admin/productEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("productlist")
    public String saveEditedProduct(Wine wine, @RequestParam("file") MultipartFile file) throws IOException {
        saveFile(wine, file);

        wineService.saveProductInfoById(wine.getWineTitle(), wine.getBrand(), wine.getYear(),
                wine.getCountry(), wine.getColor(), wine.getGrape(), wine.getCombination(),
                wine.getDecantation(), wine.getDescription(), wine.getFilename(), wine.getPrice(),
                wine.getVolume(), wine.getType(), wine.getId());

        log.debug("ADMIN save edited product to DB: id={}, brand={}, wine={}",
                wine.getId(), wine.getBrand(), wine.getWineTitle());

        return "redirect:/user/productlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("add")
    public String addProductToBd() {
        return "admin/addToDb";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add")
    public String addProductToBd(
            @Valid Wine wine,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);

            return "admin/addToDb";
        } else {
            saveFile(wine, file);

            wineService.save(wine);

            log.debug("ADMIN added product to DB: id={}, brand={}, wine={}",
                    wine.getId(), wine.getBrand(), wine.getWineTitle());
        }

        return "admin/addToDb";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin/userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "admin/userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSaveEditForm(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.userSave(username, form, user);

        log.debug("ADMIN save edited user form: id={}, name={}, form={}", user.getId(), username, form);

        return "redirect:/user";
    }

    @GetMapping("edit")
    public String getProfileInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "user/userEditProfile";
    }

    @PostMapping("edit")
    public String updateProfileInfo(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        log.debug("{} change personal info: password={}, email={}", user.getUsername(), password, email);

        return "redirect:/cabinet";
    }

    private void saveFile(Wine wine, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String resultFilename =file.getOriginalFilename();

//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            wine.setFilename(resultFilename);
        }
    }
}