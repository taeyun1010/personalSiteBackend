package com.personal.site.security.services;

import com.personal.site.domain.Member;
import com.personal.site.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    MemberRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
        return UserDetailsImpl.build(member);
    }
}
