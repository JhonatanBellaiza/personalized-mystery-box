package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.model.Customer;

import java.util.Optional;

public interface AuthService {

    Optional<RegisterResponseDto> registerCustomer(RegisterRequestDto registerRequestDto);

    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDTO);
}
