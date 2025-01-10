package com.example.pizzeria.service;

import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
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
}
