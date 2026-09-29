# Práctica Guiada: Persistencia y Relaciones con Spring Data JPA

**Universidad Americana (UAM)**  
**Facultad de Ingeniería y Arquitectura**  
**Asignatura:** Servicios Web  
**Estudiante:** Javier Espinoza  
**Proyecto:** `gestion-productos`  

---

## 📌 Descripción del Proyecto
API RESTful construida con **Spring Boot**, **Spring Data JPA**, **PostgreSQL** y control de migraciones con **Flyway**. El sistema permite administrar un catálogo de productos comerciales clasificados por categorías y vinculados a sus respectivos proveedores, implementando relaciones `@ManyToOne` y garantizando la integridad referencial.

---

## 🚀 Tecnologías Utilizadas
- **Java 21 / 25**
- **Spring Boot 3.4.4**
  - Spring Web (RESTful Controllers & JSON)
  - Spring Data JPA (Hibernate ORM)
  - Spring Boot Starter Validation
- **PostgreSQL 18** (Driver JDBC PostgreSQL)
- **Flyway** (Control de migraciones evolutivas)
- **Maven** (Gestión de dependencias y construcción)

---

## 🗄️ Migraciones de Base de Datos (Flyway)
1. **`V1__crear_tablas.sql`**: Creación inicial de las tablas `categoria` y `producto` con la llave foránea `fk_producto_categoria`.
2. **`V2__agregar_descripcion_producto.sql`**: Adición incremental del campo `descripcion VARCHAR(500)` a la tabla `producto`.
3. **`V3__crear_tabla_proveedor_y_relacion.sql`** *(Reto Final)*: Creación de la tabla `proveedor` y vinculación con `producto` mediante `proveedor_id` y `fk_producto_proveedor`.

---

## 🔗 Endpoints de la API

### Categorías (`/api/categorias`)
- `GET /api/categorias`: Listar todas las categorías.
- `POST /api/categorias`: Registrar una nueva categoría.

### Proveedores (`/api/proveedores`) - Reto Final
- `GET /api/proveedores`: Listar todos los proveedores.
- `POST /api/proveedores`: Registrar un nuevo proveedor.

### Productos (`/api/productos`)
- `GET /api/productos`: Listar productos con sus entidades `categoria` y `proveedor` anidadas.
- `POST /api/productos`: Registrar un producto asignando su categoría y proveedor.

---

## 📄 Documentación Completa y Evidencias
Consulte el archivo [**`DOCUMENTO_ENTREGA.md`**](./DOCUMENTO_ENTREGA.md) para ver:
- Configuración detallada de conexión y `application.properties`.
- Estructura completa de paquetes y código fuente de entidades, repositorios y controladores.
- Diagrama Entidad-Relación (Mermaid).
- Evidencia de migraciones en PostgreSQL (`flyway_schema_history`).
- Pruebas y capturas JSON de las peticiones tipo Postman.
- Respuestas a las 10 preguntas de comprobación de aprendizaje.
- Conclusiones de la práctica.

---

## 🛠️ Cómo Ejecutar el Proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/EveresJJ/Pr-ctica-guiada---Persistencia-y-relaciones-con-Spring-Data-JPA-Javier-Espinoza.git
   ```
2. Asegurar que PostgreSQL esté corriendo en el puerto 5432 con la base de datos `gestion_productos`.
3. Iniciar la aplicación con el Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   (En Windows PowerShell: `.\mvnw.cmd spring-boot:run`)
