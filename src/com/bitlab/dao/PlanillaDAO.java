/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Planilla;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aguilar
 */
public class PlanillaDAO extends ConexionDAO<Planilla> {

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_PLANILLA";
    }

    @Override
    protected String[] obtenerColumnas() {
        String[] str = {"PLA_ID_PK", "PLA_NOMBRE", "PLA_OBSERVACION", "PLA_FECHA_INICIO", "PLA_FECHA_FIN", "PLA_RESPONSABLE"};
        return str;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "PLA_ID_PK";
    }

    @Override
    protected Planilla getMappingResulsets(ResultSet rs) throws SQLException {
        return (new Planilla(
                rs.getInt("PLA_ID_PK"),
                rs.getString("PLA_NOMBRE"),
                rs.getString("PLA_OBSERVACION"),
                rs.getDate("PLA_FECHA_INICIO"),
                rs.getDate("PLA_FECHA_FIN"),
                rs.getString("PLA_RESPONSABLE")));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, Planilla entity) throws SQLException {
        ps.setInt(1, entity.getIdPlanilla());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getObservaciones());
        ps.setDate(4, entity.getFechaInicio());
        ps.setDate(5, entity.getFechaFin());
        ps.setString(6, entity.getResponsable());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Planilla entity) throws SQLException {
        ps.setInt(1, entity.getIdPlanilla());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getObservaciones());
        ps.setDate(4, entity.getFechaInicio());
        ps.setDate(5, entity.getFechaFin());
        ps.setString(6, entity.getResponsable());
        ps.setInt(7, entity.getIdPlanilla());
    }

}
