package com.personal.site.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class Cart {
    @Id
    private String id;
    private String username;
    private List<Product> products = new ArrayList<>();

    public Cart(String username) {
        this.username = username;
    }
}
