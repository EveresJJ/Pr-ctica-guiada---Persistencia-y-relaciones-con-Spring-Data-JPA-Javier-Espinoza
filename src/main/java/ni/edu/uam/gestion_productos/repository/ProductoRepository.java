package ni.edu.uam.gestion_productos.repository;

import ni.edu.uam.gestion_productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriaId(Integer categoriaId);
    List<Producto> findByEtiquetasId(Integer etiquetaId);
}
