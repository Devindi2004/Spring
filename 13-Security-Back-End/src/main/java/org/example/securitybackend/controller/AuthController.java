package org.example.securitybackend.controller;

import org.example.securitybackend.dto.APIResponse;
import org.example.securitybackend.dto.AuthDTO;
import org.example.securitybackend.dto.RegisterDTO;
import org.example.securitybackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<APIResponse> registerUser(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(new APIResponse
                (200, "OK", authService.register(registerDTO)));
    }
    @PostMapping("loging")
    public ResponseEntity<APIResponse> loginUser(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(new APIResponse
                (200,"OK",authService.register(new RegisterDTO())));
    }
}
