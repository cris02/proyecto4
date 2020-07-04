/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.entidades.Usuario;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author henry
 */
public class EnvioCorreo {
    
    /* Función que envia el correo electrónico de verfificación
    Se hace uso de Properties para obtener los datos encriptados del servidor de email, puerto, etc.
    */
    public int enviarCorreo(Properties prop, Usuario usuario, BufferedWriter bw) {
        int codigo=0;
        EncriptacionTexto encriptador = new EncriptacionTexto();
        Email email = new SimpleEmail();
        email.setHostName(encriptador.getTextoDesencriptado(prop.getProperty("srName")));
        email.setSmtpPort(Integer.parseInt(encriptador.getTextoDesencriptado(prop.getProperty("srPor"))));
        email.setAuthentication(encriptador.getTextoDesencriptado(prop.getProperty("srU")), encriptador.getTextoDesencriptado(prop.getProperty("srPs")));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(encriptador.getTextoDesencriptado(prop.getProperty("srU")));
            email.setSubject(prop.getProperty("asun"));
            codigo = obtenerCodigo(); /*Aca obtenemos el codigo de verificacion random y en la siguiente linea
            de codigo se le agrega al cuerpo del email*/
            email.setMsg("Para verificar su identidad, ingrese el siguiente codigo: " + codigo);
            email.addTo(usuario.getCorreo()); /* Y aqui establecemos el correo electronico al que se va a enviar el codigo*/
            email.send();
            bw.write("Email enviado a " +usuario.getCorreo() );
            bw.write("Por favor ingrese el código que hemos enviado a su correo electrónico.");
        } catch (EmailException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return codigo;
    }
    
    
    /*Funcion privada que solamente se usa en la funcion enviarCorreo() y es la que genera el codigo de verificacion*/
    private int obtenerCodigo() {
        int codigo = (int) (Math.random() * 1000000);
        return codigo;
    }
}
