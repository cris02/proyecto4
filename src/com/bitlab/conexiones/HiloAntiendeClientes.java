package com.bitlab.conexiones;

import com.bitlab.dao.DepartamentoDAO;
import com.bitlab.dao.DetallePlanillaDAO;
import com.bitlab.dao.EmpleadoDAO;
import com.bitlab.dao.UsuarioDAO;
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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HiloAntiendeClientes extends Thread {
//Rama actualizacion

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
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 927680);

            //Me da la ip a la cual el socket esta conectado
            String laIP = socket.getInetAddress().getHostAddress();

            UsuarioDAO daoUsuario = new UsuarioDAO();
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
                if (us.getIdUsuario() == 2) {
                    bw.write(us.getNombreUsuario() + " Usted es un usuario administrador");
                    PedidoDatos.flush(bw);
                    int codigo = envio.enviarCorreo(prop, us, bw);
                    bw.write("Ingrese el codigo enviado a " +us.getCorreo());
                    PedidoDatos.flush(bw);
                    String codigoIngresado = br.readLine();
                    if(codigo == Integer.parseInt(codigoIngresado)){
                    menuAdmin(br, bw);
                    }else{
                        bw.write("Codigo ingresado es invalido");
                    }

                } else if (us.getIdUsuario() == 3) {
                    bw.write("Usted es un usuario de RRHH\n");
                    PedidoDatos.flush(bw);
                    int codigo = envio.enviarCorreo(prop, us, bw);
                    bw.write("Ingrese el codigo enviado a " + us.getCorreo());
                    PedidoDatos.flush(bw);
                    String codigoIngresado = br.readLine();
                    if (codigo == Integer.parseInt(codigoIngresado)) {
                        menuRRHH(br, bw);
                    } else {
                        bw.write("Codigo ingresado es invalido");
                    }
                }
                log.info("**** CLIENTE con IP: " + laIP + ": se ha conectado de forma exitosa****");
            } else {
                bw.write("Credenciales invalidas, intente de nuevo, por seguridad se desconectara del sistema");
                PedidoDatos.flush(bw);
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
        while (true) {
            log.info("\tAdmin entra al menu principal");
            bw.newLine();
            bw.write("********Menu de Opciones**********");
            PedidoDatos.flush(bw);
            bw.write("1. Gestión de departamentos");
            PedidoDatos.flush(bw);
            bw.write("2. Gestión de estados de empleados");
            PedidoDatos.flush(bw);
            bw.write("3. Gestión de usuarios");
            PedidoDatos.flush(bw);
            bw.write("4. Gestión de Roles");
            PedidoDatos.flush(bw);
            bw.write("5. Salir");
            PedidoDatos.flush(bw);
            bw.write("Seleccione el numero con la opcion que desea");
            PedidoDatos.flush(bw);
            int opcion = 0;
            String linea;
            do {
                bw.write("---");
                bw.newLine();
                bw.flush();
                linea = br.readLine(); //Lee lo que introduce el usuario
                log.info("Esperando respuesta del usuario");
                opcion = Integer.parseInt(linea);
                if (opcion < 1 || opcion > 5) { //Si el usuario ingresa una opcion que no esta en el menu vuelve a solicitar ingresar opcion
                    log.info("Usuario selecciono una opcion no existente");
                    bw.write("Elige una opcion correcta.");
                    PedidoDatos.flush(bw);
                } else if (opcion == 5) { //Si ingresa la opcion 6 el usuario se desconectara
//                        System.out.println(laIP + ": se ha desconectado...");
                    log.info("Usuario desconectado del sistema");
                    return;
                }
            } while (opcion < 1 || opcion > 5); //Mientras el usuario ingrese opcion del 1 al 6 se estara imprimiendo el menu principal

            log.info("Entrando al switch con las opciones principales"); //Si ingresa una opcion valida, se le llevara a la opcion deseada
            switch (opcion) {
                case 1:
                    bw.write("\t*** Gestion de Departamentos *** ");
                    PedidoDatos.flush(bw);

                    ProcesaDepartamentos procesaDepts = new ProcesaDepartamentos();
                    boolean flagMenuDept = true; // bandera para ingresar la menu de usuarios
                    byte opcionMenuDept = 0; //variable para entrar al menu
                    while (flagMenuDept) {
                        do {
                            bw.write(procesaDepts.obtenerMenuDept()); //llamar al metodo para mostrar menu
                            PedidoDatos.flush(bw);
                            bw.write("Ingrese una Opcion Valida -> ");
                            PedidoDatos.flush(bw);
                            opcionMenuDept = Byte.parseByte(br.readLine()); // capturamos la opcion elegida por el usuario
                        } while (!((opcionMenuDept >= 1) && (opcionMenuDept <= 5)));

                        if (opcionMenuDept == 5) { // si la opcion es 5 cambiamos el estado de la bandera para retornar al menu principal
                            flagMenuDept = false;
                        }

                        procesaDepts.seleccionarOpcionMenu(opcionMenuDept, bw, flagMenuDept, br); //enviamos la opcion elegida 
                        PedidoDatos.flush(bw);

                    }
                    System.out.println("Saliendo del Menu Gestionar Rol ... ");
                    break;

                case 2:// Opcion para gestionar el estados de empleados
                    EmpleadoDAO empDao = new EmpleadoDAO();
                    bw.write("Gestión de estados de empleados");
                    bw.newLine();
                    byte bandera = 1;
                    while (bandera == 1) {
                        empDao.mostrarEmpleadosActivos(bw); //Obtengo una lista de empleados activos e inactivos
                        bw.write("Desea desactivar un empleado? Digite [1]. Si, [2]. No");
                        PedidoDatos.flush(bw);
                        bandera = Byte.parseByte(br.readLine());
                        if (bandera == 1) {
                            bw.write("Ingrese el [ID] del Empleado que desea inactivar:");
                            PedidoDatos.flush(bw);
                            byte id = Byte.parseByte(br.readLine());
                            Empleado empleado = empDao.obtenerEmpleado(id);
                            if (empleado != null) {
                                empleado.setEstado(false); //Seteo el estado a false para ponerlo inactivo
                                empDao.desactivaEmpleado(id);
                                bw.write("El Empleado inactivado es :" + empleado.getNombres() + " " + empleado.getApellidos());
                                PedidoDatos.flush(bw);
                                bw.write("Desea desactivar otro empleado? Digite [1]. Si, [2]. No");
                                PedidoDatos.flush(bw);
                                bandera = Byte.parseByte(br.readLine());
                            } else {
                                bw.write("No se pudo inactivar el Empleado");
                            }
                        }

                    }
                    break;

                case 3:

                    bw.write("\t*** Gestion de Usuarios *** ");
                    PedidoDatos.flush(bw);
                    ProcesarUsuarios procesarUsuarios = new ProcesarUsuarios();
                    boolean flagMenuUsuario = true; // bandera para ingresar la menu de usuarios
                    byte opcionMenuUser = 0; //variable para entrar al menu
                    while (flagMenuUsuario) {
                        do {
                            bw.write(procesarUsuarios.obtenerMenuUsuario()); //llamar al metodo para mostrar menu
                            PedidoDatos.flush(bw);
                            bw.write("Ingrese una Opcion Valida -> ");
                            PedidoDatos.flush(bw);
                            opcionMenuUser = Byte.parseByte(br.readLine()); // capturamos la opcion elegida por el usuario
                        } while (!((opcionMenuUser >= 1) && (opcionMenuUser <= 5)));

                        if (opcionMenuUser == 5) { // si la opcion es 5 cambiamos el estado de la bandera para retornar al menu principal
                            flagMenuUsuario = false;
                        }

                        procesarUsuarios.selecionarOpcionMenu(opcionMenuUser, bw, flagMenuUsuario, br); //enviamos la opcion elegida 
                        PedidoDatos.flush(bw);

                    }
                    System.out.println("Saliendo del Menu Gestionar Rol ... ");
                    break;

                case 4:
                    bw.write("\t*** Gestion de Roles *** ");
                    PedidoDatos.flush(bw);
                    ProcesarRoles procesarRoles = new ProcesarRoles();
                    boolean flagMenu = true; // bandera para ingresar la menu de rol
                    byte opcionMenu = 0; //variable para entrar la menu
                    while (flagMenu) {
                        do {
                            bw.write(procesarRoles.obtenerMenu()); //llamar al metodo para mostrar menu
                            PedidoDatos.flush(bw);
                            bw.write("Ingrese una Opcion Valida -> ");
                            PedidoDatos.flush(bw);
                            opcionMenu = Byte.parseByte(br.readLine());
                        } while (!((opcionMenu >= 1) && (opcionMenu <= 5)));

                        if (opcionMenu == 5) { // si la opcion es 5 cambiamos el estado de la bandera para retornar al menu principal
                            flagMenu = false;
                        }

                        procesarRoles.selecionarOpcionMenu(opcionMenu, bw, flagMenu, br); //enviamos la opcion elegida 
                        PedidoDatos.flush(bw);

                    }
                    System.out.println("Saliendo del Menu Gestionar Rol ... ");
                    break;

                case 5:
                    bw.write("Desconectado.");
                    System.exit(0);
                    break;
                default:
                    bw.write("Opcion invalida.");
            }
        }
    }

    public void menuRRHH(BufferedReader br, BufferedWriter bw) throws IOException, ClassNotFoundException, SQLException {
        EmpleadoDAO daoEmp = null;
        while (true) {
            try {
                log.info("Cliente entra al menu principal");
                bw.newLine();
                bw.write("\t********Menu de Opciones de RRHH**********");
                PedidoDatos.flush(bw);
                bw.write("1. Contratacion de empleados");
                PedidoDatos.flush(bw);
                bw.write("2. Actualizacion de datos del empleado");
                PedidoDatos.flush(bw);
                bw.write("3. Desactivacion de empleados por despido");
                PedidoDatos.flush(bw);
                bw.write("4. Ver empleados activos");
                PedidoDatos.flush(bw);
                bw.write("5. Visualizacion de pagos generados.");
                PedidoDatos.flush(bw);
                bw.write("6. Salir");
                PedidoDatos.flush(bw);
                bw.write("Seleccione el numero con la opcion que desea");
                PedidoDatos.flush(bw);
                int opcion = 0;
                String linea;
                do {
                    bw.write("---");
                    bw.newLine();
                    bw.flush();
                    linea = br.readLine(); //Lee lo que introduce el usuario
                    log.info("Esperando respuesta del usuario");
                    opcion = Integer.parseInt(linea);
                    if (opcion < 1 || opcion > 5) { //Si el usuario ingresa una opcion que no esta en el menu vuelve a solicitar ingresar opcion
                        log.info("Usuario selecciono una opcion no existente");
                        bw.write("Elige una opcion correcta.");
                        PedidoDatos.flush(bw);
                    } else if (opcion == 6) { //Si ingresa la opcion 5 el usuario se desconectara
//                    System.out.println(laIP + ": se ha desconectado...");
                        log.info("Usuario desconectado del sistema");
                        return;
                    }
                } while (opcion < 1 || opcion > 6); //Mientras el usuario ingrese opcion del 1 al 4 se estara imprimiendo el menu principal

                log.info("Entrando al switch con las opciones principales"); //Si ingresa una opcion valida, se le llevara a la opcion deseada
                switch (opcion) {
                    case 1:
                        List listaDatos;
                        String datosSolicitar[] = {"Ingrese el nombre del empleado:", "Ingrese el apellido del empleado", "Ingrese Genero [M] o [F]",
                            "Ingrese el documento de Identidad (DUI)", "Ingrese fecha de nacimiento formato [YYYY/MM/DD]", "Ingrese correo electronico del empleado", "Ingrese la direccion del empleado",
                            "Ingrese telefono del empleado", "Ingrese NIF", "Ingrese comision", "Ingrese profesion del empleado", "Ingrese el estado del empleado [1]. Activo, [2]. Inactivo", "Ingrese rol del empleado", "Ingrese el departamento del empleado"};
                        String tiposDatos[] = {"string", "string", "string", "string", "timestamp", "string", "string", "string", "string", "string", "string", "boolean", "int", "int"};
                        listaDatos = PedidoDatos.solicitarDatos(bw, br, datosSolicitar, tiposDatos);

                        Empleado emp1 = new Empleado(listaDatos.toArray());
                        daoEmp = new EmpleadoDAO();
                        daoEmp.insertarDato(emp1);

                        break;

                    case 2:
                        PedidoDatos pedirDato = new PedidoDatos();
                        bw.write("Actualización de datos del empleado. ");
                        bw.newLine();
                        bw.flush();
                        bw.write("Ingrese el ID del usuario que desea actualizar datos ");
                        bw.newLine();
                        bw.flush();
                        daoEmp = new EmpleadoDAO();
                        daoEmp.mostrarEmpleadosActivos(bw);
                        bw.flush();
                        String datoId = br.readLine();
                        int id = Integer.parseInt(datoId);
                        //ENVIAR EL ID 
                        pedirDato.menuActualiza(bw, br, id);
                        break;

                    case 3:

                        bw.write("Desactivación de empleados por despido");
                        PedidoDatos.flush(bw);
                        bw.write("Que empleado desea retirar de la base de datos de empleados activos");
                        PedidoDatos.flush(bw);
                        daoEmp = new EmpleadoDAO();
                        daoEmp.mostrarEmpleadosActivos(bw);
                        bw.flush();
                        String idDesactivar = br.readLine();
                        daoEmp.desactivaEmpleado(Short.parseShort(idDesactivar));
                        break;

                    case 4:
                        bw.write("Lista de empleados activos de BitLab");
                        PedidoDatos.flush(bw);
//                            List list=null;
                        daoEmp = new EmpleadoDAO();
                        daoEmp.mostrarEmpleadosActivos(bw);
                        bw.write("-------------------------------");
                        PedidoDatos.flush(bw);

                        break;
                    case 5:
                        bw.write("Visualización de pagos generados");
                        PedidoDatos.flush(bw);
                        DetallePlanillaDAO detPla = new DetallePlanillaDAO();
                        detPla.obtenerDatoTablas(bw);

                        break;
                    case 6:
                        bw.write("Desconectado.");
                        System.exit(0);
                        break;
                    default:
                        bw.write("Opcion invalida.");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(HiloAntiendeClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
