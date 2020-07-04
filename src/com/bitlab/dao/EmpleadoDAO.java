/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aguilar
 */
public class EmpleadoDAO extends ConexionDAO<Empleado>{

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_EMPLEADO";
    }
    
    //"EMP_ID_PK", 
    @Override
    protected String[] obtenerColumnas() {
        String [] columnas = {"EMP_ID_PK", "EMP_NOMBRES", "EMP_APELLIDOS", "EMP_GENERO", "EMP_DOCUMENTO", "EMP_FECHA_NACIMIENTO",
    "EMP_CORREO", "EMP_DIRECCION", "EMP_TELEFONO", "EMP_NIF", "EMP_COMISION", "EMP_PROFESION", "EMP_ESTADO", "ROL_ID_FK", "DEPT_ID_FK"};
        return columnas;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "EMP_ID_PK";
    }

    @Override
    protected Empleado getMappingResulsets(ResultSet rs) throws SQLException {
        return new Empleado(
        rs.getInt("EMP_ID_PK"),
        rs.getString("EMP_NOMBRES"),
        rs.getString("EMP_APELLIDOS"),
        rs.getString("EMP_GENERO"),
        rs.getString("EMP_DOCUMENTO"),
        rs.getTimestamp("EMP_FECHA_NACIMIENTO"),
        rs.getString("EMP_CORREO"),
        rs.getString("EMP_DIRECCION"),
        rs.getString("`EMP_TELEFONO`"),
        rs.getString("EMP_NIF"),
        rs.getDouble("EMP_COMISION"),
        rs.getString("EMP_PROFESION"),
        rs.getBoolean("EMP_ESTADO"),
        rs.getInt("ROL_ID_FK"),
        rs.getInt("DEPT_ID_FK")
        );
    }

    @Override /* es get o set?*/
    protected void getMappingParamsToInsert(PreparedStatement ps, Empleado entity) throws SQLException {
        ps.setInt(1, entity.getIdEmpleado());
        ps.setString(2, entity.getNombres());
        ps.setString(3, entity.getApellidos());
        ps.setString(4, entity.getGenero());
        ps.setString(5, entity.getDocumentoIdentidad());
        ps.setTimestamp(6, entity.getFechaNacimiento());
        ps.setString(7, entity.getCorreo());
        ps.setString(8, entity.getDireccion());
        ps.setString(9, entity.getTelefono());
        ps.setString(10, entity.getNif());
        ps.setDouble(11, entity.getComision());
        ps.setString(12, entity.getProfesion());
        ps.setBoolean(13, entity.isEstado());  /* ARREGLAR ESTO CREO QUE SERIA BOOLEAN*/ 
        ps.setInt(14, entity.getIdRol());
        ps.setInt(15, entity.getIdDepartamento());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Empleado entity) throws SQLException {
        ps.setInt(1, entity.getIdEmpleado());
        ps.setString(2, entity.getNombres());
        ps.setString(3, entity.getApellidos());
        ps.setString(4, entity.getDocumentoIdentidad());
        ps.setTimestamp(5, entity.getFechaNacimiento());
        ps.setString(6, entity.getCorreo());
        ps.setString(7, entity.getDireccion());
        ps.setString(8, entity.getTelefono());
        ps.setString(9, entity.getNif());
        ps.setDouble(10, entity.getComision());
        ps.setString(11, entity.getProfesion());
        ps.setShort(12, (short)1);  /* ARREGLAR ESTO CREO QUE SERIA BOOLEAN*/ 
        ps.setInt(13, entity.getIdEmpleado());
    }

    
}
