package com.personal.site.repository;

import com.personal.site.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins="*")
public interface MemberRepository extends MongoRepository<Member, String> {
    public Member findByEmail(String email);
    Optional<Member> findByNickname(String nickname);

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);
}
