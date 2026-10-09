
package com.EduFest.config;

import com.EduFest.entity.User;
import com.EduFest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RoleUserSetup {

    @Bean
    public CommandLineRunner setupRoleUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            createUser(userRepository, passwordEncoder,
                    "faculty@edufest.local",
                    "Faculty User", "Faculty@123", "FACULTY");

            createUser(userRepository, passwordEncoder,
                    "student@edufest.local",
                    "Student User", "Student@123", "STUDENT");

            System.out.println("Faculty and Student accounts are ready.");
        };
    }

    private void createUser(
            UserRepository repository,
            PasswordEncoder encoder,
            String email,
            String name,
            String password,
            String role) {

        User user = repository.findByEmail(email)
                .orElseGet(User::new);

        user.setEmail(email);
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setRole(role);

        repository.save(user);
    }
}
