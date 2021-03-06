package com.personal.site.repository;

import com.personal.site.domain.Member;
import com.personal.site.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins="*")
public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findByName(String name);
    public List<Product> findTop3ByOrderByName();
}
