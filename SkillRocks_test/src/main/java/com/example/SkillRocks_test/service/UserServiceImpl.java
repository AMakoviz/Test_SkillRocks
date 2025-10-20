package com.example.SkillRocks_test.service;

import com.example.SkillRocks_test.dto.UserDto;
import com.example.SkillRocks_test.entity.UserEntity;
import com.example.SkillRocks_test.repository.UserRepository;
import com.example.SkillRocks_test.util.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<UserDto> create (UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(userMapper::dtoToEntity)
                .map(userRepository::save)
                .map(userMapper::entityToDto);
    }

    @Override
    public Optional<UserDto> findById(UUID id) {
        return Optional.ofNullable(
                userRepository.findById(id)
                        .map(userMapper::entityToDto)
                        .orElseThrow(() -> new EntityNotFoundException("Пользователь с " + id + " не найден"))
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<UserDto> update(UserDto userDto) {
        findById(userDto.getId());
        return Optional.ofNullable(userDto)
                .map(userMapper::dtoToEntity)
                .map(userRepository::save)
                .map(userMapper::entityToDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete (UUID id) {
        findById(id);
        if (checkId(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDto> findByFio (String fio) {
        List<UserEntity> list = userRepository.findByFio(fio);
        return list.stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }

    private boolean checkId(UUID id) {
        return id != null;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDto> findByFio (String fio) {
        List<UserEntity> list = userRepository.findByFio(fio);
        return list.stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }
}
