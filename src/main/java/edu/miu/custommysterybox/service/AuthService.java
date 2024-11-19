package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterCustomerRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.model.Manager;

import java.util.Optional;

public interface AuthService {

    Optional<Manager> createManager(Manager manager);
    Optional<RegisterResponseDto> registerCustomer(RegisterCustomerRequestDto registerCustomerRequestDto);

    Optional<LoginResponseDto> login(LoginRequestDto loginRequestDTO);
}
