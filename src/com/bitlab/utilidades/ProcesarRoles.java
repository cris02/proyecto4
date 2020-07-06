/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.dao.RolDAO;
import com.bitlab.entidades.Rol;
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

    //metodo para enviar el menu de Gestionar rol
    public String obtenerMenu() {
        String menu = "\t1) Consultar Lista de Roles"
                + "\t2) Eliminar ROl \n "
                + "\t3) MOdificar Rol \n "
                + "\t4) Crear Nuevo ROl \n "
                + "\t5) Retornal al menu principal";
        return menu;
    }


    //metodo  para gestionar las opciones de roles
    public void selecionarOpcionMenu(short opcionMenu, BufferedWriter bw, boolean flagMenu) throws ClassNotFoundException, SQLException, IOException {
        switch (opcionMenu) {
            case 1:
                //listar los roles existentes en la base d datos
                List<Rol> listRol = rolDAO.obtenerDatos();

                for (Rol rol : listRol) {
                    bw.write(rol.toString());
                }
                break;
                
            case 2:
                bw.write("Elminar Rol");
                break;
            case 3:
                bw.write("MOdificar Rol");
                break;
            case 4:
                bw.write("Crear Nuevo ROl");
                break;
            case 5:
                System.out.println("Retornar al menu principal");
                flagMenu = false;

        }
    }
}
