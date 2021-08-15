package com.personal.site.dal;

import com.personal.site.domain.Cart;
import com.personal.site.domain.Product;

public interface CartDal {
    Cart save(Cart cart);
    Cart findByUsername(String username);
    void addToCart(String username, Product product);
}
