
package com.bitlab.entidades;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author henry
 */
public class Empleado {
    private int idEmpleado;
    private String nombres;
    private String apellidos;
    private String genero;
    private String documentoIdentidad;
    private Timestamp fechaNacimiento;
    private String correo;
    private String direccion;
    private String telefono;
    private String nif;
    private double comision;
    private String profesion;
    private boolean estado; // ver si empleado esta activo o inactivo
    private int idRol;
    private int idDepartamento;

    //Contructor que recibira un arreglo de objetos
    public Empleado(Object[] datos) {
        for(int i=0; i<1; i++){
            this.idEmpleado = (int) datos[0];
            this.nombres = (String) datos[1];
            this.apellidos = (String) datos[2];
            this.genero = (String) datos[3];
            this.documentoIdentidad = (String) datos[4];
            this.fechaNacimiento = (Timestamp) datos[5];
            this.correo = (String) datos[6];
            this.direccion = (String) datos[7];
            this.telefono = (String) datos[8];
            this.nif = (String) datos[9];
            this.comision = Double.parseDouble((String) datos[10]);
            this.profesion = (String) datos[11];
            this.estado = (boolean) datos[12];
            this.idRol = (int) datos[13];
            this.idDepartamento = (int) datos[14];
        }
    }

    public Empleado(int idEmpleado, String nombres, String apellidos, String genero, String documentoIdentidad, Timestamp fechaNacimiento, String correo, String direccion, String telefono, String nif, double comision, String profesion, boolean estado, int idRol, int idDepartamento) {
        this.idEmpleado = idEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.documentoIdentidad = documentoIdentidad;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nif = nif;
        this.comision = comision;
        this.profesion = profesion;
        this.estado = estado;
        this.idRol = idRol;
        this.idDepartamento = idDepartamento;
    }
    
    

    /*
         Metodos Getter y Setter
    */

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
    
    
}
