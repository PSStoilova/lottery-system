package com.example.lottery.users;

import com.example.lottery.users.data.User;
import com.example.lottery.users.data.UserRepository;
import com.example.lottery.users.dto.UserDto;
import com.example.lottery.users.exceptions.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto) {
        User user = userDto.toEntity();
        try {
            User createdUser = userRepository.save(user);
            return UserDto.fromEntity(createdUser);
        } catch (Exception e) {
            throw new UserException("Failed to save user " + e.getMessage());
        }
    }

    public List<UserDto> fetchAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDto::fromEntity)
                .toList();
    }
}
