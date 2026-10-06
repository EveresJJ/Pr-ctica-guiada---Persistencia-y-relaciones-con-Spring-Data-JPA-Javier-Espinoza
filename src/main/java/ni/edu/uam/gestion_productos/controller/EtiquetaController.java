package ni.edu.uam.gestion_productos.controller;

import ni.edu.uam.gestion_productos.entity.Etiqueta;
import ni.edu.uam.gestion_productos.service.EtiquetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @GetMapping
    public ResponseEntity<List<Etiqueta>> listar() {
        return ResponseEntity.ok(etiquetaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(etiquetaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Etiqueta> crear(@RequestBody Etiqueta etiqueta) {
        Etiqueta creada = etiquetaService.crear(etiqueta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}
