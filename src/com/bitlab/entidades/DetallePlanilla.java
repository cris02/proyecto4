
package com.bitlab.entidades;

/**
 *
 * @author henry
 */
public class DetallePlanilla {
    //pojo detallePlanilla
    
    private int idDetallePlanilla;
    private double descISSS;
    private double descAFP;
    private double descRenta;
    private double totalPagar;
    private byte diasLaborados;
    private int idPlanilla;
    
    //constructores

    public DetallePlanilla() {
    }

    public DetallePlanilla(int idDetallePlanilla, double descISSS, double descAFP, double descRenta, double totalPagar, byte diasLaborados, int idPlanilla) {
        this.idDetallePlanilla = idDetallePlanilla;
        this.descISSS = descISSS;
        this.descAFP = descAFP;
        this.descRenta = descRenta;
        this.totalPagar = totalPagar;
        this.diasLaborados = diasLaborados;
        this.idPlanilla = idPlanilla;
    }
    
    
    /*
        Metodos Getter y Setter
    */

    public int getIdDetallePlanilla() {
        return idDetallePlanilla;
    }

    public void setIdDetallePlanilla(int idDetallePlanilla) {
        this.idDetallePlanilla = idDetallePlanilla;
    }

    public double getDescISSS() {
        return descISSS;
    }

    public void setDescISSS(double descISSS) {
        this.descISSS = descISSS;
    }

    public double getDescAFP() {
        return descAFP;
    }

    public void setDescAFP(double descAFP) {
        this.descAFP = descAFP;
    }

    public double getDescRenta() {
        return descRenta;
    }

    public void setDescRenta(double descRenta) {
        this.descRenta = descRenta;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public byte getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(byte diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }
    
    

  
    
    
}
