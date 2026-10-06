package ni.edu.uam.gestion_productos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class ProductoRequestDTO {

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El ID de categoría es obligatorio")
    private Integer categoriaId;

    private Integer proveedorId;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precioVenta;

    @Min(value = 0, message = "La existencia no puede ser negativa")
    private int existencia;

    private Set<Integer> etiquetasIds;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String codigo, String nombre, String descripcion, Integer categoriaId, Integer proveedorId, BigDecimal precioVenta, int existencia, Set<Integer> etiquetasIds) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
        this.precioVenta = precioVenta;
        this.existencia = existencia;
        this.etiquetasIds = etiquetasIds;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Set<Integer> getEtiquetasIds() {
        return etiquetasIds;
    }

    public void setEtiquetasIds(Set<Integer> etiquetasIds) {
        this.etiquetasIds = etiquetasIds;
    }
}
