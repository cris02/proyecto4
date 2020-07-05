package com.bitlab.conexiones;

import com.bitlab.dao.ConexionDAO;
import com.bitlab.dao.DepartamentoDAO;
import com.bitlab.dao.EmpleadoDAO;
import com.bitlab.dao.UsuarioDAO;
import com.bitlab.entidades.Departamento;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Usuario;
import com.bitlab.propiedades.ConfigProperties;
import com.bitlab.utilidades.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HiloAntiendeClientes extends Thread {

    private static Logger log = Logger.getLogger(HiloAntiendeClientes.class.getName());
    EnvioCorreo envio = new EnvioCorreo();
    Properties prop = new Properties();
    private BufferedReader br;
    private BufferedWriter bw;
    private Socket socket;

    public HiloAntiendeClientes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //Me da la ip a la cual el socket esta conectado
            String laIP = socket.getInetAddress().getHostAddress();
            System.out.println(laIP + ": se ha conectado...");

            /* Empieza prueba */
            UsuarioDAO daoUsuario = new UsuarioDAO();
//            Usuario us = new Usuario();
//            us.setNombreUsuario("henjo");
//            us.setContrasena("prueba");
//            us.setCorreo("henry.callejas@gmail.com");
//            us.setIdUsuario(1);

            bw.write("Ingrese su usuario");
            bw.newLine();
            bw.flush();
            String usuario = br.readLine();

            bw.write("Ingrese su contrasena");
            bw.newLine();
            bw.flush();
            String contra = br.readLine();
            
            Usuario us = daoUsuario.verificarUsuario(usuario, contra);

            if (usuario.equals(us.getNombreUsuario()) && contra.equals(us.getContrasena())) {
                try {
                    prop.load(ConfigProperties.getResourceAsInputStream("config.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bw.write("FUNCIONA!!!!\n");
                if (us.getIdUsuario() == 2) {
                    bw.write( us.getNombreUsuario()+ " Usted es un usuario administrador\n");
//                    int codigo = envio.enviarCorreo(prop, us, bw);
//                    bw.write("Ingrese el codigo enviado a" +us.getCorreo());
//                    PedidoDatos.flush(bw);
//                    String codigoIngresado = br.readLine();
//                    if(codigo == Integer.parseInt(codigoIngresado)){
                        menuAdmin(br, bw);
//                    }else{
//                        bw.write("Codigo ingresado es invalido");
//                    }
                    
                } else if(us.getIdUsuario() == 3) {
                    bw.write("Usted es un usuario de RRHH\n");
//                    int codigo = envio.enviarCorreo(prop, us, bw);
//                    bw.write("Ingrese el codigo enviado a" +us.getCorreo());
//                    PedidoDatos.flush(bw);
//                    String codigoIngresado = br.readLine();
//                    if(codigo == Integer.parseInt(codigoIngresado)){
                        menuRRHH(br, bw);
//                    }else{
//                        bw.write("Codigo ingresado es invalido");
//                    }
                }
            } else {
                bw.write("Credenciales invalidas, intente de nuevo, por seguridad se desconectara del sistema");
            }

            /* *Termina prueba */
        } catch (IOException ex) {
            Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    public void consultarUsuario(Usuario us) {

    }

    public void menuAdmin(BufferedReader br, BufferedWriter bw) throws IOException, ClassNotFoundException, SQLException {
        DepartamentoDAO daoDept = new DepartamentoDAO();
        while (true) {
            log.info("Admin entra al menu principal");
            bw.write("1. Gestión de departamentos\n2. Gestión de estados de empleados\n3. Gestión de usuarios\n4. Salir");
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
//                        System.out.println(laIP + ": se ha desconectado...");
                    log.info("Usuario desconectado del sistema");
                    return;
                }
            } while (opcion < 1 || opcion > 4); //Mientras el usuario ingrese opcion del 1 al 4 se estara imprimiendo el menu principal

            log.info("Entrando al switch con las opciones principales"); //Si ingresa una opcion valida, se le llevara a la opcion deseada
            switch (opcion) {
                case 1:
                    bw.write("Gestión de departamentos prueba.");
                    PedidoDatos.flush(bw);
                    bw.write("Que departamento desea gestionar");
                    PedidoDatos.flush(bw);
                    List <Departamento> listaDept= daoDept.obtenerDatos();
                    for(Departamento dep : listaDept){
                        bw.write(dep.getIdDepartamento() + ". " +dep.getNombre());
                        PedidoDatos.flush(bw);
                    }
                    bw.write("Funcionalidad aun no completada");
                    PedidoDatos.flush(bw);
                    break;

                case 2:
                    bw.write("Gestión de estados de empleados prueba. ");
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
                default:
                    bw.write("Opcion invalida.");
            }
        }
    }

    public void menuRRHH(BufferedReader br, BufferedWriter bw) throws IOException {
        EmpleadoDAO dao = null;
        while (true) {
            try {
                log.info("Cliente entra al menu principal");
                bw.write("1. Contratación de empleados\n2. Actualización de datos del empleado\n3. Desactivación de empleados por despido\n4. Salir");
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
//                    System.out.println(laIP + ": se ha desconectado...");
                        log.info("Usuario desconectado del sistema");
                        return;
                    }
                } while (opcion < 1 || opcion > 4); //Mientras el usuario ingrese opcion del 1 al 4 se estara imprimiendo el menu principal

                log.info("Entrando al switch con las opciones principales"); //Si ingresa una opcion valida, se le llevara a la opcion deseada
                switch (opcion) {
                    case 1:
                        List listaDatos;
                        String datosSolicitar[] = {"Ingrese el ID del empleado:", "Ingrese los nombre del empleado:", "Ingrese los apellidos del empleado", "Ingrese Genero",
                            "Ingrese el documento de Identidad (DUI)", "Ingrese fecha de nacimiento", "Ingrese correo electronico del empleado", "Ingrese la direccion del empleado",
                            "Ingrese telefono del empleado", "Ingrese NIF", "Ingrese comision", "Ingrese profesion del empleado", "Ingrese estado de empleado", "Ingrese rol del empleado", "Ingrese el departamento del empleado"};
                        String tiposDatos[] = {"int", "string", "string", "string", "string", "timestamp", "string", "string", "string", "string", "string", "string", "boolean", "int", "int"};
                        listaDatos = PedidoDatos.solicitarDatos(bw, br, datosSolicitar, tiposDatos);

                        Empleado emp1 = new Empleado(listaDatos.toArray());

//                        Empleado emp = new Empleado(ID, nombre, apellido, genero, DUI, timestamp, correo, direccion, telefono, NIF, com, profesion, estado, rol, departamento);
                        dao = new EmpleadoDAO();
                        dao.insertarDato(emp1);

                        break;

                    case 2:
                        PedidoDatos pedirDato = new PedidoDatos();
                        bw.write("Actualización de datos del empleado. ");
                        bw.newLine();
                        bw.flush();
                        bw.write("Ingrese el ID del usuario que desea actualizar datos ");
                        bw.newLine();
                        bw.flush();
                        List listaEmpleados;
                        dao = new EmpleadoDAO();
                        listaEmpleados = dao.obtenerEmpleadosActivos();
                        dao.mostrarEmpleadosActivos(bw, listaEmpleados);
                        String datoId = br.readLine();
                        int id = Integer.parseInt(datoId);
                        //ENVIAR EL ID 
                        pedirDato.menuActualiza(bw, br, id);
//                        List lista;
//                        String datosaSolicitar[] = {"Ingrese el ID del empleado:", "Ingrese los nombre del empleado:", "Ingrese los apellidos del empleado", "Ingrese Genero",
//                            "Ingrese el documento de Identidad (DUI)", "Ingrese fecha de nacimiento", "Ingrese correo electronico del empleado", "Ingrese la direccion del empleado",
//                            "Ingrese telefono del empleado", "Ingrese NIF", "Ingrese comision", "Ingrese profesion del empleado", "Ingrese estado de empleado", "Ingrese rol del empleado", "Ingrese el departamento del empleado"};
//                        String tiposDeDatos[] = {"int", "string", "string", "string", "string", "timestamp", "string", "string", "string", "string", "string", "string", "boolean", "int", "int"};
                        break;

                    case 3:

                        bw.write("Desactivación de empleados por despido");
                        PedidoDatos.flush(bw);
                        bw.write("Que empleado desea retirar de la base de datos de empleados activos");
                        PedidoDatos.flush(bw);
                        
                        List listaEmp;
                        dao = new EmpleadoDAO();
                        listaEmp = dao.obtenerEmpleadosActivos();
                        Empleado emp = dao.mostrarEmpleadosActivos(bw, listaEmp);
                        String idDesactivar = br.readLine();
                        dao.desactivaEmpleado(Short.parseShort(idDesactivar), emp);
                        break;

                    case 4:
                        bw.write("Desconectado.");
                        System.exit(0);
                        break;
                    default:
                        bw.write("Opcion invalida.");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
