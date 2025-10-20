package com.example.SkillRocks_test.repository;

import com.example.SkillRocks_test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserEntityById(UUID id);

    List<UserEntity> findByFio(String username);
}
