package com.personal.site.repository;

import com.personal.site.domain.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface CounterRepository extends MongoRepository<Counter, String> {

}
