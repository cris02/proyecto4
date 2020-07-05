/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Contrato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aguilar
 */
public class ContratoDAO extends ConexionDAO<Contrato> {

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_CONTRATO";
    }

    @Override
    protected String[] obtenerColumnas() {
        String[] str = {"CONT_ID_PK", "CONT_NOMBRE", "CONT_SALARIO", "CONT_FECHA_INICIO_CONTRATO", "CONT_FECHA_FIN_CONTRATO", "CONT_OBSERVACION", "CONT_ESTATUS",
            "CONT_HORARIO", "CONT_HORAS_DIA", "CONT_DIAS_LABORADOS", "CONT_TIPO_PAGO", "CONT_PERIODO_PAGO", "CONT_TIPOPLAZA", "EMP_ID_FK"};
        return str;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "CONT_ID_PK";
    }

    @Override
    protected Contrato getMappingResulsets(ResultSet rs) throws SQLException {
        return (new Contrato(
                rs.getInt("CONT_ID_PK"),
                rs.getString("CONT_NOMBRE"),
                rs.getDouble("CONT_SALARIO"),
                rs.getDate("CONT_FECHA_INICIO_CONTRATO"),
                rs.getDate("CONT_FECHA_FIN_CONTRATO"),
                rs.getString("CONT_OBSERVACION"),
                rs.getBoolean("CONT_ESTATUS"),
                rs.getString("CONT_HORARIO"),
                rs.getByte("CONT_HORAS_DIA"),
                rs.getByte("CONT_DIAS_LABORADOS"),
                rs.getString("CONT_TIPO_PAGO"),
                rs.getString("CONT_PERIODO_PAGO"),
                rs.getString("CONT_TIPOPLAZA"),
                rs.getInt("EMP_ID_FK")));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, Contrato entity) throws SQLException {
        ps.setInt(1, entity.getIdContrato());
        ps.setString(2, entity.getNombre());
        ps.setDouble(3, entity.getSalario());
        ps.setDate(4, entity.getFechaInicioContrato());
        ps.setDate(5, entity.getFechaFinContrato());
        ps.setString(6, entity.getObservacion());
        ps.setBoolean(7, entity.isEstatus());
        ps.setString(8, entity.getHorario());
        ps.setByte(9, entity.getHorasDia());
        ps.setByte(10, entity.getDiasLaborados());
        ps.setString(11, entity.getFormaPago());
        ps.setString(12, entity.getPeridoPago());
        ps.setString(13, entity.getTipoPlaza());
        ps.setInt(14, entity.getIdEmpleado());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Contrato entity) throws SQLException {
        ps.setInt(1, entity.getIdContrato());
        ps.setString(2, entity.getNombre());
        ps.setDouble(3, entity.getSalario());
        ps.setDate(4, entity.getFechaInicioContrato());
        ps.setDate(5, entity.getFechaFinContrato());
        ps.setString(6, entity.getObservacion());
        ps.setBoolean(7, entity.isEstatus());
        ps.setString(8, entity.getHorario());
        ps.setByte(9, entity.getHorasDia());
        ps.setByte(10, entity.getDiasLaborados());
        ps.setString(11, entity.getFormaPago());
        ps.setString(12, entity.getPeridoPago());
        ps.setString(13, entity.getTipoPlaza());
        ps.setInt(14, entity.getIdEmpleado());
        ps.setInt(15, entity.getIdContrato());
    }

}
