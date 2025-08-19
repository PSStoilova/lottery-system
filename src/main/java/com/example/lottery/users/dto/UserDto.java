package com.example.lottery.users.dto;

import com.example.lottery.users.data.User;

public record UserDto(
        String username,
        String name,
        String email,
        String apikey
) {
    public User toEntity() {
        return new User(this.name, this.username, this.email, this.apikey);
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getUsername(), user.getName(), user.getEmail(), user.getApikey());
    }
}
