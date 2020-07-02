package com.bitlab.conexiones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HiloAntiendeClientes extends Thread {

    private static Logger log = Logger.getLogger(HiloAntiendeClientes.class.getName());
    private BufferedReader br;
    private BufferedWriter bw;
    private Socket socket;

    public HiloAntiendeClientes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        
        try {
            br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //Me da la ip a la cual el socket esta conectado
            String laIP = socket.getInetAddress().getHostAddress();
            System.out.println(laIP + ": se ha conectado...");

            while (true) {
                log.info("Cliente entra al menu principal");
                bw.write("1. Actualización de datos del empleado\n2. Desactivación de empleados por despido\n3. Contratación de empleados\n4. Salir");
                bw.newLine();
                int opcion = 0;
                String linea;
                do {
                    bw.write("---");
                    bw.newLine();
                    bw.flush();
                    linea = br.readLine(); //Lee lo que introduce el usuario
                    log.info("Esperando respuesta del usuario");
                    opcion = Integer.parseInt(linea);
                    if (opcion < 1 || opcion > 4) { //Si el usuario ingresa una opcion que no esta en el menu vuelve a solicitar ingresar opcion
                        log.info("Usuario selecciono una opcion no existente");
                        bw.write("Elige una opcion correcta.");
                        bw.newLine();
                        bw.flush();
                    } else if (opcion == 4) { //Si ingresa la opcion 4 el usuario se desconectara
                        System.out.println(laIP + ": se ha desconectado...");
                        log.info("Usuario desconectado del sistema");
                        return;
                    }
                } while (opcion < 1 || opcion > 4); //Mientras el usuario ingrese opcion del 1 al 4 se estara imprimiendo el menu principal

                log.info("Entrando al switch con las opciones principales"); //Si ingresa una opcion valida, se le llevara a la opcion deseada
                switch (opcion) {
                    case 1:
                            bw.write("Actualizando empleado prueba.");
                            bw.newLine();
                            bw.flush();
                        break;

                    case 2:
                        bw.write("de empleados por despido. ");
                        bw.newLine();
                        bw.write("---");
                        bw.newLine();
                        bw.flush();
                        break;

                    case 3:
                        
                            bw.write("Contratando empleados prueba.");
                            bw.newLine();
                            bw.flush();
                        break;

                    case 4:
                        bw.write("Desconectado.");
                        System.exit(0);
                        break;
                    default: bw.write("Opcion invalida.");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
