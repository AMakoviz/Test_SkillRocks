package com.example.SkillRocks_test.util;

import com.example.SkillRocks_test.dto.UserDto;
import com.example.SkillRocks_test.entity.UserEntity;
import com.example.SkillRocks_test.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    RoleMapper roleMapper;
    UserMapper() {
        roleMapper = new RoleMapper();
    }

    public UserEntity dtoToEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setFio(dto.getFio());
        if (dto.getAvatar() != null) {
            userEntity.setAvatar(dto.getAvatar());
        }
        if (dto.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getRole() != null) {
            userEntity.setRole(roleMapper.dtoToEntity(dto.getRole()));
        }
        return userEntity;
    }

    public UserDto entityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .fio(userEntity.getFio())
                .avatar(
                        userEntity.getAvatar() != null ? userEntity.getAvatar() : null
                )
                .phoneNumber(
                        userEntity.getPhoneNumber() != null ? userEntity.getPhoneNumber() : null
                )
                .role(
                        userEntity.getRole() != null ? roleMapper.entityToDto(userEntity.getRole()) : null
                )
                .build();
    }

    public UserDto modelToDto(UserModel model) {
        return UserDto.builder()
                .id(model.getId())
                .fio(model.getFio())
                .avatar(
                        model.getAvatar() != null ? model.getAvatar() : null
                )
                .phoneNumber(
                        model.getPhoneNumber() != null ? model.getPhoneNumber() : null
                )
                .role(
                        model.getRole() != null ? roleMapper.modelToDto(model.getRole()) : null
                )
                .build();
    }

    public UserModel dtoToModel(UserDto dto) {
        return UserModel.builder()
                .id(dto.getId())
                .fio(dto.getFio())
                .avatar(
                        dto.getAvatar() != null ? dto.getAvatar() : null
                )
                .phoneNumber(
                        dto.getPhoneNumber() != null ? dto.getPhoneNumber() : null
                )
                .role(
                        dto.getRole() != null ? roleMapper.dtoToModel(dto.getRole()) : null
                )
                .build();
    }

}
