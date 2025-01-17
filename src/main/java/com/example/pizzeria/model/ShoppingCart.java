package com.example.pizzeria.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Pizza pizza, int quantity) {
        for (CartItem item : items) {
            if (item.getPizza().getId().equals(pizza.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(pizza, quantity));
    }

    public void removeItem(Pizza pizza) {
        items.removeIf(item -> item.getPizza().getId().equals(pizza.getId()));
    }

    public void removeItemById(long id) {
        items.removeIf(item -> item.getPizza().getId().equals(id));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getPizza().getPrice() * item.getQuantity())
                .sum();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void clear() {
        items.clear();
    }
}
