package com.example.pizzeria.controller;

import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("listings", menuService.getMenu());
        return "menu";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        MenuListing listing = menuService.getListingById(id);
        if (listing != null) {
            model.addAttribute("listing", listing);
            return "menu_listing_detail";
        }
        return "redirect:/menu/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        menuService.deleteListingById(id);
        return "redirect:/menu/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listing", new MenuListing());
        model.addAttribute("edit", false);
        return "menu_listing_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        MenuListing menuListing = menuService.getListingById(id);
        if (menuListing != null) {
            model.addAttribute("listing", menuListing);
            model.addAttribute("edit", true);
            return "menu_listing_edit";
        }
        return "redirect:/menu/";
    }

    @PostMapping("/save")
    public String save(@Valid MenuListing menuListing, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("listings", menuService.getMenu());
            return "menu_listing_edit";
        }
        menuService.saveListing(menuListing);
        return "redirect:/menu/";
    }

}
