/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.DetallePlanilla;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
