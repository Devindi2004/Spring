package org.example.securitybackend.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.example.securitybackend.dto.AuthDTO;
import org.example.securitybackend.dto.AuthResponseDTO;
import org.example.securitybackend.dto.RegisterDTO;
import org.example.securitybackend.entity.Role;
import org.example.securitybackend.entity.User;
import org.example.securitybackend.repository.UserRepository;
import org.example.securitybackend.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        User user = userRepository.findByUsername(authDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(authDTO.getUsername()));
        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(authDTO.getUsername());
        }
        String token = jwtUtil.generateToken(authDTO.getUsername());
        return new AuthResponseDTO(token);

    }
    public String register(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }
        User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }
}