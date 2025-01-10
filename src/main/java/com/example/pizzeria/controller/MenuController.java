package com.example.pizzeria.controller;

import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("listings", menuService.getMenu());
        return "menu_listings";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        MenuListing listing = menuService.getListingById(id);
        if (listing != null) {
            model.addAttribute("listing", listing);
            return "menu_listing_detail";
        }
        return "redirect:/menu";
    }
}
