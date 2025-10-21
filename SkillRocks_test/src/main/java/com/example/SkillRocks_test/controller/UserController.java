package com.example.SkillRocks_test.controller;

import com.example.SkillRocks_test.model.UserModel;
import com.example.SkillRocks_test.service.UserService;
import com.example.SkillRocks_test.util.UserMapper;
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
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/users", produces = "application/json")
    @Operation(summary = "Получение пользователя по Id", description = "Возвращает информацию о пользователе и его роли по переданному идентификатору. В случае, если пользователя с таким идентификатором не существует, будет возвращена ошибка 404")
    public ResponseEntity<UserModel> getUsers(@RequestParam String id) {
        UUID uuid = UUID.fromString(id);
        return userService.findById(uuid)
                .map(userMapper::dtoToModel)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping(path = "/users", produces = "application/json")
    @Operation(summary = "Получение пользователя по ФИО", description = "Возвращает информацию о пользователе и его роли по переданному ФИО. В случае, если пользователя с таким идентификатором не существует, будет возвращена ошибка 404")
    public List<UserModel> getUsersByFio(@RequestParam String fio) {
        return userService.findByFio(fio).stream()
                .map(userMapper::dtoToModel)
                .toList();
    }

    @PostMapping(path = "/createNewUser", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Добавление нового пользователя", description = "Добавляет нового пользователя в бд. Вместе с данными о пользователе можно указать его роль (если такая уже существует в бд). Для этого необходимо передать id роли.")
    public ResponseEntity<UserModel> createNewUser(@Valid @RequestBody UserModel userModel) {
        return userService.create(userMapper.modelToDto(userModel))
                .map(userMapper::dtoToModel)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PutMapping(path="/userDetailsUpdate", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Обновление данных о пользователе.", description = "Обновляет данные о пользователе, возвращает обновленные данные. Если пользователя с указанным id не существует, будет возвращена ошибка 404")
    public ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserModel userModel) {
        return userService.update(userMapper.modelToDto(userModel))
                .map(userMapper::dtoToModel)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @DeleteMapping(path = "/users")
    @Operation(summary = "Удаляет пользователя.", description = "Удаляет пользователя по переданному id. Если пользователя с таким id не существует, будет возвращена ошибка 404")
    public ResponseEntity<Void> deleteUser(@RequestParam String id) {
        UUID uuid = UUID.fromString(id);
        userService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}
