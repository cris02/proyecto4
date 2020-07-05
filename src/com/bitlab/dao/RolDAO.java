/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aguilar
 */
public class RolDAO extends ConexionDAO<Rol> {

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_ROL";
    }

    @Override
    protected String[] obtenerColumnas() {
        String[] str = {"ROL_ID_PK", "ROL_NOMBREROL", "ROL_DESCRIPCION", "ROL_ESTATUS"};
        return str;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "ROL_ID_PK";
    }

    @Override
    protected Rol getMappingResulsets(ResultSet rs) throws SQLException {
        return (new Rol(
                rs.getInt("ROL_ID_PK"),
                rs.getString("ROL_NOMBREROL"),
                rs.getString("ROL_DESCRIPCION"),
                rs.getBoolean("ROL_ESTATUS")));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, Rol entity) throws SQLException {
        ps.setInt(1, entity.getIdRol());
        ps.setString(2, entity.getNombreRol());
        ps.setString(3, entity.getDescripcion());
        ps.setBoolean(4, entity.isEstatus());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Rol entity) throws SQLException {
        ps.setInt(1, entity.getIdRol());
        ps.setString(2, entity.getNombreRol());
        ps.setString(3, entity.getDescripcion());
        ps.setBoolean(4, entity.isEstatus());
        ps.setInt(5, entity.getIdRol());
    }

}
