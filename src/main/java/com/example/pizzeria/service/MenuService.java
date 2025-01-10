package com.example.pizzeria.service;

import com.example.pizzeria.model.MenuListing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    List<MenuListing> getMenu();

    MenuListing getListingById(long id);

    void deleteListingById(long id);

    void saveListing(MenuListing menuListing);
}
