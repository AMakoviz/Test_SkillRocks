package com.example.SkillRocks_test.util;

import com.example.SkillRocks_test.dto.RoleDto;
import com.example.SkillRocks_test.entity.RoleEntity;
import com.example.SkillRocks_test.model.RoleModel;
import org.springframework.stereotype.Component;


@Component
public class RoleMapper {

    public RoleDto entityToDto(RoleEntity role) {
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }

    public RoleEntity dtoToEntity(RoleDto dto) {
        RoleEntity role = new RoleEntity();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        return role;
    }

    public RoleModel dtoToModel(RoleDto dto) {
        return RoleModel.builder()
                .id(dto.getId())
                .roleName(dto.getRoleName())
                .build();
    }

    public RoleDto modelToDto(RoleModel model) {
        return RoleDto.builder()
                .id(model.getId())
                .roleName(model.getRoleName())
                .build();
    }


}
