/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

/**
 *
 * @author Aguilar
 */
public class Validaciones {

    //metodo que validar numeros
    public static boolean validarIsNumeric(String opcion) {
        byte op;
        try {
            op = Byte.parseByte(opcion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
