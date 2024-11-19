package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.config.JwtUtil;
import edu.miu.custommysterybox.dto.request.LoginRequestDto;
import edu.miu.custommysterybox.dto.request.RegisterCustomerRequestDto;
import edu.miu.custommysterybox.dto.response.LoginResponseDto;
import edu.miu.custommysterybox.dto.response.RegisterResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Manager;
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

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Optional<Manager> createManager(Manager manager) {
        Manager newManager = new Manager();
        newManager.setUsername(manager.getUsername());
        newManager.setPassword((passwordEncoder.encode(manager.getPassword())));
        newManager.setRole(Role.MANAGER);

        return Optional.of(managerRespository.save(newManager));
    }

    @Override
    public Optional<RegisterResponseDto> registerCustomer(RegisterCustomerRequestDto registerCustomerRequestDto) {
        if (userRepository.findByUsername(registerCustomerRequestDto.username()).isPresent()) {
            return Optional.empty();  // Return empty if username is already taken
        }

        // Map DTO to entity
        Customer customer = new Customer();
        customer.setUsername(registerCustomerRequestDto.username());
        customer.setPassword(passwordEncoder.encode(registerCustomerRequestDto.password()));
        customer.setRole(Role.CUSTOMER);
        customer.setFavoriteColors(registerCustomerRequestDto.favoriteColors());
        customer.setDislikedColors(registerCustomerRequestDto.dislikedColors());
        customer.setTopSize(registerCustomerRequestDto.topSize());
        customer.setBottomSize(registerCustomerRequestDto.bottomSize());
        customer.setStylePreferences(registerCustomerRequestDto.stylePreferences());
        customer.setSubscriptionType(registerCustomerRequestDto.subscriptionType());

        customerRepository.save(customer);
        System.out.println(customer.getUsername());

        return Optional.of(new RegisterResponseDto("Customer registered successfully.", customer.getUsername()));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto loginRequestDTO) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequestDTO.username());

        // Check if user exists and password matches
        if (userOptional.isPresent() && passwordEncoder.matches(loginRequestDTO.password(), userOptional.get().getPassword())) {
            User user = userOptional.get();

            // Generate JWT token using JwtUtil
            String token = jwtUtil.generateToken(user.getUsername());

            // Return message and token in LoginResponseDto
            return Optional.of(new LoginResponseDto("Login successful", token));
        }

        // Return empty if login failed
        return Optional.of(new LoginResponseDto("Login failed: Invalid username or password", null));
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
