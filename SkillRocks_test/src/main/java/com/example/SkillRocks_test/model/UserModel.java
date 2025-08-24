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
@Schema(name = "Данные о пользователе")
public class UserModel {

    @JsonProperty(value = "user_id")
    @Schema(name = "book_id", description = "Идентификатор пользователя.")
    private UUID id;

    @NotBlank(message = "Поле ФИО не может быть пустым")
    @Schema(name = "fio", description = "ФИО пользователя. Не может быть пустым.")
    @JsonProperty(value = "fio")
    private String fio;

    @Schema(name = "phone_number", description = "Номер телефона пользователя.")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @Schema(name = "avatar", description = "Ссылка на аватар пользователя.")
    @JsonProperty(value = "avatar")
    private String avatar;

    @JsonProperty(value = "role")
    @Schema(name = "role", description = "Информация о роли пользователя. Может содержать только id, если пользователю добавлена роль")
    private RoleModel role;
}
