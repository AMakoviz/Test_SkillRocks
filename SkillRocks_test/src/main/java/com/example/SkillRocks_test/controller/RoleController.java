package com.example.SkillRocks_test.controller;

import com.example.SkillRocks_test.model.RoleModel;
import com.example.SkillRocks_test.service.RoleService;
import com.example.SkillRocks_test.util.RoleMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Данные о пользователях", description = "Управление данными о пользователях")
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Получение списка всех возможных ролей", description = "Возвращает список всех доступных пользователям ролей")
    public ResponseEntity<List<RoleModel>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll()
                .map(roleMapper::dtoToModel)
                .toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Добавление новой роли", description = "Добавляет новую роль в бд.")
    public ResponseEntity<RoleModel> createNewRole(@Valid @RequestBody RoleModel roleModel) {
        return roleService.create(roleMapper.modelToDto(roleModel))
                .map(roleMapper::dtoToModel)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Обновление данных о роли.", description = "Обновляет данные о роли, возвращает обновленные данные. Если роль с указанным id не существует, будет возвращена ошибка 404")
    public ResponseEntity<RoleModel> updateRole(@Valid @RequestBody RoleModel roleModel) {
        return roleService.update(roleMapper.modelToDto(roleModel))
                .map(roleMapper::dtoToModel)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Удаляет роль.", description = "Удаляет роль по переданному id. Если роли с таким id не существует, будет возвращена ошибка 404. Все пользователи для удаляемой роли также удаляются. ")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        roleService.delete(uuid);
        return ResponseEntity.noContent().build();
    }






}
