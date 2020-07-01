/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Aguilar
 */
public class GenerarEncriptacion {
    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        String privateData = "Cris09Ag$05.";
        encryptor.setPassword(privateData);
        
        String textoEncriptado = encryptor.encrypt(""); //encriptamos la cadena
        System.out.println("Texto encriptado: " + textoEncriptado);
        
        String textoDesincriptado = encryptor.decrypt(textoEncriptado);
        System.out.println("Texto desencriptado: " + textoDesincriptado);
    }
    
}
