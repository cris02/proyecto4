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
import java.util.logging.Logger;

/**
 *
 * @author Aguilar
 */
public abstract class ConexionDAO<T> {

    public final short LIMITE_DATOS = 1000;
    public final String SQL_SELECT = "SELECT [COLUMNS] FROM [TABLE]";
    public final String SQL_INSERT = "INSERT INTO [TABLE] ([COLUMNS]) VALUES ([COLUMNS_INDEX])";
    public final String SQL_WHERE = " WHERE ";
    public final String SQL_DELETE = "DELETE FROM [TABLE] WHERE [CONDITIONS]";
    public final String SQL_UPDATE = "UPDATE [TABLE] SET [COLUMNS] WHERE [CONDITIONS]";
    public final String SQL_PARAM = " = ?";
    private final String TABLA = "[TABLE]";
    private final String COLUMNAS = "[COLUMNS]";
    private final String COLUMNA_INDEX = "[COLUMNS_INDEX]";
    private final String CONDICION = "[CONDITIONS]";
    
    //variable para los loggers
    private static Logger log = Logger.getLogger(ConexionDAO.class.getSimpleName());

    //metodo para obtener la conecion
    protected Connection obtenerConexion() throws ClassNotFoundException, SQLException {
        return ConexionDB.getInstance().crearConexionDB();
    }

    /*
        Metodos para cerar las conexiones existentes
     */
    
    //cerrando la conexion
    protected void cerrarJDBCObjects(Connection con) throws SQLException {
        ConexionDB.cerrarConexion(con);
    }

