package com.personal.site.repository;

import com.personal.site.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface MemberRepository extends MongoRepository<Member, String> {
    public Member findByEmail(String email);
}
