CREATE TABLE etiqueta (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE producto_etiqueta (
    producto_id INTEGER NOT NULL,
    etiqueta_id INTEGER NOT NULL,
    PRIMARY KEY (producto_id, etiqueta_id),
    CONSTRAINT fk_pe_producto
        FOREIGN KEY (producto_id)
        REFERENCES producto(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_pe_etiqueta
        FOREIGN KEY (etiqueta_id)
        REFERENCES etiqueta(id)
        ON DELETE CASCADE
);
