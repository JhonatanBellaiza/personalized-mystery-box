package edu.miu.custommysterybox;

import edu.miu.custommysterybox.model.Manager;
import edu.miu.custommysterybox.model.Role;
import edu.miu.custommysterybox.model.User;
import edu.miu.custommysterybox.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomMysteryBoxApplication {

    private final AuthService authService;
    public static void main(String[] args) {
        SpringApplication.run(CustomMysteryBoxApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            //Load Manager

            Manager manager = new Manager();
            manager.setUsername("admin");
            manager.setPassword("admin");
            authService.createManager(manager);

        };
    }

}
