package com.bitlab.utilidades;

import com.bitlab.conexiones.HiloAntiendeClientes;
import com.bitlab.dao.ConexionDAO;
import com.bitlab.dao.DepartamentoDAO;
import com.bitlab.dao.EmpleadoDAO;
import com.bitlab.dao.RolDAO;
import com.bitlab.entidades.Departamento;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Rol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class PedidoDatos {

    private static Logger log = Logger.getLogger(PedidoDatos.class.getName());
    EmpleadoDAO dao = new EmpleadoDAO();
   
        protected String[] obtenerCampos() {
            String [] columnas = {"1. Nombre", "2. Apellido", "3. Genero", "4. DUI", "5. Fecha de Nacimiento",
    "6. Correo Electronico", "7. Direccion", "8. Telefono", "9. NIF", "10. Comision", "11. Profesion", "12 Estado", "13. Rol", "14. Departamento"};
        return columnas;
        }

    //Metodo que recibe un BufferedWriter, BufferedReader, una lista de datos que se le solicitaran al usuario y
    // un arreglo con los tipos de datos que se espera recibir
    //Este metodo se usa para agregar un nuevo empleado
    public static List solicitarDatos(BufferedWriter bw, BufferedReader br, String[] ingresoDatos, String[] tipoDato) throws IOException, ClassNotFoundException, SQLException {
        List lista = new ArrayList();
        RolDAO rolDao = new RolDAO();
        DepartamentoDAO depDao = new DepartamentoDAO();
        for (int i = 0; i < ingresoDatos.length; i++) {
            bw.write(ingresoDatos[i]);  //Aqui pedira al usuario el ingreso de algun dato
            PedidoDatos.flush(bw);
            if(ingresoDatos[i].equals("Ingrese rol del empleado")){
                List<Rol> roles = rolDao.obtenerDatos();
                for(Rol rol : roles){
                    bw.write(rol.getIdRol() + ". " +rol.getNombreRol());
                    PedidoDatos.flush(bw);
                }
            }else if(ingresoDatos[i].equals("Ingrese el departamento del empleado")){
                List<Departamento> departamentos = depDao.obtenerDatos();
                for(Departamento dep : departamentos){
                    bw.write(dep.getIdDepartamento() + ". " + dep.getNombre());
                    PedidoDatos.flush(bw);
                }
            }
            String respuesta = br.readLine(); //Aqui lee el dato ingresado por el usuario
            switch (tipoDato[i]) { //Dependiendo del tipo de dato se castea a su correspondiente tipo
                case "int":
                    int entero = Integer.parseInt(respuesta);
                    lista.add(entero);
                    break;
                case "double":
                    Double doble = Double.parseDouble(respuesta);
                    lista.add(doble);
                    break;
                case "boolean":
                    Boolean boleano = true;
                    lista.add(boleano);
                    break;
                case "timestamp":
                    Date d = new Date(respuesta);
                    Timestamp timestamp = new Timestamp(d.getTime());
                    lista.add(timestamp);
                    break;
                case "string":
                    lista.add(respuesta); //Si es string no se castea, ya que readLine devuelve un string
            }
        }
        return lista; //Se devuelve la lista de datos ingresados por el usuario
    }

    /*Metodo que vacía el flujo de salida */
    public static void flush(BufferedWriter bw) throws IOException {
        bw.newLine();
        bw.flush();
    }

    //Metodo para actualizar un empleado
    public void menuActualiza(BufferedWriter bw, BufferedReader br, int id) throws ClassNotFoundException, SQLException {
        try {
            bw.write("Que dato desea actualizar del empleado");
            PedidoDatos.flush(bw);
            for(String str : obtenerCampos()){
                bw.write(str);
                PedidoDatos.flush(bw);
            }
            String numCampo = br.readLine();
            short num = Short.parseShort(numCampo);
            bw.write("Ingrese el nuevo dato");
            PedidoDatos.flush(bw);
            String nuevoDato = br.readLine();
            /* Obtengo todos los datos del empleado seleccionado*/
            Empleado emp = dao.obtenerEmpleado(id);
            System.out.println(emp.toString());
            dao.seteaDato(num, emp, nuevoDato);
            dao.actualizarDatos(emp);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(PedidoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo para reutilizar en opciones de admin
    public void opcionesAdmin(String tabla, BufferedWriter bw, BufferedReader br, Object dao) throws IOException{
        bw.write("Que " + tabla + " desea gestionar?");
        
    }

}
