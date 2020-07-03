/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

/**
 *
 * @author Aguilar
 */
public class TipoPlaza {
    
    private int idTipoPlaza;
    private String nombre;
    private String descripcion;

    public TipoPlaza() {
    }

    public TipoPlaza(int idTipoPlaza, String nombre, String descripcion) {
        this.idTipoPlaza = idTipoPlaza;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    /*
        Metodos Getter y Setter
    */

    public int getIdTipoPlaza() {
        return idTipoPlaza;
    }

    public void setIdTipoPlaza(int idTipoPlaza) {
        this.idTipoPlaza = idTipoPlaza;
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
