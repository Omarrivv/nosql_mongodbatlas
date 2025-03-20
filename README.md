# Spring WebFlux MongoDB Atlas CRUD

Este proyecto es una API REST reactiva construida con Spring WebFlux y MongoDB Atlas, implementando operaciones CRUD para la gestión de clientes.

## Tecnologías Utilizadas

- Java 11
- Spring Boot 2.7.18
- Spring WebFlux
- MongoDB Atlas
- Maven
- Lombok
- Reactor Core

## Prerrequisitos

- JDK 11
- Maven 3.8+
- MongoDB Atlas Account
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## Configuración

1. Clonar el repositorio:
```bash
git clone https://github.com/Omarrivv/nosql_mongodbatlas.git
cd nosql_mongodbatlas
```

2. Configurar MongoDB Atlas:
   - Crear una cuenta en [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
   - Crear un nuevo cluster
   - Obtener la URI de conexión
   - Configurar el archivo `application.yml` con tus credenciales

3. Configurar `application.yml`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@<cluster-url>/<database>
      database: nosql
```

## Estructura del Proyecto

```
src/main/java/pe/edu/vallegrande/app/
├── model
│   └── Customer.java
├── repository
│   └── CustomerRepository.java
├── service
│   ├── CustomerService.java
│   └── impl/CustomerServiceImpl.java
├── rest
│   └── CustomerRest.java
└── MsSpringWebfluxNosqlApplication.java
```

## Modelo de Datos

```java
public class Customer {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String state;
}
```

## API Endpoints

### 1. Obtener todos los clientes
```http
GET /api/customers
```

### 2. Obtener cliente por ID
```http
GET /api/customers/{id}
```

### 3. Crear nuevo cliente
```http
POST /api/customers
Content-Type: application/json

{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com",
    "telefono": "123456789",
    "direccion": "Av. Principal 123"
}
```

### 4. Actualizar cliente
```http
PUT /api/customers/{id}
Content-Type: application/json

{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com",
    "telefono": "123456789",
    "direccion": "Nueva Dirección"
}
```

### 5. Eliminar cliente
```http
DELETE /api/customers/{id}
```

## Ejemplos de Uso con cURL

1. Crear Cliente:
```bash
curl -X POST http://localhost:8081/api/customers \
-H "Content-Type: application/json" \
-d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com",
    "telefono": "123456789",
    "direccion": "Av. Principal 123"
}'
```

2. Obtener Todos los Clientes:
```bash
curl http://localhost:8081/api/customers
```

3. Obtener Cliente por ID:
```bash
curl http://localhost:8081/api/customers/{id}
```

4. Actualizar Cliente:
```bash
curl -X PUT http://localhost:8081/api/customers/{id} \
-H "Content-Type: application/json" \
-d '{
    "nombre": "Juan",
    "apellido": "Pérez Actualizado",
    "email": "juan.updated@example.com",
    "telefono": "987654321",
    "direccion": "Nueva Dirección"
}'
```

5. Eliminar Cliente:
```bash
curl -X DELETE http://localhost:8081/api/customers/{id}
```

## Ejecución

1. Compilar el proyecto:
```bash
mvn clean install
```

2. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8081`

## Características

- Operaciones CRUD reactivas
- Manejo de errores global
- Logging detallado
- Documentación de API
- Cross-Origin Resource Sharing (CORS) habilitado
- Validación de datos
- Respuestas HTTP apropiadas

## Contribuir

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Autor

Omar Rivera - [GitHub](https://github.com/Omarrivv)

## Comandos Git

```bash
echo "# nosql_mongodbatlas" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/Omarrivv/nosql_mongodbatlas.git
git push -u origin main
```# nosql_mongodbatlas
