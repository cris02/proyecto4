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
import java.util.List;

/**
 *
 * @author Aguilar
 */
public abstract class ConexionDAO<T> {

    public final short LIMIT_RECORDS = 100;
    ConexionDB conexionDB = new ConexionDB();

    //metodo para obtener la conecion
    protected Connection obtenerConexion() throws ClassNotFoundException, SQLException {
        return conexionDB.crearConexionDB();
    }
    
    
    /*
        Metodos para cerar las conexiones existentes
    */   
    
    //cerrando la conexion
    protected void cerarJDBCObjects(Connection con) throws SQLException {
        conexionDB.cerrarConexion(con);
    }

    protected void cerarJDBCObjects(Connection con, Statement st) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion y Statement
    protected void cerarJDBCObjects(Connection con, Statement st, ResultSet rs) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion y PreparedStatement
    protected void cerarJDBCObjects(Connection con, PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }

        conexionDB.cerrarConexion(con);
    }

    //cerrando conexion, PreparedStatement y ResultSet
    protected void cerarJDBCObjects(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        conexionDB.cerrarConexion(con);
    }

    //metodo para manipular datos
    public abstract List<T> getAllDate() throws ClassNotFoundException, SQLException;
    
    public abstract T getByIDData(Object id) throws ClassNotFoundException, SQLException;

    public abstract void insertDate(T entity) throws ClassNotFoundException, SQLException;

    public abstract void updateDate(T entity) throws ClassNotFoundException, SQLException;

    public abstract void deleteDate(Object id) throws ClassNotFoundException, SQLException;

}
