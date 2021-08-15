package com.personal.site.controller;

import com.personal.site.domain.Cart;
import com.personal.site.domain.Product;
import com.personal.site.repository.CartRepository;
import com.personal.site.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/carts/{username}")
    public List<Product> getCart(@PathVariable String username) {
        Cart cart = cartRepository.findByUsername(username);
        List<Product> products = cart.getProducts();
//        List<Product> products = new ArrayList<>();
        return products;
    }

    @PostMapping("/carts/{username}/{name}")
    public void addToCart(@PathVariable String username, @PathVariable String name) {
//        System.out.println("name = " + name);

        Product product = productRepository.findByName(name);
//        System.out.println("product = " + product);
//        System.out.println("product.getPrice() = " + product.getPrice());
        cartRepository.addToCart(username, product);
    }
}
