package com.expenseNote.apps.user.repository;

import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.model.RoleType;
import com.expenseNote.apps.role.repository.RoleRepository;
import com.expenseNote.apps.user.model.User;
import com.expenseNote.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
//        user = userRepository.findById((long)1).orElse(null);
//        assertThat(user).isNotNull();
//        log.info("Test to retrieve user data from database -> {}", user);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void createUserObject_ThenSaveToDatabase() throws UnsupportedEncodingException, MessagingException {
        Role role = new Role();
        Optional<Role> getRole = roleRepository.findByName(RoleType.ROLE_USER);
//        role.setName(RoleType.ROLE_USER);
//        user.setRole(getRole.orElse(role));
//        role.setName(getRole.orElse(role));
        String encodedPassword = encoder.encode("12345678");
        User user = User.builder().firstName("Victor")
                .lastName("Ihedioha")
                .username("codesign")
                .email("ihedioha.victor@gmail.com")
                .password(encodedPassword)
                .role(getRole.orElse(role))
                .enabled(true)
                .locked(false)
                .build();

        log.info("User object created --> {}", user);
        userRepository.save(user);
        emailConfig.sendVerificationEmail(user);

        assertThat(user.getId()).isNotNull();
        log.info("User object after saving --> {}", user);
    }

    @Test
    void findAllUsersInDatabaseTest() {
        Collection<User> allUsers = userRepository.findAll();
        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty -> {}", allUsers);

        assertThat(allUsers.size()).isEqualTo(1);
        log.info("All Role in database --> {}", allUsers);
    }

    @Test
    void findUserById() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        user = userRepository.findById((long)1).orElse(null);
        log.info("Test to retrieve User by id -> {}", user);
    }

    @Test
    void findUserByEmail() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        user = userRepository.findByEmail("ihedioha.victor@gmail.com").orElse(null);
        if (user != null) {
            log.info("User with email found -> {}", user);
        } else {
            log.info("User with this email not found");
        }
    }

    @Test
    void findUserByUsername() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        user = userRepository.findByUsername("codesign").orElse(null);
        if (user != null) {
            log.info("User with username found -> {}", user);
        } else {
            log.info("User with this username not found");
        }
    }

    @Test
    void existsByUsername() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        boolean checkUsername = userRepository.existsByUsername("codesign");
        assertThat(checkUsername).isTrue();
        log.info("User with username exists -> {}", true);
    }

    @Test
    void existsByEmail() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        boolean checkEmail = userRepository.existsByEmail("ihedioha.victor@gmail.com");
        assertThat(checkEmail).isTrue();
        log.info("User with email exists -> {}", true);
    }

    @Test
    void updateUserById() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        user = userRepository.findById((long)1).orElse(null);
        assertThat(user).isNotNull();
        log.info("User with Id (1) exists --> {}", user);

        user.setFirstName("Ndubuisi");
        user = userRepository.save(user);
        assertThat(user.getFirstName()).isEqualTo("Ndubuisi");
        log.info("After updating user --> {}", user);
    }

    @Test
    void deleteUserByIdTest() {
        Collection<User> allUsers = userRepository.findAll();

        assertThat(allUsers.size()).isNotNull();
        log.info("User table is not empty --> {}", allUsers);

        user = userRepository.findById((long)1).orElse(null);
        assertThat(user).isNotNull();
        log.info("User with Id (1) exists --> {}", user);

        Long userId = user.getId();
        userRepository.deleteById(userId);

        user = userRepository.findById((long)1).orElse(null);
        assertThat(user).isNull();
        log.info("After deleting User from database by id "+ null);
    }
}