# Práctica Guiada # 2 / Laboratorio #1: Persistencia y Relaciones con Spring Data JPA

**Universidad Americana (UAM)**  
**Facultad de Ingeniería y Arquitectura**  
**Asignatura:** Servicios Web  
**Estudiante:** Javier Espinoza  
**Proyecto:** `gestion-productos`  

---

## 📌 Descripción del Proyecto
Evolución de la API RESTful empresarial construida con **Spring Boot**, **Spring Data JPA**, **PostgreSQL** y control de migraciones con **Flyway**. Incorpora una arquitectura en capas desacoplada (**Controller → Service → Repository → PostgreSQL**), uso de **DTOs con Bean Validation**, un **CRUD completo de Productos**, consultas por relación y una relación **Muchos a Muchos (Many-to-Many)** entre `Producto` y `Etiqueta`.

---

## 🚀 Arquitectura y Tecnologías
- **Java 21 / 25**
- **Spring Boot 3.4.4**
  - **Controller:** Endpoints REST con `ResponseEntity` y manejo de códigos HTTP semánticos.
  - **Service:** Capa de lógica de negocio transaccional (`@Transactional`) y validaciones de entidades.
  - **DTO:** `ProductoRequestDTO` para transferir datos de forma segura desacoplando el modelo interno.
  - **Repository:** `JpaRepository` con consultas derivadas (`findByCategoriaId`, `findByEtiquetasId`).
  - **Entity:** `Categoria`, `Producto`, `Proveedor`, `Etiqueta` con mapeos `@ManyToOne`, `@OneToMany` y `@ManyToMany`.
- **PostgreSQL 18** (Driver JDBC PostgreSQL)
- **Flyway** (Control de migraciones evolutivas V1, V2, V3 y V4)
- **Maven** (Maven Wrapper `./mvnw`)

---

## 🗄️ Migraciones de Base de Datos (Flyway)
1. **`V1__crear_tablas.sql`**: Creación inicial de las tablas `categoria` y `producto`.
2. **`V2__agregar_descripcion_producto.sql`**: Adición del campo `descripcion VARCHAR(500)` a la tabla `producto`.
3. **`V3__crear_tabla_proveedor_y_relacion.sql`**: Creación de la tabla `proveedor` y clave foránea `proveedor_id` en `producto`.
4. **`V4__crear_etiquetas.sql`**: Creación de la tabla `etiqueta` y tabla intermedia de unión `producto_etiqueta` para la relación N:M.

---

## 🔗 Endpoints Principales de la API

### Productos (`/api/productos`)
- `GET /api/productos`: Listar todos los productos.
- `GET /api/productos/{id}`: Obtener producto por ID.
- `POST /api/productos`: Crear producto utilizando `ProductoRequestDTO` (201 Created).
- `PUT /api/productos/{id}`: Actualizar producto completo.
- `DELETE /api/productos/{id}`: Eliminar producto (204 No Content).
- `GET /api/productos/categoria/{categoriaId}`: Consultar productos por categoría.
- `POST /api/productos/{productoId}/etiquetas/{etiquetaId}`: Asociar etiqueta a un producto.
- `DELETE /api/productos/{productoId}/etiquetas/{etiquetaId}`: **(Reto 1)** Eliminar asociación producto-etiqueta.
- `GET /api/productos/etiqueta/{etiquetaId}`: **(Reto 2)** Consultar productos por etiqueta.

### Etiquetas (`/api/etiquetas`)
- `GET /api/etiquetas`: Listar todas las etiquetas.
- `GET /api/etiquetas/{id}`: Obtener etiqueta por ID.
- `POST /api/etiquetas`: Registrar nueva etiqueta.

### Categorías (`/api/categorias`) y Proveedores (`/api/proveedores`)
- Endpoints `GET` y `POST` para la administración de categorías y proveedores.

---

## 📄 Documentación Completa y Evidencias
Consulte el archivo [**`DOCUMENTO_ENTREGA.md`**](./DOCUMENTO_ENTREGA.md) para ver:
- Matriz de verificación detallada de cumplimiento.
- Código fuente de Services, DTOs y Controllers.
- Diagrama Entidad-Relación (Mermaid).
- Salidas reales de PostgreSQL (`flyway_schema_history`, `etiqueta`, `producto_etiqueta`).
- Pruebas y respuestas JSON de Postman.
- Respuestas exhaustivas a las 12 preguntas de comprobación de aprendizaje.

---

## 🛠️ Cómo Ejecutar el Proyecto
```powershell
.\mvnw.cmd spring-boot:run
```
El servidor responderá en `http://localhost:8080`.
