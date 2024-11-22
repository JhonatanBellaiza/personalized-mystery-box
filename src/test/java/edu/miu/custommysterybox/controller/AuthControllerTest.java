package edu.miu.custommysterybox.controller;

import com.google.gson.Gson;
import edu.miu.custommysterybox.config.JwtAuthenticationFilter;
import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterCustomerRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Test
    void loginSuccessTest() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("Jhonatan", "1234");
        LoginResponseDto loginResponseDto = new LoginResponseDto("Success", "Token");

        Mockito.when(authService.login(loginRequestDto)).thenReturn(Optional.of(loginResponseDto));
        ResponseEntity<LoginResponseDto> responseEntity = authController.login(loginRequestDto);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        Assertions.assertEquals(responseEntity.getBody(), loginResponseDto);

    }

    @Test
    void loginFailureTest() throws Exception {

    }

//    @Test
//    void registerSuccessTest() throws Exception {
//        // Arrange
//        RegisterCustomerRequestDto registerRequest = new RegisterCustomerRequestDto("Jhonatan", "password123", "jhonatan@example.com");
//        RegisterResponseDto registerResponse = new RegisterResponseDto("Registration successful", "customerId123");
//
//        Mockito.when(authService.registerCustomer(registerRequest)).thenReturn(Optional.of(registerResponse));
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(registerRequest)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().json(gson.toJson(registerResponse)));
//    }
//
//    @Test
//    void registerFailureTest() throws Exception {
//        // Arrange
//        RegisterCustomerRequestDto registerRequest = new RegisterCustomerRequestDto("Jhonatan", "password123", "jhonatan@example.com");
//
//        Mockito.when(authService.registerCustomer(registerRequest)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(registerRequest)))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Username is already taken."));
//    }
}
