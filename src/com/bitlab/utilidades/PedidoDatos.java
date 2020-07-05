
package com.bitlab.utilidades;

import com.bitlab.conexiones.HiloAntiendeClientes;
import com.bitlab.dao.EmpleadoDAO;
import com.bitlab.entidades.Empleado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
    //Metodo que recibe un BufferedWriter, BufferedReader, una lista de datos que se le solicitaran al usuario y
    // un arreglo con los tipos de datos que se espera recibir
    public static List solicitarDatos(BufferedWriter bw, BufferedReader br, String[] ingresoDatos, String[] tipoDato) throws IOException{
        List lista = new ArrayList();
        for(int i=0; i<ingresoDatos.length; i++){
            bw.write(ingresoDatos[i]);  //Aqui pedira al usuario el ingreso de algun dato
            PedidoDatos.flush(bw);
            String respuesta = br.readLine(); //Aqui lee el dato ingresado por el usuario
            switch(tipoDato[i]){ //Dependiendo del tipo de dato se castea a su correspondiente tipo
                case "int": int entero = Integer.parseInt(respuesta);
                            lista.add(entero);
                break;
                case "double": Double doble = Double.parseDouble(respuesta);
                             lista.add(doble);
                break;
                case "boolean": Boolean boleano = Boolean.parseBoolean(respuesta);
                             lista.add(boleano);
                break;
                case "timestamp": Date d = new Date(respuesta);
                                  Timestamp timestamp = new Timestamp(d.getTime());
                                  lista.add(timestamp);
                break;
                case "string": lista.add(respuesta); //Si es string no se castea, ya que readLine devuelve un string
            }
        }
        return lista; //Se devuelve la lista de datos ingresados por el usuario
    }
    
    
    /*Metodo que vacía el flujo de salida */
    private static void flush(BufferedWriter bw) throws IOException{
        bw.newLine(); 
        bw.flush();
    }

}
