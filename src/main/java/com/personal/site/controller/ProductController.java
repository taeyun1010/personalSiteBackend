package com.personal.site.controller;

import com.personal.site.domain.Product;
import com.personal.site.repository.ProductRepository;
import com.personal.site.service.ProductService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    //TODO: url을 이렇게 하면 /products/{id} 로 delete가 안 된다. method not supported가 뜬다.
//    @GetMapping("/products/{skip}")
//    public List<Product> getProducts(@PathVariable String skip) {
//        List<Product> products = productService.getTop3ProductsOrderByName();
//        return products;
//    }

    @GetMapping("/products/top")
    public Iterable<Product> getProducts() {
        PageRequest page = PageRequest.of(
                0, 3, Sort.by("id").descending()
        );
        return productRepository.findAll(page).getContent();
    }

    @PostMapping(path = "/products/add", consumes = {MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("username") String username,
                             @RequestParam("price") Long price,
                             @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile image) throws IOException {
        Product product = new Product(name, username, price, description);
        product.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        productService.addProduct(product);
        return "ok";
    }
}
