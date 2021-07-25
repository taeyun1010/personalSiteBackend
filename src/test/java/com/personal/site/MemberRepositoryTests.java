package com.personal.site;

import com.personal.site.domain.Member;
import com.personal.site.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class MemberRepositoryTests {
//
//	@Autowired
//	private MemberRepository repository;
//
//	@AfterEach
//	public void afterEach() {
//		repository.deleteAll();
//	}
//
//	@Test
//	void findByEmail() {
//		Member member = new Member("taeyun1010@gmail.com", "1234", "taeyun1010",
//				"Korea", "01011112222", "https://www.example.com", "Male");
//		repository.save(member);
//		Member result = repository.findByEmail("taeyun1010@gmail.com");
//
//		assertThat(result).isEqualTo(member);
//	}
//
//}
