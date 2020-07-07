/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aguilar
 */
public class DepartamentoDAO extends ConexionDAO<Departamento> {
    

    @Override
    protected String obtenerNombreTabla() {
        return "BIT_DEPARTAMENTO";
    }

    @Override
    protected String[] obtenerColumnas() {

        String[] columnas = {"DEPT_ID_PK", "DEPT_NOMBRE", "DEPT_DESCRIPCION", "DEPT_PRESUPUESTO", "DEPT_VACANTES_REQUERIDAS", "DEPT_VACANTE_DISPONIBLE", "DEPT_ESTADO"};
        return columnas;
    }
    
    public String[] obtenerDepartamentos(){
        String[] departamentos = {""};
        return departamentos;
    }

    @Override
    protected String obtenerLLavePrimariaTabla() {
        return "DEPT_ID_PK";
    }

    @Override
    protected Departamento getMappingResulsets(ResultSet rs) throws SQLException {

        return new Departamento(
                rs.getInt("DEPT_ID_PK"),
                rs.getString("DEPT_NOMBRE"),
                rs.getString("DEPT_DESCRIPCION"),
                rs.getDouble("DEPT_PRESUPUESTO"),
                rs.getShort("DEPT_VACANTES_REQUERIDAS"),
                rs.getShort("DEPT_VACANTE_DISPONIBLE"),
                rs.getBoolean("DEPT_ESTADO"));
    }

    @Override
    protected void getMappingParamsToInsert(PreparedStatement ps, Departamento entity) throws SQLException {
        ps.setInt(1, entity.getIdDepartamento());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getDescripcion());
        ps.setDouble(4, entity.getPresupuesto());
        ps.setShort(5, entity.getVacantesRequeridas());
        ps.setShort(6, entity.getVacantesDisponibles());
        ps.setBoolean(7, entity.isEstado());
    }

    @Override
    protected void setMappingUpdateStatement(PreparedStatement ps, Departamento entity) throws SQLException {
        ps.setInt(1, entity.getIdDepartamento());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getDescripcion());
        ps.setDouble(4, entity.getPresupuesto());
        ps.setShort(5, entity.getVacantesRequeridas());
        ps.setShort(6, entity.getVacantesDisponibles());
        ps.setBoolean(7, entity.isEstado());
        ps.setInt(8, entity.getIdDepartamento());
    }

    @Override
    public void actualizarDatos(Departamento entity) throws ClassNotFoundException, SQLException {
        super.actualizarDatos(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Departamento> obtenerDatos(int cantidadDatos) throws ClassNotFoundException, SQLException {
        cantidadDatos = 1000;
        Connection con = obtenerConexion();
        Statement st = con.createStatement();
        st.setMaxRows(cantidadDatos);
        ResultSet rs = st.executeQuery(obtenerSelectSQL() + " WHERE DEPT_ESTADO=TRUE"); //ejecutar el query

        //crear una lista de objetos
        List<Departamento> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(getMappingResulsets(rs)); //agregar los datos a la lista
        }
        cerrarJDBCObjects(con, st, rs);
        return depts;
    }
    
    

}
