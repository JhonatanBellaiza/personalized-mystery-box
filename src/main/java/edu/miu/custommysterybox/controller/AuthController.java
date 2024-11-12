package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDTO) {
        // Call service to login and return Optional response
        return authService.login(loginRequestDTO)
                .map(ResponseEntity::ok)  // If login is successful, return 200 OK with response
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)  // If login fails, return 401 Unauthorized
                        .body(new LoginResponseDto("Invalid username or password", null)));
    }

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerCustomerDTO) {
        // Call service to register and return Optional response
        return authService.registerCustomer(registerCustomerDTO)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))  // If registration is successful, return 201 Created
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)  // If username is taken, return 400 Bad Request
                        .body(new RegisterResponseDto("Username is already taken.", null)));
    }
}
