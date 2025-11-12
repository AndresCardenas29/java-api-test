# API REST de Usuarios

## 🏗️ Arquitectura

Este proyecto sigue una arquitectura profesional en capas:

```
com.nek.api/
├── controller/         # Capa de presentación (REST endpoints)
├── service/           # Capa de lógica de negocio
│   └── impl/         # Implementaciones de servicios
├── repository/        # Capa de acceso a datos (JPA)
├── entity/           # Entidades JPA (modelo de datos)
├── dto/              # Data Transfer Objects
└── exception/        # Manejo de excepciones global
```

## 📋 Endpoints Disponibles

### Crear Usuario
```http
POST /api/users
Content-Type: application/json

{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan.perez@example.com",
  "phone": "1234567890"
}
```

### Obtener Usuario por ID
```http
GET /api/users/{id}
```

### Obtener Todos los Usuarios
```http
GET /api/users
```

### Obtener Solo Usuarios Activos
```http
GET /api/users?active=true
```

### Buscar Usuarios por Nombre/Apellido
```http
GET /api/users?search=juan
```

### Actualizar Usuario
```http
PUT /api/users/{id}
Content-Type: application/json

{
  "firstName": "Juan Carlos",
  "lastName": "Pérez García",
  "email": "juan.perez@example.com",
  "phone": "0987654321"
}
```

### Eliminar Usuario (eliminación física)
```http
DELETE /api/users/{id}
```

### Desactivar Usuario (eliminación lógica)
```http
PATCH /api/users/{id}/deactivate
```

### Activar Usuario
```http
PATCH /api/users/{id}/activate
```

## 🎯 Características

### ✅ Validaciones
- Nombre y apellido: 2-50 caracteres, obligatorios
- Email: formato válido, único, obligatorio
- Teléfono: máximo 15 caracteres, opcional
- Todas las validaciones con mensajes en español

### 🛡️ Manejo de Errores
- **404 Not Found**: Recurso no encontrado
- **409 Conflict**: Email duplicado
- **400 Bad Request**: Validaciones fallidas
- **500 Internal Server Error**: Errores del servidor

### 📊 Respuestas de Error
```json
{
  "status": 404,
  "message": "Usuario no encontrado con id: 5",
  "timestamp": "2025-11-12T00:20:00"
}
```

```json
{
  "status": 400,
  "errors": {
    "email": "El email debe ser válido",
    "firstName": "El nombre es obligatorio"
  },
  "timestamp": "2025-11-12T00:20:00"
}
```

### 🗄️ Base de Datos
- Base de datos H2 en memoria
- Auditoría automática: `createdAt` y `updatedAt`
- Eliminación lógica mediante campo `active`
- Consola H2 disponible en: http://localhost:8080/h2-console

#### Configuración H2 Console:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User Name**: `sa`
- **Password**: (vacío)

## 🚀 Cómo Usar

### 1. Iniciar la aplicación
```bash
./mvnw spring-boot:run
```

### 2. Probar con cURL

**Crear usuario:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "María",
    "lastName": "González",
    "email": "maria.gonzalez@example.com",
    "phone": "5551234567"
  }'
```

**Obtener todos los usuarios:**
```bash
curl http://localhost:8080/api/users
```

**Obtener usuario por ID:**
```bash
curl http://localhost:8080/api/users/1
```

**Actualizar usuario:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "María José",
    "lastName": "González López",
    "email": "maria.gonzalez@example.com",
    "phone": "5559876543"
  }'
```

**Desactivar usuario:**
```bash
curl -X PATCH http://localhost:8080/api/users/1/deactivate
```

**Eliminar usuario:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

### 3. Probar con Postman o Insomnia
- Importa los endpoints
- Usa `http://localhost:8080` como base URL
- Configura `Content-Type: application/json` en los headers

## 🏗️ Patrones Implementados

1. **Repository Pattern**: Abstracción de acceso a datos
2. **Service Layer**: Lógica de negocio separada
3. **DTO Pattern**: Separación entre entidades y objetos de transferencia
4. **Dependency Injection**: Inyección de dependencias por constructor
5. **Exception Handling**: Manejo global de excepciones con `@RestControllerAdvice`
6. **Soft Delete**: Eliminación lógica mediante flag `active`

## 📦 Tecnologías

- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Java 21**
- **Maven**

## 📝 Notas

- La base de datos H2 es en memoria, los datos se pierden al reiniciar
- Todos los usuarios creados tienen `active=true` por defecto
- Los timestamps (`createdAt`, `updatedAt`) se generan automáticamente
- El email debe ser único en el sistema
