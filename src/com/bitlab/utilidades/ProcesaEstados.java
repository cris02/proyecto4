
package com.bitlab.utilidades;

import com.bitlab.dao.EmpleadoDAO;
import com.bitlab.entidades.Empleado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author henry
 */
public class ProcesaEstados {
    public final short LIMITE_DATOS = 1000;
    EmpleadoDAO daoEmp = new EmpleadoDAO();
    
    public void obtenerEmpleadosActivosEInactivos(BufferedWriter bw) throws ClassNotFoundException, SQLException, IOException{
        List<Empleado> listaEmpleados = daoEmp.obtenerDatos(LIMITE_DATOS);
        for(Empleado emp : listaEmpleados){
            bw.write("ID: :"+emp.getIdEmpleado() + " Nombre: "+emp.getNombres() + " " +emp.getApellidos() + "Estado: " +emp.isEstado());
        }
    }
    
    public void activarDesactivarEmpleado(Empleado entity, String activoInactivo) throws ClassNotFoundException, SQLException{
        if(activoInactivo.equals("activo")) entity.setEstado(true);
            else if(activoInactivo.equals("inactivo")) entity.setEstado(false);
        daoEmp.insertarDato(entity);
    }
}
