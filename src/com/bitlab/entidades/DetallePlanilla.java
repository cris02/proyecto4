
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class DetallePlanilla {
    private int idDetalle;
    private String nombre;
    private String descripcion;

    public DetallePlanilla() {
    }

    public DetallePlanilla(int idDetalle, String nombre, String descripcion) {
        this.idDetalle = idDetalle;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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
    
    
}
