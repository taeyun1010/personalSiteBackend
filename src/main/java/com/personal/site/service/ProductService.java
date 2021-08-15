package com.personal.site.service;

import com.personal.site.domain.Product;
import com.personal.site.repository.ProductRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String addProduct(String name, String username, Long price, String description
//                             MultipartFile image) throws IOException {
                            ) throws IOException {
            Product product = new Product(name, username, price, description);
//        product.setImage(
//                new Binary(BsonBinarySubType.BINARY, image.getBytes())
//        );
        product = productRepository.insert(product);
        return product.getId();
    }

    public String addProduct(Product product) {
        Product result = productRepository.insert(product);
        return result.getId();
    }

    public Product getProduct(String name) {
        return productRepository.findByName(name);
    }
}
