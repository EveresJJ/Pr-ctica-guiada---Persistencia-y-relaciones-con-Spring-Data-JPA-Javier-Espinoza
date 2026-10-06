package ni.edu.uam.gestion_productos.service;

import ni.edu.uam.gestion_productos.dto.ProductoRequestDTO;
import ni.edu.uam.gestion_productos.entity.Categoria;
import ni.edu.uam.gestion_productos.entity.Etiqueta;
import ni.edu.uam.gestion_productos.entity.Producto;
import ni.edu.uam.gestion_productos.entity.Proveedor;
import ni.edu.uam.gestion_productos.repository.CategoriaRepository;
import ni.edu.uam.gestion_productos.repository.EtiquetaRepository;
import ni.edu.uam.gestion_productos.repository.ProductoRepository;
import ni.edu.uam.gestion_productos.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final EtiquetaRepository etiquetaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           ProveedorRepository proveedorRepository,
                           EtiquetaRepository etiquetaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + id));
    }

    @Transactional
    public Producto crear(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Proveedor proveedor = null;
        if (dto.getProveedorId() != null) {
            proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + dto.getProveedorId()));
        }

        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia());

        if (dto.getEtiquetasIds() != null && !dto.getEtiquetasIds().isEmpty()) {
            Set<Etiqueta> etiquetas = new HashSet<>(etiquetaRepository.findAllById(dto.getEtiquetasIds()));
            producto.setEtiquetas(etiquetas);
        }

        return productoRepository.save(producto);
    }

    @Transactional
    public Producto actualizar(Integer id, ProductoRequestDTO dto) {
        Producto producto = obtenerPorId(id);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Proveedor proveedor = null;
        if (dto.getProveedorId() != null) {
            proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + dto.getProveedorId()));
        }

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setExistencia(dto.getExistencia());

        if (dto.getEtiquetasIds() != null) {
            Set<Etiqueta> etiquetas = new HashSet<>(etiquetaRepository.findAllById(dto.getEtiquetasIds()));
            producto.setEtiquetas(etiquetas);
        }

        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminar(Integer id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(Integer categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new NoSuchElementException("Categoría no encontrada con ID: " + categoriaId);
        }
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Transactional
    public Producto asociarEtiqueta(Integer productoId, Integer etiquetaId) {
        Producto producto = obtenerPorId(productoId);
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con ID: " + etiquetaId));

        producto.agregarEtiqueta(etiqueta);
        return productoRepository.save(producto);
    }

    @Transactional
    public Producto desasociarEtiqueta(Integer productoId, Integer etiquetaId) {
        Producto producto = obtenerPorId(productoId);
        Etiqueta etiqueta = etiquetaRepository.findById(etiquetaId)
                .orElseThrow(() -> new NoSuchElementException("Etiqueta no encontrada con ID: " + etiquetaId));

        producto.eliminarEtiqueta(etiqueta);
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> listarPorEtiqueta(Integer etiquetaId) {
        if (!etiquetaRepository.existsById(etiquetaId)) {
            throw new NoSuchElementException("Etiqueta no encontrada con ID: " + etiquetaId);
        }
        return productoRepository.findByEtiquetasId(etiquetaId);
    }
}
