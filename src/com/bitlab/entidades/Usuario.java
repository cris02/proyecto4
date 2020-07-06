
package com.bitlab.entidades;

import java.sql.Date;

/**
 *
 * @author henry
 */
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    private boolean estatus; //ver si el usario esta activo o no
    private Date fechaCreacion;
    private Date ultimaConexio;
    private int idRol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String correo, String contrasena, boolean estatus, Date fechaCreacion, Date ultimaConexio, int idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.ultimaConexio = ultimaConexio;
        this.idRol = idRol;
    }

    public Usuario(String nombreUsuario, String correo, String contrasena, boolean estatus, int idRol) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estatus = estatus;
        this.idRol = idRol;
    }
    
    
    
    /*
        Metodos Getter y Setter
    */

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaConexio() {
        return ultimaConexio;
    }

    public void setUltimaConexio(Date ultimaConexio) {
        this.ultimaConexio = ultimaConexio;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return  "\tNOMBRE = " + nombreUsuario + "\tCORREO = " + correo + "\tCONTRASENA = " + contrasena  + "\tCODIGO ROL = " + idRol ;
    }
    
    
   
}
