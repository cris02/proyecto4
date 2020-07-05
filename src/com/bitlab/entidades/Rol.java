
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class Rol {
    //pojos Rol
    
    private int idRol;
    private String nombreRol;
    private String descripcion;
    private boolean estatus;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol, String descripcion, boolean estatus) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.estatus = estatus;
    }
    
    /*
        Metodos getter y Setter
    */

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "\n-> ID_ROL = " + idRol + "\t NOMBRE DEL ROL = " + nombreRol + "\n";
    }
    
    
}
