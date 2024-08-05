package test;

import data.repositories.UserRepository;
import domain.entities.Role;
import domain.entities.User;
import domain.utils.AuditService;
import service.UserService;
import service.impl.UserServiceImpl;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(new UserRepository(), new AuditService());
    }

    @Test
    public void testAddUser() {
        User user = new User("Ishen", "Ishen", "Ishen", Role.CLIENT);
        userService.addUser(user);
        assertNotNull(userService.getUser("Ishen"));
    }

    @Test
    public void testUpdateUser() {
        User user = new User("Ishen", "Ishen", "Ishen", Role.CLIENT);
        userService.addUser(user);
        user.setName("Ishen Updated");
        userService.updateUser(user);
        assertEquals("Ishen Updated", userService.getUser("Ishen").getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("Ishen", "Ishen", "Ishen", Role.CLIENT);
        userService.addUser(user);
        userService.deleteUser(user.getId());
        assertNull(userService.getUser("Ishen"));
    }
}