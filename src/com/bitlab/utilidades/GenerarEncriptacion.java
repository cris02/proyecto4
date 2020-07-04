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
        
       //String textoEncriptado = encryptor.encrypt(""); //encriptamos la cadena
        //System.out.println("Texto encriptado: " + textoEncriptado);
        
//        String textoDesincriptado1 = encryptor.decrypt("sh15pmN5dguwzIDUTJ70HDu7qHDrsFTeIZ/QEOuxXDpPtHvEVBFUnmxEzAzrJOQ7");
//        String textoDesincriptado2 = encryptor.decrypt("jpTG8tdAqE0tkUHt7ZhTiQ==");
//        String textoDesincriptado3 = encryptor.decrypt("UaEEVHUmqtd2zwf0VNl71nY8rYwBtg9r");
//        String textoDesincriptado4 = encryptor.decrypt("1LcPL3mSX9aEhoK7UDDJT3bW0cfCXCZak4InsZD6nEF5Pqp4+COdZA==");
//        System.out.println("Texto desencriptado: " + textoDesincriptado1);
//        System.out.println("Texto desencriptado: " + textoDesincriptado2);
//        System.out.println("Texto desencriptado: " + textoDesincriptado3);
//        System.out.println("Texto desencriptado: " + textoDesincriptado4);
    }
    
}
