package com.groupeisi.commandeservice.service;
import com.groupeisi.commandeservice.dto.AuthDTO; import com.groupeisi.commandeservice.entities.UserAccount;
import com.groupeisi.commandeservice.repository.UserRepository; import com.groupeisi.commandeservice.security.JwtService;
import lombok.RequiredArgsConstructor; import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository; private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; private final AuthenticationManager authenticationManager;
    public AuthDTO.LoginResponse register(AuthDTO.RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) throw new IllegalArgumentException("Email déjà utilisé");
        UserAccount u = new UserAccount(); u.setEmail(req.getEmail()); u.setPassword(passwordEncoder.encode(req.getPassword())); u.setRole(UserAccount.Role.valueOf(req.getRole().toUpperCase()));
        UserAccount saved = userRepository.save(u);
        User ud = (User) User.builder().username(saved.getEmail()).password(saved.getPassword()).roles(saved.getRole().name()).build();
        return new AuthDTO.LoginResponse(jwtService.generateToken(ud), "Bearer", saved.getId(), saved.getEmail(), saved.getRole().name());
    }
    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        UserAccount u = userRepository.findByEmail(req.getEmail()).orElseThrow();
        User ud = (User) User.builder().username(u.getEmail()).password(u.getPassword()).roles(u.getRole().name()).build();
        return new AuthDTO.LoginResponse(jwtService.generateToken(ud), "Bearer", u.getId(), u.getEmail(), u.getRole().name());
    }
}
