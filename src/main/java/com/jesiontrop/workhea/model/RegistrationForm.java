package com.jesiontrop.workhea.model;

import com.jesiontrop.workhea.security.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,
                        passwordEncoder.encode(password),
                        email);
    }
}
