
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class Departamento {
    //Pojo departamento
    
    private int idDepartamento;
    private String nombre;
    private String descripcion;
    private double presupuesto;
    private short vacantesRequeridas;
    private short vacantesDisponibles;
    private boolean estado; // para verificar si el depto esta activo o inactivo
    
    //constructores
    public Departamento() {
    }

    public Departamento(int idDepartamento, String nombre, String descripcion, double presupuesto, short vacantesRequeridas, short vacantesDisponibles, boolean estado) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.vacantesRequeridas = vacantesRequeridas;
        this.vacantesDisponibles = vacantesDisponibles;
        this.estado = estado;
    }
    
    /*
        Metodos Getter y Setter
    */

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
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

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public short getVacantesRequeridas() {
        return vacantesRequeridas;
    }

    public void setVacantesRequeridas(short vacantesRequeridas) {
        this.vacantesRequeridas = vacantesRequeridas;
    }

    public short getVacantesDisponibles() {
        return vacantesDisponibles;
    }

    public void setVacantesDisponibles(short vacantesDisponibles) {
        this.vacantesDisponibles = vacantesDisponibles;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

   
    
}
