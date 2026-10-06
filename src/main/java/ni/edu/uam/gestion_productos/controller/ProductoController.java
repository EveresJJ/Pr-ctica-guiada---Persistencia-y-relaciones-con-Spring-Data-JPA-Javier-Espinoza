package ni.edu.uam.gestion_productos.controller;

import jakarta.validation.Valid;
import ni.edu.uam.gestion_productos.dto.ProductoRequestDTO;
import ni.edu.uam.gestion_productos.entity.Producto;
import ni.edu.uam.gestion_productos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
    }

    @PostMapping("/{productoId}/etiquetas/{etiquetaId}")
    public ResponseEntity<Producto> asociarEtiqueta(@PathVariable Integer productoId, @PathVariable Integer etiquetaId) {
        return ResponseEntity.ok(productoService.asociarEtiqueta(productoId, etiquetaId));
    }

    @DeleteMapping("/{productoId}/etiquetas/{etiquetaId}")
    public ResponseEntity<Producto> desasociarEtiqueta(@PathVariable Integer productoId, @PathVariable Integer etiquetaId) {
        return ResponseEntity.ok(productoService.desasociarEtiqueta(productoId, etiquetaId));
    }

    @GetMapping("/etiqueta/{etiquetaId}")
    public ResponseEntity<List<Producto>> listarPorEtiqueta(@PathVariable Integer etiquetaId) {
        return ResponseEntity.ok(productoService.listarPorEtiqueta(etiquetaId));
    }
}
