
package com.bitlab.utilidades;

import com.bitlab.propiedades.ConfigProperties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class ConexionDB {
    private static Logger log = Logger.getLogger(ConexionDB.class.getName());
    public Connection con = null;
    EncriptacionTexto encript = new EncriptacionTexto();
    Properties prop = new Properties();
    
    
    public Connection crearConexionDB(){
        try { /*Se carga el archivo conexion.properties que contiene los datos de conexion a base de datos */
            prop.load(ConfigProperties.getResourceAsInputStream("conexion.properties"));
            log.info("Enviando peticion a la base de datos");            
            con = DriverManager.getConnection(encript.getTextoDesencriptado(prop.getProperty("dbUr")), encript.getTextoDesencriptado(prop.getProperty("dbNam")), encript.getTextoDesencriptado(prop.getProperty("dbPss")));
            log.info("Se ha realizado la conexion con la base de datos");
        } catch (IOException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
        /*Este metodo devuelve un objeto de tipo Connection, la conexion queda abierta por lo que hay que cerrarla en la clase donde se le llame */
    }
}
