package com.example.pizzeria.service;

import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public AdminMenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuListing> getMenu() {
        return menuRepository.findAll();
    }

    @Override
    public MenuListing getListingById(long id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteListingById(long id) {
        Optional<MenuListing> menuListing = menuRepository.findById(id);
        if (menuListing.isPresent()) {
            menuRepository.delete(menuListing.get());
        }
    }

    @Override
    public void saveListing(MenuListing menuListing) {
        menuRepository.save(menuListing);
    }
}
