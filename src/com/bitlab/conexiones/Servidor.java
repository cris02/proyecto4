
package com.bitlab.conexiones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class Servidor {
    private static final int PUERTO = 8888;
    private static Logger log = Logger.getLogger(Servidor.class.getName());
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO); //Creo un nuevo socket con el puerto especificado
            while(true){
                log.info("Esperando conexion de algun cliente");
                Socket socket = serverSocket.accept(); //El socket esta pendiente de alguna conexion y cuando entra la acepta
                HiloAntiendeClientes hilo = new HiloAntiendeClientes(socket); //Creo un nuevo hilo con la clase que hereda de Thread
                hilo.start(); //Inicia el nuevo hilo
                System.out.println("Cliente " +socket.getInetAddress().getHostAddress() + " conectado exitosamente");
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
