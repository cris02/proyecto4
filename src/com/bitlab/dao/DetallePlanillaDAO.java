/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.DetallePlanilla;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aguilar
 */
public class DetallePlanillaDAO extends ConexionDAO<DetallePlanilla> {

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_DETALLEPLANILLA";
    }

    @Override
    protected String[] obtenerColumnas() {
        String[] str = {"DPLA_ID_PK", "DPLA_ISSS", "DPLA_AFP", "DPLA_IMP_RENTA", "DPLA_TOT_PAGAR", "DPLA_DIAS_LABORADOS", "PLA_ID_FK", "CONT_ID_PK"};
        return str;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "DPLA_ID_PK";
    }

    @Override
    protected DetallePlanilla getMappingResulsets(ResultSet rs) throws SQLException {
        return (new DetallePlanilla(
                rs.getInt("DPLA_ID_PK"),
                rs.getDouble("DPLA_ISSS"),
                rs.getDouble("DPLA_AFP"),
                rs.getDouble("DPLA_IMP_RENTA"),
                rs.getDouble("DPLA_TOT_PAGAR"),
                rs.getByte("DPLA_DIAS_LABORADOS"),
                rs.getInt("PLA_ID_FK"),
                rs.getInt("CONT_ID_PK")));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, DetallePlanilla entity) throws SQLException {
        ps.setInt(1, entity.getIdDetallePlanilla());
        ps.setDouble(2, entity.getDescISSS());
        ps.setDouble(3, entity.getDescAFP());
        ps.setDouble(4, entity.getDescRenta());
        ps.setDouble(5, entity.getTotalPagar());
        ps.setByte(6, entity.getDiasLaborados());
        ps.setInt(7, entity.getIdPlanilla());
        ps.setInt(8, entity.getIdContrato());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, DetallePlanilla entity) throws SQLException {
        ps.setInt(1, entity.getIdDetallePlanilla());
        ps.setDouble(2, entity.getDescISSS());
        ps.setDouble(3, entity.getDescAFP());
        ps.setDouble(4, entity.getDescRenta());
        ps.setDouble(5, entity.getTotalPagar());
        ps.setByte(6, entity.getDiasLaborados());
        ps.setInt(7, entity.getIdPlanilla());
        ps.setInt(8, entity.getIdContrato());
         ps.setInt(9, entity.getIdDetallePlanilla());
    }

    
    public void obtenerDatoTablas(BufferedWriter bw) throws ClassNotFoundException, SQLException, IOException {
        String sql = "SELECT bc.CONT_SALARIO, bc.CONT_HORAS_DIA, bc.CONT_PERIODO_PAGO, be.EMP_NOMBRES, be.EMP_APELLIDOS, be.EMP_COMISION, bd.DPLA_ISSS, bd.DPLA_AFP,\n" +
"bd.DPLA_IMP_RENTA, bd.DPLA_TOT_PAGAR\n" +
"FROM BIT_CONTRATO bc\n" +
"join BIT_EMPLEADO be on bc.EMP_ID_FK = be.EMP_ID_PK\n" +
"join BIT_DETALLEPLANILLA bd on bd.CONT_ID_PK = bc.CONT_ID_PK\n";
        System.out.println(sql); //datos para ver la construccion del sql
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            bw.newLine();
            bw.write("Nombre Empleado: " + rs.getString("EMP_NOMBRES") + " "+ rs.getString("EMP_APELLIDOS"));
            bw.newLine();
            bw.write("Salario Base: $" + rs.getString("CONT_SALARIO"));
            bw.newLine();
            bw.write("Horas diarias trabajadas: " +rs.getString("CONT_HORAS_DIA"));
            bw.newLine();
            bw.write("Periodo de pago: " +rs.getString("CONT_PERIODO_PAGO"));
            bw.newLine();
            bw.write("Comision: $" +rs.getString("EMP_COMISION"));
            bw.newLine();
            bw.write("ISSS: $" +rs.getString("DPLA_ISSS"));
            bw.newLine();
            bw.write("AFP: $" +rs.getString("DPLA_AFP"));
            bw.newLine();
            bw.write("Renta: $" +rs.getString("DPLA_IMP_RENTA"));
            bw.newLine();
            bw.write("Total a pagar: $" +rs.getString("DPLA_TOT_PAGAR"));
            bw.newLine();
        }
        cerrarJDBCObjects(con, ps, rs);
    }
    
    
    

}
