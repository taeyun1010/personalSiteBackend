package com.personal.site.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.personal.site.domain.Cart;
import com.personal.site.domain.ERole;
import com.personal.site.domain.Member;
import com.personal.site.domain.Role;
import com.personal.site.repository.CartRepository;
import com.personal.site.repository.MemberRepository;
import com.personal.site.repository.RoleRepository;
import com.personal.site.security.jwt.*;
import com.personal.site.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAddress(),
                userDetails.getPhone(),
                userDetails.getWebsite(),
                userDetails.getGender(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Member user = new Member(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getUsername(), signUpRequest.getAddress(),
                signUpRequest.getPhone(), signUpRequest.getWebsite(), signUpRequest.getGender());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        Cart cart = new Cart(user.getUsername());
        cartRepository.save(cart);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @DeleteMapping("/withdraw/{username}")
    public ResponseEntity<?> withdraw(@PathVariable String username, HttpServletRequest request) {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter();
        String jwt = authTokenFilter.parseJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String usernameFromJwt = jwtUtils.getUserNameFromJwtToken(jwt);

            if (!username.equals(usernameFromJwt)) {
                return ResponseEntity.status(401).body("You can only withdraw your account");
            }
            userRepository.deleteByUsername(username);
            cartRepository.deleteByUsername(username);
            return ResponseEntity.status(200).body("ok");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
}
