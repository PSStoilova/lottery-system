package com.example.lottery.users;

import com.example.lottery.users.data.User;
import com.example.lottery.users.data.UserRepository;
import com.example.lottery.users.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    private static final User USER = new User("petya", "pet", "petya@gmail.com", "123");

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        when(userRepository.save(any())).thenReturn(USER);
        UserDto userDto = userService.addUser(new UserDto("petya", "pet", "petya@gmail.com", "123"));

        assertEquals(USER.getEmail(), userDto.email());
        assertEquals(USER.getUsername(), userDto.username());
        assertEquals(USER.getApikey(), userDto.apikey());
        assertEquals(USER.getName(), userDto.name());
    }

    @Test
    public void testFetchAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(USER));
        List<UserDto> userDtos = userService.fetchAllUsers();

        assertEquals(1, userDtos.size());
        assertEquals(USER.getEmail(), userDtos.getFirst().email());
        assertEquals(USER.getUsername(), userDtos.getFirst().username());
        assertEquals(USER.getApikey(), userDtos.getFirst().apikey());
        assertEquals(USER.getName(), userDtos.getFirst().name());
    }
}
