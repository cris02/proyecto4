/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.dao.RolDAO;
import com.bitlab.dao.UsuarioDAO;
import com.bitlab.entidades.Rol;
import com.bitlab.entidades.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aguilar
 */
public class ProcesarUsuarios {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    public final short LIMITE_DATOS = 1000;
    EncriptacionTexto encriptacionTexto = new EncriptacionTexto();
    Validaciones validaciones = new Validaciones();

    //variables axiliares
    private String nombreUser = "";
    private String correoUser = "";
    private String passUser = "";
    private byte estadoUser = 1;
    private byte idRol = 0;

    //metodo para enviar el menu de Gestionar Usuarios
    public String obtenerMenuUsuario() {
        String menu = "\t1) Consultar Lista de Usuarios\n"
                + "\t2) Eliminar Usuario \n "
                + "\t3) Modificar Usuario \n "
                + "\t4) Crear Nuevo Usuario \n "
                + "\t5) Retornal al menu principal";
        return menu;
    }

    //metodo  para gestionar las opciones de Usuarios
    public void selecionarOpcionMenu(short opcionMenu, BufferedWriter bw, boolean flagMenu, BufferedReader br) throws ClassNotFoundException, SQLException, IOException {
        List<Usuario> listUsers2 = usuarioDAO.obtenerDatos(LIMITE_DATOS); //obtenemos todos los datos de la base
        RolDAO rolDAO = new RolDAO();

        switch (opcionMenu) {
            case 1:
                //listar los usuarios existentes en la base d datos
                byte i = 0;
                List<Usuario> listUsers = usuarioDAO.obtenerDatos(LIMITE_DATOS);

                for (Usuario usr : listUsers) {
                    bw.write("ID -> " + (i + 1) + usr.toString());
                    PedidoDatos.flush(bw);
                    i += 1;
                }
                i = 0;
                break;

            case 2:
                //Elimnar el Usuario de manera logica
                PedidoDatos.flush(bw);
                bw.write("Ingrese el [ID] del Usuario que desea eliminar:");
                PedidoDatos.flush(bw);
                byte id = Byte.parseByte(br.readLine());
                --id;
                Usuario userEliminar = listUsers2.get(id); //Obtenemos el rol de la lista             
                if (userEliminar != null) {
                    userEliminar.setEstatus(false);
                    usuarioDAO.actualizarDatos(userEliminar);
                    bw.write("El Usuario Eliminado es :" + userEliminar);
                } else {
                    bw.write("No se pudo eliminar el Usuario");
                }
                break;

            case 3:
                //opcion para actualizar un Usuario
                bw.write("Ingrese el [ID] del Usuario que desea Actualizar:");
                PedidoDatos.flush(bw);
                byte idModificar = Byte.parseByte(br.readLine());
                --idModificar;
                Usuario userModificar = listUsers2.get(idModificar); //Obtenemos el usuario de la lista
                //comprobamos que objeto Rol que hemos recibido de las lista no sea null
                if (userModificar != null) {
                    bw.write("El Usuario que desea Actualizar contiene la siguiente informacion + " + userModificar);
                    PedidoDatos.flush(bw);
                    boolean correoValido; //variable auxiliar
                    //validamos los datos
                    do {
                        bw.write("Ingrese el Nuevo Nombre del Usuario");
                        PedidoDatos.flush(bw);
                        nombreUser = br.readLine();
                        userModificar.setNombreUsuario(nombreUser);
                        //validar el correo
                        do {
                            bw.write("Ingrese el Nuevo Correo del Usuario");
                            PedidoDatos.flush(bw);
                            correoUser = br.readLine();
                            PedidoDatos.flush(bw);
                            correoValido = validaciones.validarCorreo(correoUser);
                            if (correoValido) {
                                correoValido = false;
                            } else {
                                correoValido = true;
                            }
                        } while (correoValido);
                        userModificar.setCorreo(correoUser); //asignar correo si es valido
                        bw.write("Ingrese la Nuevo Contraseña del Usuario");
                        PedidoDatos.flush(bw);
                        passUser = br.readLine();
                        userModificar.setContrasena(encriptacionTexto.getTextoEncriptado(passUser));
                        bw.write("Ingrese el Estado del Usuario: 1. Activo o 2. Inactivo");
                        PedidoDatos.flush(bw);
                        estadoUser = Byte.parseByte(br.readLine());
                        //asignamos el estado
                        if (estadoUser == 2) {
                            userModificar.setEstatus(false); //modificamos solo si es inactivo
                        }
                        bw.write("Ingrese el ID del Rol");
                        byte indice = 0;
                        List<Rol> listRol = rolDAO.obtenerDatos(LIMITE_DATOS);
                        for (Rol rol : listRol) {
                            bw.write("ID -> " + (indice + 1) + rol.toString());
                            indice += 1;
                        }
                        indice = 0;
                        PedidoDatos.flush(bw);
                        bw.write("ID del Rol: -> ");
                        PedidoDatos.flush(bw);
                        idRol = Byte.parseByte(br.readLine());
                        userModificar.setIdRol(idRol);
                    } while ("".equals(nombreUser) && "".equals(correoUser) && "".equals(passUser) && idRol < 0 && (estadoUser < 0 && estadoUser > 2));
                    PedidoDatos.flush(bw);
                    bw.write("Usuario  actualizado " + userModificar);
                    usuarioDAO.actualizarDatos(userModificar); // modificamos los valores solicitados
                } else {
                    bw.write("No se puede procesar la Modificacion");
                }

                break;
            case 4:
                //opcion para agregar un nuevo Usuario
                boolean stcUser; //varaiable axiliar
                byte estadoUsr;
                //validamos los datos
                do {
                    bw.write("Ingrese el Nombre del Usuario");
                    PedidoDatos.flush(bw);
                    nombreUser = br.readLine();
                    boolean correoValido;
                    //validar el correo
                    do {
                        bw.write("Ingrese el Correo del Usuario");
                        PedidoDatos.flush(bw);
                        correoUser = br.readLine();
                        correoValido = validaciones.validarCorreo(correoUser);
                        //comprobamos la validacion del correo
                        if (correoValido) {
                            correoValido = false;
                        } else {
                            correoValido = true;
                        }
                    } while (correoValido);
                    bw.write("Ingrese la Contraseña del Usario");
                    PedidoDatos.flush(bw);
                    passUser = br.readLine();
                    PedidoDatos.flush(bw);
                    bw.write("Ingrese el Estado del Usuario: 1. Activo o 2. Inactivo");
                    PedidoDatos.flush(bw);
                    estadoUsr = Byte.parseByte(br.readLine());
                    //casignamos el estado del user
                    if (estadoUsr == 1) {
                        stcUser = true;
                    } else {
                        stcUser = false;
                    }
                    bw.write("Ingrese el ID del Rol");
                    PedidoDatos.flush(bw);
                    byte indice = 0;
                    List<Rol> listRol1 = rolDAO.obtenerDatos(LIMITE_DATOS);
                    for (Rol rol : listRol1) {
                        bw.write("ID -> " + (indice + 1) + rol.toString());
                        indice += 1;
                    }
                    indice = 0;
                    PedidoDatos.flush(bw);
                    bw.write("ID del Rol: -> ");
                    idRol = Byte.parseByte(br.readLine());
                } while ("".equals(nombreUser) && "".equals(correoUser) && "".equals(passUser) && idRol < 0 && (estadoUsr < 0 && estadoUsr > 2));
                Usuario userNuevo = new Usuario(nombreUser, correoUser, encriptacionTexto.getTextoEncriptado(passUser), stcUser, idRol);
                PedidoDatos.flush(bw);
                bw.write("Creando el Usuario " + userNuevo);
                usuarioDAO.insertarDato(userNuevo);
                break;
            case 5:
                System.out.println("Retornar al menu principal");

        }

    }
}
