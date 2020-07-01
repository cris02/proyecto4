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
public class EncriptacionTexto {
    
    
    private static final String LLAVE_VAL = "Cris09Ag$05."; //mi llave de encriptacion
    BasicTextEncryptor encryptor;
    
    //constructor para asignar una nueva llave
    public EncriptacionTexto(String valor) {
        encryptor = new BasicTextEncryptor();
        if (valor == null || valor.length() == 0) {
            valor = LLAVE_VAL;
        }
        
        encryptor.setPassword(valor);
    }
    
    //constructor para asignar mi llave por defecto
    public EncriptacionTexto() {
        encryptor = new BasicTextEncryptor();
        encryptor.setPassword(LLAVE_VAL);
    }
    
    //metodo para encriptar textos
    public String getTextoEncriptado(String texto) {
        return encryptor.encrypt(texto);
    }
    
    //metodo para desencriptar textos
    public String getTextoDesencriptado(String texto) {
        return encryptor.decrypt(texto);
    }
}
