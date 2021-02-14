package com.expenseNote.apps.role.repository;

import com.expenseNote.apps.role.model.Role;
import com.expenseNote.apps.role.model.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoleRepositoryTest {

    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    RoleRepository roleRepository;

    Role role;

    @BeforeEach
    void setUp() {
        role = roleRepository.findById(2).orElse(null);
        assertThat(role).isNull();
        logger.info("Test to retrieve Role data from database --> "+ null);
    }

    @Test
    void createRoleObject_ThenSaveToDatabaseTest() {
        Role role = new Role();

        role.setName(RoleType.ROLE_ADMIN);
        logger.info("Created Role object --> "+ role);
        assertThat(role.getId()).isNull();

        role = roleRepository.save(role);
        logger.info("After saving Role Object to database --> "+ role.getName());
        assertThat(role.getId()).isNotNull();

        Collection<Role> getAllRoles = roleRepository.findAll();
        assertThat(getAllRoles.size()).isEqualTo(1);
        logger.info("After getting all Roles from database -> "+ getAllRoles);
    }

    @Test
    void findAllRolesInDatabaseTest() {
        Collection<Role> allRoles = roleRepository.findAll();

        assertThat(allRoles.size()).isNotNull();
        logger.info("Role table is not empty -->"+ allRoles);

        assertThat(allRoles.size()).isEqualTo(1);
        logger.info("All Role in database -->"+ allRoles);
    }

    @Test
    void findRoleByIdTest() {
        Collection<Role> allRoles = roleRepository.findAll();

        assertThat(allRoles.size()).isNotNull();
        logger.info("Role table is not empty -->"+ allRoles);

        role = roleRepository.findById(1).orElse(null);
        logger.info("Test to retrieve Role by id"+ role);
    }

    @Test
    void updateRoleByIdTest() {
        Collection<Role> allRoles = roleRepository.findAll();

        assertThat(allRoles.size()).isNotNull();
        logger.info("Role table is not empty -->"+ allRoles);

        assertThat(role.getId()).isEqualTo(1);
        logger.info("Role with id 1 exists --> "+ role);

        role.setName(RoleType.ROLE_MODERATOR);
        role = roleRepository.save(role);
        assertThat(role.getName()).isEqualTo(RoleType.ROLE_MODERATOR);
        logger.info("After updating role -->"+ role);
    }

    @Test
    void deleteRoleById() {
        Collection<Role> allRoles = roleRepository.findAll();

        assertThat(allRoles.size()).isNotNull();
        logger.info("Role table is not empty -->"+ allRoles);

        assertThat(role.getId()).isEqualTo(1);
        logger.info("Role with id 1 exists --> "+ role);

        roleRepository.deleteById(1);

        role = roleRepository.findById(1).orElse(null);
        assertThat(role).isNull();

        logger.info("After deleting Role from database by id "+ null);
    }
}