    protected void cerrarJDBCObjects(Connection con, Statement st) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        ConexionDB.cerrarConexion(con);
    }

    //cerrando conexion y Statement
    protected void cerrarJDBCObjects(Connection con, Statement st, ResultSet rs) throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        ConexionDB.cerrarConexion(con);
    }

    //cerrando conexion y PreparedStatement
    protected void cerrarJDBCObjects(Connection con, PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }

        ConexionDB.cerrarConexion(con);
    }

    //cerrando conexion, PreparedStatement y ResultSet
    protected void cerrarJDBCObjects(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        ConexionDB.cerrarConexion(con);
    }

    /*
        metodo para manipular datos
     */
    
    //metodo para obtener todos los datos por defecto en este caso 1000
    public List<T> obtenerDatos() throws ClassNotFoundException, SQLException {
        return obtenerDatos(LIMITE_DATOS);
    }

    //metodo para obtener los datos por medio de un parametro que indica cuantos registros deseo
    public List<T> obtenerDatos(int cantidadDatos) throws ClassNotFoundException, SQLException {
        Connection con = obtenerConexion();
        Statement st = con.createStatement();
        st.setMaxRows(cantidadDatos);
        ResultSet rs = st.executeQuery(obtenerSelectSQL()); //ejecutar el query

        //crear una lista de objetos
        List<T> objects = new ArrayList<>();
        while (rs.next()) {
            objects.add(getMappingResulsets(rs)); //agregar los datos a la lista
        }
        cerrarJDBCObjects(con, st, rs);
        return objects;
    }

    //metodo para obtener un dato por id
    public T obtenerDatoID(Object id) throws ClassNotFoundException, SQLException {
        String sql = obtenerSelectSQL() + SQL_WHERE + obtenerLLavePrimariaTabla() + SQL_PARAM;
        log.info("Genrando el Query filtrado " + sql);
        System.out.println(sql); //datos para ver la construccion del sql
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setObject(1, id);
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
    
    public void insertarDatoSinID(T entity) throws ClassNotFoundException, SQLException {
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement("INSERT INTO [TABLE] ([COLUMNS]) VALUES ([COLUMNS_INDEX])");
        getMappingParamsToInsert(ps, entity);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para eliminar un dato de la base
    public void eliminarDato(Object id) throws ClassNotFoundException, SQLException {
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(obtenerDeleteSQL());
        ps.setInt(1, (int) id);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para actualizar un dato en la base de datos
    public void actualizarDatos(T entity) throws ClassNotFoundException, SQLException {
        log.info("Actualizando datos en la base de datos");
        Connection con = obtenerConexion();
        PreparedStatement ps = con.prepareStatement(obtenerUpdateSQL());
        setMappingUpdateStatement(ps, entity);
        ps.execute();
        cerrarJDBCObjects(con, ps);
    }

    //metodo para obtener el query select
    protected String obtenerSelectSQL() {
        String sql = SQL_SELECT;
        sql = sql.replace(TABLA, obtenerNombreTabla()).replace(COLUMNAS, Arrays.toString(obtenerColumnas()))
                .replace("[", "").replace("]", "");
        log.info("Se genera el Query Select " + sql);
        System.out.println(sql); //valores de prueba
        return sql;
    }
    
    //metodo para obtener el query delete
    protected String obtenerDeleteSQL() {
        String sql = SQL_DELETE.replace(TABLA, obtenerNombreTabla()).replace(CONDICION, obtenerLLavePrimariaTabla() + SQL_PARAM);
        System.out.println(sql); //prueba de la query
        log.info("Se genera el Query delete " + sql);
        return sql;
    }

    //metodo para obtener le query insert
    protected String obtenerInsertSQL() {
        String sql = SQL_INSERT;
        String sqlColumnsIndicator = "";
        for (int i = 0; i < obtenerColumnas().length; i++) 
            sqlColumnsIndicator += "?,";
        
        sqlColumnsIndicator = sqlColumnsIndicator.substring(0, sqlColumnsIndicator.length() - 1);
        sql = sql.replace(COLUMNA_INDEX, sqlColumnsIndicator);
        
        sql = sql.replace(TABLA, obtenerNombreTabla())
                .replace(COLUMNAS, Arrays.toString(obtenerColumnas()))
                .replace("[", "").replace("]", "");
        System.out.println(sql); // prueba de como se arma la sentenccia sql
        log.info("Se genera el query insert " + sql);
        return sql;
    }
    
    //metodo para obtener le query insert
    protected String obtenerInsertSQLSinID() {
        String sql = "INSERT INTO [TABLE] ([COLUMNS]) VALUES ([COLUMNS_INDEX])";
        String sqlColumnsIndicator = "";
        for (int i = 0; i < obtenerColumnas().length; i++) 
            sqlColumnsIndicator += "?,";
        
        sqlColumnsIndicator = sqlColumnsIndicator.substring(0, sqlColumnsIndicator.length() - 1);
        sql = sql.replace(COLUMNA_INDEX, sqlColumnsIndicator);
        
        sql = sql.replace(TABLA, obtenerNombreTabla())
                .replace(COLUMNAS, Arrays.toString(obtenerColumnas()))
                .replace("[", "").replace("]", "");
        System.out.println(sql); // prueba de como se arma la sentenccia sql
        log.info("Se genera el query insert " + sql);
        return sql;
    }
    //metodo para obtener el query update
    protected String obtenerUpdateSQL() {
        String sql = SQL_UPDATE.replace(CONDICION, obtenerLLavePrimariaTabla() + SQL_PARAM);
        sql = sql.replace(TABLA, obtenerNombreTabla());

        StringBuilder strIndicator = new StringBuilder();
        for (String str : obtenerColumnas()) 
            strIndicator.append(str).append("=?,");
                
        sql = sql.replace(COLUMNAS, strIndicator.toString().substring(0, strIndicator.toString().length()-1));
        System.out.println(sql); //prueba del query
        log.info("Se genera el Query update " + sql);
        return sql;
    }

    //metodos para obtener el nombre de la tabla
    protected abstract String obtenerNombreTabla();

    //metodo para obtener las columnas de la tabla
    protected abstract String[] obtenerColumnas();

    //metodo para obtener la id de la tabla
    protected abstract String obtenerLLavePrimariaTabla();

    /*
        metodos abstractos
     */
    //metodo para mapear los resultados de una consulta
    protected abstract T getMappingResulsets(ResultSet rs) throws SQLException;

    //metodo para mapear los resultados de un insert
    protected abstract void getMappingParamsToInsert(PreparedStatement ps, T entity) throws SQLException;

    //metodo para mapear los resultados de un update
    protected abstract void setMappingUpdateStatement(PreparedStatement ps, T entity) throws SQLException;

}
