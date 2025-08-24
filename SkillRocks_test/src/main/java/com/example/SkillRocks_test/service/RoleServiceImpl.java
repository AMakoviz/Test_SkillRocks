package com.example.SkillRocks_test.service;

import com.example.SkillRocks_test.dto.RoleDto;
import com.example.SkillRocks_test.repository.RoleRepository;
import com.example.SkillRocks_test.util.RoleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<RoleDto> create (RoleDto dto) {
        return Optional.ofNullable(dto)
                .map(roleMapper::dtoToEntity)
                .map(roleRepository::save)
                .map(roleMapper::entityToDto);
    }

    @Override
    public Stream<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::entityToDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<RoleDto> update (RoleDto dto) {
        findById(dto.getId());
        return Optional.ofNullable(dto)
                .map(roleMapper::dtoToEntity)
                .map(roleRepository::save)
                .map(roleMapper::entityToDto);
    }

    @Override
    public Optional<RoleDto> findById(UUID id) {
        return Optional.ofNullable(
                roleRepository.findById(id)
                        .map(roleMapper::entityToDto)
                        .orElseThrow(() -> new EntityNotFoundException("Роль с " + id + " не найдена"))
        );
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete (UUID id) {
        findById(id);
        roleRepository.deleteById(id);
    }









}
