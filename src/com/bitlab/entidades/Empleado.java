
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class Empleado {
    private int idEmpleado;
    private String nombres;
    private String apellidos;
    private char genero;
    private String documentoIdentidad;
    private boolean estado;
    private int idRol;
    private int idDepartamento;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombres, String apellidos, char genero, String documentoIdentidad, int idRol, int idDepartamento, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.documentoIdentidad = documentoIdentidad;
        this.idRol = idRol;
        this.idDepartamento = idDepartamento;
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
