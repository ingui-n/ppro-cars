package com.example.pizzeria.repository;

import com.example.pizzeria.model.MenuListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuListing, Long> {

}
