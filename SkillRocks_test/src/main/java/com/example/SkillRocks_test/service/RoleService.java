package com.example.SkillRocks_test.service;

import com.example.SkillRocks_test.dto.RoleDto;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface RoleService {
    Optional<RoleDto> create (RoleDto dto);
    Stream<RoleDto> findAll();
    Optional<RoleDto> update (RoleDto dto);
    Optional<RoleDto> findById(UUID id);
    void delete (UUID id);
}
