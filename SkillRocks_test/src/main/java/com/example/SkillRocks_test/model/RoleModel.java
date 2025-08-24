package com.example.SkillRocks_test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class RoleModel {

    @JsonProperty(value = "role_id")
    @Schema(name = "role_id", description = "Идентификатор роли для пользователей.")
    private UUID id;

    @NotBlank(message = "Поле Название роли не может быть пустым.")
    @JsonProperty(value = "role_name")
    @Schema(name = "role_name", description = "Название роли. Не может быть пустым.")
    private String roleName;
}
