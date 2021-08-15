package com.personal.site.controller;

import com.personal.site.domain.Product;
import com.personal.site.service.ProductService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

//    @PostMapping("/products/add")
//    public String addProduct(@RequestParam("name") String name,
//                             @RequestParam("username") String username,
//                             @RequestParam("price") Long price,
//                             @RequestParam("description") String description,
//                             @RequestParam("image")MultipartFile image,
//                             Model model) throws IOException {
//        String id = productService.addProduct(name, username, price, description, image);
//        return "redirect:/products/" + id;
//    }

//    @PostMapping("/products/add")
//    public String addProduct(@RequestBody String name,
//                             @RequestBody String username,
//                             @RequestBody Long price,
//                             @RequestBody String description,
////                             @RequestBody MultipartFile image,
//                             Model model) throws IOException {
//        System.out.println(name);
////        String id = productService.addProduct(name, username, price, description, image);
//        String id = productService.addProduct(name, username, price, description);
//        return "ok";
//    }

//    @PostMapping("/products/add")
//    public String addProduct(@RequestBody Product product) throws IOException {
//        System.out.println("product.getName() = " + product.getName());
//        System.out.println("product.getPrice() = " + product.getPrice());
//        String id = productService.addProduct(product);
//        return "ok";
//    }

    @PostMapping(path = "/products/add", consumes = {MULTIPART_FORM_DATA_VALUE})
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("username") String username,
                             @RequestParam("price") Long price,
                             @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile image) throws IOException {
//    public String addProduct(@RequestBody String body) throws IOException {
//        System.out.println("body = " + body);
//        System.out.println("image.getBytes() = " + image.getBytes());
        Product product = new Product(name, username, price, description);
        product.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        productService.addProduct(product);
        return "ok";
    }

//    @GetMapping("/products/{name}")
//    public String getProduct(@PathVariable String name) {
//        Product product = productService.getProduct(name);
//
//        return Base64.getEncoder().encodeToString(product.getImage().getData());
//    }
}
