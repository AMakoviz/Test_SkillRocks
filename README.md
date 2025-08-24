# SkillRocks_test — REST API для управления пользователями и ролями

Учебный проект по ТЗ:

- CRUD по пользователю: создать, получить, обновить, удалить.
- Данные пользователя: **ФИО**, **телефон**, **аватар (URL)**, **роль**.
- База данных: **PostgreSQL**.
- Технологии: **Java 17**, **Spring Boot 3**, **Spring Data JPA**, **Hibernate Validator**, **springdoc-openapi**.

> Дополнительно реализованы CRUD по ролям (удобно для наполнения справочника).

---
Swagger UI:  
`http://localhost:10152/swagger-ui/index.html`

---

## Архитектура и модель данных

### Таблицы

**roles**
- `role_id` UUID PK (генерируется)
- `role_name` VARCHAR(150) NOT NULL

**users**
- `user_id` UUID PK (генерируется)
- `fio` VARCHAR(150) NOT NULL
- `phone_number` VARCHAR(15)
- `avatar` TEXT
- `role_id` UUID FK → roles(role_id)  
  (удаление роли приводит к удалению пользователей с этой ролью — см. ниже)

> В `UserEntity.role` стоит `@ManyToOne` + `@OnDelete(CASCADE)`.  
> Для полного соответствия на уровне БД рекомендуется FK `ON DELETE CASCADE`.  

### Слои
- **Entity**: `UserEntity`, `RoleEntity`
- **DTO**: `UserDto`, `RoleDto`
- **API-модели (I/O)**: `UserModel`, `RoleModel`
- **Mapper**: `UserMapper`, `RoleMapper`
- **Repository**: `UserRepository`, `RoleRepository`
- **Service**: `UserService(+Impl)`, `RoleService(+Impl)`
- **Controller**: `UserController`, `RoleController`
- **Ошибки**: `ValidationExceptionHandler` (`@RestControllerAdvice`)

---

## REST API

Базовый префикс: `/api`  
Content-Type: `application/json`

### Пользователи

#### 1) Создать пользователя
```
POST /api/createNewUser
```
Пример запроса:
```json
{
  "fio": "Иванов Иван",
  "phone_number": "+79991234567",
  "avatar": "https://cdn.example.com/ava/ivanov.png",
  "role": { "role_id": "9f0d2d5e-0e17-4f3a-8a09-dbc5d0c9b9d1" }
}
```
Пример ответа `200 OK`:
```json
{
  "user_id": "82e7d5a1-4a2d-45e3-b62e-158c6c0baf52",
  "fio": "Иванов Иван",
  "phone_number": "+79991234567",
  "avatar": "https://cdn.example.com/ava/ivanov.png",
  "role": {
    "role_id": "9f0d2d5e-0e17-4f3a-8a09-dbc5d0c9b9d1",
    "role_name": "USER"
  }
}
```

#### 2) Получить пользователя по ID
```
GET /api/users?id={UUID}
```

#### 3) Обновить пользователя
```
PUT /api/userDetailsUpdate
```

#### 4) Удалить пользователя по ID
```
DELETE /api/users?id={UUID}
```

### Роли

#### Получить все роли
```
GET /api/role
```

#### Создать роль
```
POST /api/role
```

#### Обновить роль
```
PUT /api/role
```

#### Удалить роль
```
DELETE /api/role/{id}
```

---

## Валидация и обработка ошибок

### Валидация
- `UserModel.fio` — `@NotBlank`
- `RoleModel.roleName` — `@NotBlank`

### Ошибки (`ValidationExceptionHandler`)
- `400 Bad Request` — ошибки валидации
- `404 Not Found` — сущность не найдена
- `409 Conflict` — конфликт состояния
- `500 Internal Server Error` — общие и ошибки БД

---

## Соответствие ТЗ

- CRUD по пользователю — ✅  
- Роли — реализованы дополнительно  
- Обработка ошибок — ✅  
- Swagger UI — ✅  

---

## Лицензия
Проект для учебных целей.

## Автор 
Маковиз Анастасия

