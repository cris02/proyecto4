
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class DetalleContrato {
    private int idDetalle;
    private String descripcion;

    public DetalleContrato() {
    }

    public DetalleContrato(int idDetalle, String descripcion) {
        this.idDetalle = idDetalle;
        this.descripcion = descripcion;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
