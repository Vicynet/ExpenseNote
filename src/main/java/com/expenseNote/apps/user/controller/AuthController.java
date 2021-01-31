package com.expenseNote.apps.user.controller;

import com.expenseNote.config.EmailConfig;
import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.model.RoleType;
import com.expenseNote.apps.user.model.User;
import com.expenseNote.payload.request.LoginRequest;
import com.expenseNote.payload.request.SignupRequest;
import com.expenseNote.payload.response.JwtResponse;
import com.expenseNote.payload.response.MessageResponse;
import com.expenseNote.apps.role.repository.RoleRepository;
import com.expenseNote.apps.user.repository.UserRepository;
import com.expenseNote.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final EmailConfig emailConfig;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userService = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userService.getId(),
                userService.getFirstName(),
                userService.getLastName(),
                userService.getUsername(),
                userService.getEmail(),
                userService.getRole().getName().name()
        ));

    }

    @PostMapping("/signup/user")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) throws UnsupportedEncodingException, MessagingException {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getRole()
        );

        Role role = new Role();

        Optional<Role> getRole = roleRepository.findByName(RoleType.ROLE_USER);
        user.setRole(getRole.orElse(role));
        userRepository.save(user);
        emailConfig.sendVerificationEmail(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@Validated @RequestBody SignupRequest signupRequest) throws UnsupportedEncodingException, MessagingException {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getRole()
        );

        Role role = new Role();

        Optional<Role> getRole = roleRepository.findByName(RoleType.ROLE_ADMIN);
        getRole.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(getRole.orElse(role));
        userRepository.save(user);
        emailConfig.sendVerificationEmail(user);

        return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
    }

}
