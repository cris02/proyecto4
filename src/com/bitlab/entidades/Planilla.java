
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class Planilla {
    private int idPlanilla;
    private String nombre;
    private String descripcion;

    public Planilla() {
    }

    public Planilla(int idPlanilla, String nombre, String descripcion) {
        this.idPlanilla = idPlanilla;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
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
