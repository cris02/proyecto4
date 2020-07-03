
package com.bitlab.entidades;

import java.sql.Date;

/**
 *
 * @author henry
 */
public class Planilla {
    private int idPlanilla;
    private String nombre;
    private String observaciones;
    private Date fechaInicio;
    private Date fechaFin;
    private String responsable; // autor de la planilla
    private int idContrato; //llave foranea de contrato

    public Planilla() {
    }

    public Planilla(int idPlanilla, String nombre, String observaciones, Date fechaInicio, Date fechaFin, String responsable, int idContrato) {
        this.idPlanilla = idPlanilla;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.responsable = responsable;
        this.idContrato = idContrato;
    }
    
    /*
        Metodos Getter y Setter
    */

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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    
    

    
    
}
