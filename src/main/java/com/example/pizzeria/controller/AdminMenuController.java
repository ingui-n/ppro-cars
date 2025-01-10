package com.example.pizzeria.controller;

import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.service.AdminMenuService;
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
@RequestMapping("/admin/menu")
public class AdminMenuController {

    private final AdminMenuService adminMenuService;

    @Autowired
    public AdminMenuController(AdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

    @GetMapping({"/", ""})
    public String list(Model model) {
        model.addAttribute("listings", adminMenuService.getMenu());
        return "admin_menu_listings";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        MenuListing listing = adminMenuService.getListingById(id);
        if (listing != null) {
            model.addAttribute("listing", listing);
            return "admin_menu_listing_detail";
        }
        return "redirect:/admin/menu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        adminMenuService.deleteListingById(id);
        return "redirect:/admin/menu";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("listing", new MenuListing());
        model.addAttribute("edit", false);
        return "admin_menu_listing_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        MenuListing menuListing = adminMenuService.getListingById(id);
        if (menuListing != null) {
            model.addAttribute("listing", menuListing);
            model.addAttribute("edit", true);
            return "admin_menu_listing_edit";
        }
        return "redirect:/admin/menu";
    }

    @PostMapping("/save")
    public String save(@Valid MenuListing menuListing, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("listings", adminMenuService.getMenu());
            return "admin_menu_listing_edit";
        }
        adminMenuService.saveListing(menuListing);
        return "redirect:/admin/menu";
    }
}
