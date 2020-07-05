/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entidades.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
        rs.getString("EMP_TELEFONO"),
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
        ps.setInt(16, entity.getIdEmpleado());
    }
    
    public void seteaDato(short num, Empleado entity, String datoActualizar){
        if(num == 1) entity.setNombres(datoActualizar);
        else if(num == 2) entity.setApellidos(datoActualizar);
        else if(num == 3) entity.setGenero(datoActualizar);
        else if(num ==4) entity.setDocumentoIdentidad(datoActualizar);
        else if(num == 5) entity.setFechaNacimiento((Timestamp.valueOf(datoActualizar)));
        else if(num == 6) entity.setCorreo(datoActualizar);
        else if(num ==7) entity.setDireccion(datoActualizar);
        else if(num ==8) entity.setTelefono(datoActualizar);
        else if(num ==9) entity.setNif(datoActualizar);
        else if(num ==10) entity.setComision(Double.parseDouble(datoActualizar));
        else if(num == 11) entity.setProfesion(datoActualizar);
        else if(num ==12) entity.setEstado(Boolean.parseBoolean(datoActualizar));
        else if(num ==13) entity.setIdRol(Integer.parseInt(datoActualizar));
        else if(num == 14) entity.setIdDepartamento(Integer.parseInt(datoActualizar));
    }

    @Override
    public void desactivaEmpleado(short id, Empleado entity) throws ClassNotFoundException, SQLException {
        super.desactivaEmpleado(id, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> obtenerDatos() throws ClassNotFoundException, SQLException {
        return super.obtenerDatos(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarDato(Empleado entity) throws ClassNotFoundException, SQLException {
        super.insertarDato(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    //metodo para obtener los datos por medio de un parametro que indica cuantos registros deseo
    public List<Empleado> obtenerEmpleadosActivos() throws ClassNotFoundException, SQLException {
        Connection con = obtenerConexion();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT EMP_ID_PK, EMP_NOMBRES, EMP_APELLIDOS FROM BIT_EMPLEADO WHERE EMP_ESTADO=1"); //ejecutar el query

        //crear una lista de objetos
        List<Empleado> empleados = new ArrayList<>();
        while (rs.next()) {
            empleados.add(new Empleado(
        rs.getInt("EMP_ID_PK"),
        rs.getString("EMP_NOMBRES"),
        rs.getString("EMP_APELLIDOS"))); 
        }//agregar los datos a la lista
        cerrarJDBCObjects(con, st, rs);
        return empleados;
    }
    
    
}
