package ni.edu.uam.gestion_productos.service;

import ni.edu.uam.gestion_productos.entity.Etiqueta;
import ni.edu.uam.gestion_productos.repository.EtiquetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaService(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Transactional(readOnly = true)
    public List<Etiqueta> listarTodas() {
        return etiquetaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Etiqueta obtenerPorId(Integer id) {
        return etiquetaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con ID: " + id));
    }

    @Transactional
    public Etiqueta crear(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }
}
