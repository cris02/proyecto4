/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.dao.DepartamentoDAO;
import com.bitlab.dao.RolDAO;
import com.bitlab.entidades.Departamento;
import com.bitlab.entidades.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author henry
 */
public class ProcesaDepartamentos {

    DepartamentoDAO daoDept = new DepartamentoDAO();
    public final short LIMITE_DATOS = 1000;
    
    //Variables auxiliares
    String nombreDept; 
    String descripcion;
    double presupuesto;
    Short vacantes;
    Short vacantesDisp;
    Byte estadoDept;

    public String obtenerMenuDept() {
        String menu = "\t1) Consultar Lista de Departamentos\n"
                + "\t2) Eliminar Departamento \n "
                + "\t3) Modificar Departamento \n "
                + "\t4) Crear Nuevo Departamento \n "
                + "\t5) Retornal al menu principal\n";
        return menu;
    }

    //metodo  para gestionar las opciones de Departamentos
    public void seleccionarOpcionMenu(short opcionMenu, BufferedWriter bw, boolean flagMenu, BufferedReader br) throws ClassNotFoundException, SQLException, IOException {
        List<Departamento> listaDept = daoDept.obtenerDatos(LIMITE_DATOS); //obtenemos todos los departamentos de base de datos

        switch (opcionMenu) {
            case 1: //Listando departamentos

                byte i = 0;
                for (Departamento depts : listaDept) {
                    bw.write("ID -> " + (i + 1) + depts.toString());
                    PedidoDatos.flush(bw);
                    i += 1;
                }
                break;
            case 2:
                PedidoDatos.flush(bw);
                bw.write("Igrese el [ID] del Departamento que desea eliminar:");
                PedidoDatos.flush(bw);
                byte id = Byte.parseByte(br.readLine());
                --id;
                Departamento deptEliminar = listaDept.get(id); //Obtenemos el rol de la lista             
                if (deptEliminar != null) {
                    deptEliminar.setEstado(false);
                    daoDept.actualizarDatos(deptEliminar);
                    bw.write("El Departamento Eliminado es :" + deptEliminar);
                } else {
                    bw.write("No se pudo eliminar el Departamento");
                }
                break;
            case 3:
                //opcion para actualizar un Departamento
                bw.write("Igrese el [ID] del Departamento que desea Actualizar:");
                PedidoDatos.flush(bw);
                for (Departamento depts : listaDept) {
                    bw.write("ID -> " + depts.getIdDepartamento() + " " + depts.getNombre());
                    PedidoDatos.flush(bw);
                }
                byte idModificar = Byte.parseByte(br.readLine());

                Departamento deptModificar = listaDept.get(idModificar-1); //Obtenemos el Departamento de la lista  

                //comprobamos que objeto Departamento que hemos recibido de las lista no sea null
                if (deptModificar != null) {
                    bw.write("El Departamento que desea Actualizar contiene la siguiente informacion  \n" + deptModificar);
                    PedidoDatos.flush(bw);
                    mostrarIngresoDatos(bw, br, deptModificar, "");
                    bw.write("Departamento " + deptModificar +" actualizado exitosamente");
                    daoDept.actualizarDatos(deptModificar); // modificamos los valores solicitados
                } else {
                    bw.write("NO se puede procesar la Modificacion");
                }
                break;
            case 4: //Opcion para agregar nuevo Departamento
                Departamento deptAgregar = new Departamento();
                    mostrarIngresoDatos(bw, br, deptAgregar, " que desea agregar");
                    bw.write("Departamento " + deptAgregar +" agregado exitosamente");
                    daoDept.insertarDatoSinID(deptAgregar); // Agregamos el departamento a la base de datos
                break;
                default: bw.write("Opcion invalida");
        }

    }
    
    
    private void mostrarIngresoDatos(BufferedWriter bw, BufferedReader br, Departamento deptModificar, String agregar) throws IOException{
        do {
                        bw.write("Ingrese el Nuevo Nombre del Departamento " +agregar);
                        PedidoDatos.flush(bw);
                        nombreDept = br.readLine();
                        deptModificar.setNombre(nombreDept);
                        bw.write("Ingrese la nueva descripcion del departamento " +agregar);
                        PedidoDatos.flush(bw);
                        descripcion = br.readLine();
                        deptModificar.setDescripcion(descripcion);

                        bw.write("Ingrese el nuevo presupuesto del departamento " +agregar);
                        PedidoDatos.flush(bw);
                        presupuesto = Double.parseDouble(br.readLine());
                        deptModificar.setPresupuesto(presupuesto);

                        bw.write("Ingrese las nuevas vacantes requeridas " +agregar);
                        PedidoDatos.flush(bw);
                        vacantes = Short.parseShort(br.readLine());
                        deptModificar.setVacantesRequeridas(vacantes);

                        bw.write("Ingrese las nuevas vacantes disponibles " +agregar);
                        PedidoDatos.flush(bw);
                        vacantesDisp = Short.parseShort(br.readLine());
                        deptModificar.setVacantesDisponibles(vacantesDisp);

                        bw.write("Ingrese el nuevo estado del departamento 1. Activo o 2. Inactivo");
                        PedidoDatos.flush(bw);
                        estadoDept = Byte.parseByte(br.readLine());
                        Boolean stDept;
                        if (estadoDept == 1) {
                            stDept = true;
                        } else {
                            stDept = false;
                        }
                        
                        deptModificar.setEstado(stDept);

                    } while ("".equals(nombreDept) && "".equals(descripcion) && vacantes<0 && vacantesDisp<0 && (estadoDept < 0 && estadoDept > 2));
    }
}
