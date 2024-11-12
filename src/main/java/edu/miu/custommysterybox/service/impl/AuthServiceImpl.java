package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Role;
import edu.miu.custommysterybox.model.User;
import edu.miu.custommysterybox.repository.AuthRepository;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.repository.ManagerRespository;
import edu.miu.custommysterybox.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Autowired
    private AuthRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ManagerRespository managerRespository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<RegisterResponseDto> registerCustomer(RegisterRequestDto registerRequestDto) {
        if (userRepository.findByUsername(registerRequestDto.username()).isPresent()) {
            return Optional.empty();  // Return empty if username is already taken
        }

        // Map DTO to entity
        Customer customer = new Customer();
        customer.setUsername(registerRequestDto.username());
        customer.setPassword(passwordEncoder.encode(registerRequestDto.password()));
        customer.setRole(Role.CUSTOMER);

        customerRepository.save(customer);
        System.out.println(customer.getUsername());

        return Optional.of(new RegisterResponseDto("Customer registered successfully.", customer.getUsername()));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto loginRequestDTO) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequestDTO.username());
        if (userOptional.isPresent() && passwordEncoder.matches(loginRequestDTO.password(), userOptional.get().getPassword())) {
            return Optional.of(new LoginResponseDto("Login successful" ,userOptional.get().getUsername()));
        }
        return Optional.empty();  // Return empty if login failed
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));

        // Example: Assign roles based on user type
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
