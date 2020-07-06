/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.dao.RolDAO;
import com.bitlab.entidades.Rol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aguilar
 */
public class ProcesarRoles {

    RolDAO rolDAO = new RolDAO();
    public final short LIMITE_DATOS = 1000;

    //metodo para enviar el menu de Gestionar rol
    public String obtenerMenu() {
        String menu = "\t1) Consultar Lista de Roles\n "
                + "\t2) Eliminar ROl \n "
                + "\t3) MOdificar Rol \n "
                + "\t4) Crear Nuevo ROl \n "
                + "\t5) Retornal al menu principal";
        return menu;
    }

    //metodo para eliminar un rol (La eliminacion sera logica)
    //metodo  para gestionar las opciones de roles
    public void selecionarOpcionMenu(short opcionMenu, BufferedWriter bw, boolean flagMenu, BufferedReader br) throws ClassNotFoundException, SQLException, IOException {
        List<Rol> listRol2 = rolDAO.obtenerDatos(LIMITE_DATOS); //obtenemos todos los datos de la base
        switch (opcionMenu) {
            case 1:
                //listar los roles existentes en la base d datos
                byte i = 0;
                List<Rol> listRol = rolDAO.obtenerDatos(LIMITE_DATOS);

                for (Rol rol : listRol) {
                    bw.write("ID -> " + (i + 1) + rol.toString());
                    PedidoDatos.flush(bw);
                    i += 1;
                }
                i = 0;
                break;

            case 2:
                //Elimnar el Rol de manera logica
                PedidoDatos.flush(bw);
                bw.write("Igrese el [ID] del rol que desea eliminar:");
                PedidoDatos.flush(bw);
                byte id = Byte.parseByte(br.readLine());
                --id;
                Rol rol = listRol2.get(id); //Obtenemos el rol de la lista             
                if (rol != null) {
                    rol.setEstatus(false);
                    rolDAO.actualizarDatos(rol);
                    bw.write("El Rol Eliminado es :" + rol);
                } else {
                    bw.write("No se pudo eliminar el Rol");
                }
                break;

            case 3:
                //opcion para actualizar un rol
                bw.write("Igrese el [ID] del rol que desea Actualizar:");
                PedidoDatos.flush(bw);
                byte idModificar = Byte.parseByte(br.readLine());
                Rol rolModificar = listRol2.get(idModificar); //Obtenemos el rol de la lista  

                //comprobamos que objeto Rol que hemos recibido de las lista no sea null
                if (rolModificar != null) {
                    bw.write("Ingrese el Nuevo Nombre del Rol");
                    PedidoDatos.flush(bw);
                    String nombreRol = br.readLine();
                    rolModificar.setNombreRol(nombreRol);

                    bw.write("Ingrese la descripcion del Rol");
                    PedidoDatos.flush(bw);
                    String descripRol = br.readLine();
                    rolModificar.setDescripcion(descripRol);

                    bw.write("Ingrese el Estado del Rol: 1. Activo o 2. Inactivo");
                    PedidoDatos.flush(bw);
                    byte estadoRol = Byte.parseByte(br.readLine());

                    if (estadoRol == 2) {
                        rolModificar.setEstatus(false); //modificamos solo si es inactivo
                    }

                    bw.write("El Rol con la siguiente informacion sera actualizado " + rolModificar);

                    rolDAO.actualizarDatos(rolModificar); // modificamos los valores solicitados
                } else {
                    bw.write("NO se puede procesar la Modificacion");
                }

                break;
            case 4:
                //opcion para agregar un nuevo rol
                boolean stcRol; //varaiable axiliar
                byte statusRol;
                bw.write("Ingrese el Nombre del Rol");
                PedidoDatos.flush(bw);
                String nombreRol = br.readLine();

                bw.write("Ingrese la descripcion del Rol");
                PedidoDatos.flush(bw);
                String descRol = br.readLine();

                do {
                    bw.write("Ingrese el estatus del Rol: 1. Activo o 2. Inactivo");
                    PedidoDatos.flush(bw);
                    statusRol = Byte.parseByte(br.readLine());
                } while (!(statusRol >= 1 && statusRol <=2));

                if (statusRol == 1) {
                    stcRol = true;
                } else {
                    stcRol = false;
                }

                Rol rolNuevo = new Rol(nombreRol, descRol, stcRol);
                bw.write("Creando el empleado " + rolNuevo);
                rolDAO.insertarDato(rolNuevo);
                break;
            case 5:
                System.out.println("Retornal al menu principal");
                flagMenu = false;

        }
    }
}
