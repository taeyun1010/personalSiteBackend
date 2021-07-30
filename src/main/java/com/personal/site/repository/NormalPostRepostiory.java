package com.personal.site.repository;

import com.personal.site.domain.NormalPost;
import com.personal.site.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface NormalPostRepostiory extends MongoRepository<NormalPost, String> {

}
