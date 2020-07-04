/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.utilidades.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Aguilar
 */
public abstract class ConexionDAO<T> {

    ConexionDB conexionDB = new ConexionDB();
    public final short LIMIT_RECORDS = 10;
    public final String SQL_SELECT = "Select [CAMPOS] from [TABLA]";
    public final String SQL_INSERT = "insert into [TABLA] values([CAMPOS])";
    public final String SQL_WHERE = " Where ";
    public final String SQL_DELETE = "delete from [TABLA]";
    public final String SQL_UPDATE = "update [TABLA] set ";
    public final String SQL_PARAM = "= ?";

    //metodo para obtener la conecion
    protected Connection obtenerConexion() throws ClassNotFoundException, SQLException {
        return conexionDB.crearConexionDB();
    }

    /*
        Metodos para cerar las conexiones existentes
     */
    //cerrando la conexion
    protected void cerrarJDBCObjects(Connection con) throws SQLException {
        conexionDB.cerrarConexion(con);
    }

    protected void cerrarJDBCObjects(Connection con, Statement st) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion y Statement
    protected void cerrarJDBCObjects(Connection con, Statement st, ResultSet rs) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion y PreparedStatement
    protected void cerrarJDBCObjects(Connection con, PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }

        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion, PreparedStatement y ResultSet
    protected void cerrarJDBCObjects(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        conexionDB.cerrarConexion(con);
    }

    /*
        metodo para manipular datos
     */
    //metodo para caragar todos los datos de la base
    public List<T> obtenerDatos() throws ClassNotFoundException, SQLException {
        Connection con = obtenerConexion();
        Statement st = con.createStatement();
        st.setMaxRows(LIMIT_RECORDS);
        ResultSet rs = st.executeQuery(obtenerSelectSQL());

        //crear una lista de pojo empleados
        List<T> objects = new ArrayList<>();
        while (rs.next()) {
            objects.add(getMappingResulsets(rs));
        }
        cerrarJDBCObjects(con, st, rs);
        return objects;
    }

    //metodo para obtener un dato por id
    public T obtenerDatoID(Object id) throws ClassNotFoundException, SQLException {
        String sql = obtenerSelectSQL() + SQL_WHERE + obtenerLLavePrimariaTabla() + SQL_PARAM;
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(obtenerSelectSQL());

        ps.setInt(1, (int) id);
        ResultSet rs = ps.executeQuery();
        T e = null;
        if (rs.next()) {
            getMappingResulsets(rs);
        }
        cerrarJDBCObjects(con, ps, rs);
        return e;
    }

    //metodo para insertar datos
    public void insertarDato(T entity) throws ClassNotFoundException, SQLException {

        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(obtenerInsertSQL());
        getMappingParamsToInsert(ps, entity);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para eliminar un dato de la base
    public void eliminarDato(Object id) throws ClassNotFoundException, SQLException {
        String sql = SQL_DELETE + SQL_WHERE + obtenerLLavePrimariaTabla() + SQL_PARAM;
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, (int) id);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para actualizar un dato en la base de datos
    public void actualizarDatos(T entity) throws ClassNotFoundException, SQLException { // *** no terminado ***
        String sql = SQL_UPDATE + "update employees set birth_date = ?, first_name = ?, last_name = ?, gender = ?, hire_date = ?"
                + "where emp_no = ?";
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        setMappingUpdateStatement(ps, entity);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para obtener el query select
    protected String obtenerSelectSQL() {
        String sql = SQL_SELECT;
        sql = sql.replace("[TABLA]", obtenerNombreTabla()).replace("[CAMPOS]", Arrays.toString(obtenerColumnas())).replace("[", "").replace("]", "");
        System.out.println(sql); //valores de prueba
        return sql;
    }

    //metodo para obtener le query insert
    protected String obtenerInsertSQL() {
        String sql = SQL_INSERT;
        sql = sql.replace("[TABLA]", obtenerNombreTabla());
        String sqlColumnsIndicator = "";
        for(int i = 0; i < obtenerColumnas().length; i++) {
            sqlColumnsIndicator += "?,";
        }
        sqlColumnsIndicator = sqlColumnsIndicator.substring(0, sqlColumnsIndicator.length() - 1);
        sql = sql.replace("[CAMPOS]", sqlColumnsIndicator);
        System.out.println(sql);
        return sql;
    }

    //metodo para obtener el query update
    protected String getUpdateSQL() { // **** no terminado ******
        String sql = SQL_UPDATE;
        //String sql = "Select emp_no,birth_date,first_name,last_name,gender,hire_date from employees";
        sql = sql.replace("[TABLA]", obtenerNombreTabla());
        String sqlColumnsIndicator = "";
        for (int i = 0; i < obtenerColumnas().length; i++) {
            sqlColumnsIndicator += "?,";
        }

        sqlColumnsIndicator = sqlColumnsIndicator.substring(0, sqlColumnsIndicator.length() - 2);
        System.out.println(sql);
        return sql;
    }

    //metodos para obtener el nombre de la tabla
    protected abstract String obtenerNombreTabla();

    //metodo para obtener las columnas de la tabla
    protected abstract String[] obtenerColumnas();

    //metodo para obtener la id de la tabla
    protected abstract String obtenerLLavePrimariaTabla();

    //metodos abstractos
    protected abstract T getMappingResulsets(ResultSet rs) throws SQLException;

    protected abstract void getMappingParamsToInsert(PreparedStatement ps, T entity) throws SQLException;

    protected abstract void setMappingUpdateStatement(PreparedStatement ps, T entity) throws SQLException;

}
