/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aguilar
 */
public class UsuarioDAO extends ConexionDAO<Usuario>{

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_USUARIO";
    }

    @Override
    protected String[] obtenerColumnas() {
        String[] str = {"USU_ID_PK", "USU_NOMBRE", "USU_CORREO", "USU_CONTRASENA", "USU_ESTATUS", "USU_FECHA_CREACION", "USU_FECHA_CONEXION", "ROL_ID_FK"};
        return str;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "USU_ID_PK";
    }

    @Override
    protected Usuario getMappingResulsets(ResultSet rs) throws SQLException {
        return (new Usuario(
                rs.getInt("USU_ID_PK"),
                rs.getString("USU_NOMBRE"),
                rs.getString("USU_CORREO"),
                rs.getString("USU_CONTRASENA"),
                rs.getBoolean("USU_ESTATUS"),
                rs.getDate("USU_FECHA_CREACION"),
                rs.getDate("USU_FECHA_CONEXION"),
                rs.getInt("ROL_ID_FK")));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, Usuario entity) throws SQLException {
        ps.setInt(1, entity.getIdUsuario());
        ps.setString(2, entity.getNombreUsuario());
        ps.setString(3, entity.getCorreo());
        ps.setString(4, entity.getContrasena());
        ps.setBoolean(5, entity.isEstatus());
        ps.setDate(6, entity.getFechaCreacion());
        ps.setDate(7, entity.getUltimaConexio());
        ps.setInt(8, entity.getIdRol());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Usuario entity) throws SQLException {
        ps.setInt(1, entity.getIdUsuario());
        ps.setString(2, entity.getNombreUsuario());
        ps.setString(3, entity.getCorreo());
        ps.setString(4, entity.getContrasena());
        ps.setBoolean(5, entity.isEstatus());
        ps.setDate(6, entity.getFechaCreacion());
        ps.setDate(7, entity.getUltimaConexio());
        ps.setInt(8, entity.getIdRol());
        ps.setInt(9, entity.getIdUsuario());
    }

    
}
