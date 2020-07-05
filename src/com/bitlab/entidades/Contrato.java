
package com.bitlab.entidades;

import java.sql.Date;

/**
 *
 * @author henry
 */
public class Contrato {
    //Pojo Contrato
    
    private int idContrato;
    private String nombre;
    private double salario;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;
    private String observacion;
    private boolean estatus;
    private String horario;
    private byte horasDia; //horas trabajadas al dia
    private byte diasLaborados; // dias laborados al mes
    private String formaPago; //el pago en efectivo, cheque, depositos, etc
    private String peridoPago; // pago semana, quincenal o mesual
    private String tipoPlaza; // temporal, fija, proyecto,pasantia
    private int idEmpleado; //llave foranea
    
    //constructores

    public Contrato() {
    }

    public Contrato(int idContrato, String nombre, double salario, Date fechaInicioContrato, Date fechaFinContrato, String observacion, boolean estatus, String horario, byte horasDia, byte diasLaborados, String formaPago, String peridoPago, String tipoPlaza, int idEmpleado) {
        this.idContrato = idContrato;
        this.nombre = nombre;
        this.salario = salario;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
        this.observacion = observacion;
        this.estatus = estatus;
        this.horario = horario;
        this.horasDia = horasDia;
        this.diasLaborados = diasLaborados;
        this.formaPago = formaPago;
        this.peridoPago = peridoPago;
        this.tipoPlaza = tipoPlaza;
        this.idEmpleado = idEmpleado;
    }
    
    /*
        Metodos Getter y Setter
    */

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public byte getHorasDia() {
        return horasDia;
    }

    public void setHorasDia(byte horasDia) {
        this.horasDia = horasDia;
    }

    public byte getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(byte diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getPeridoPago() {
        return peridoPago;
    }

    public void setPeridoPago(String peridoPago) {
        this.peridoPago = peridoPago;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public void setTipoPlaza(String tipoPlaza) {
        this.tipoPlaza = tipoPlaza;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
}
