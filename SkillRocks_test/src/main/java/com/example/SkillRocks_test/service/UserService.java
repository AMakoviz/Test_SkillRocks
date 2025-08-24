package com.example.SkillRocks_test.service;
import com.example.SkillRocks_test.dto.UserDto;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<UserDto> create (UserDto userDto);
    Optional<UserDto> findById(UUID id);
    Optional<UserDto> update(UserDto userDto);
    void delete (UUID id);
}
