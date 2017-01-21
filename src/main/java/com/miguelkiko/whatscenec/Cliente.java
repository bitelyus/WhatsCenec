/*
 * Projecto de Conexion entre Equipos - Cliente : Servidor
 * (c) - 2017 | Miguel KiKO @ www.miguelkiko.com - 2ºDAM

Crear dos programas Java:

Servidor:   A todo cliente que se conecte, una vez conectado, le mandará los Buenos días, 
            Buenas tardes o Buenas noches en función de la hora que sea así como la fecha y la hora en UTF como un String. 
            E.g.: “Buenas tardes! Son las 17.31 y hoy es 10/01/2017”

Cliente:    Se conectará al servidor y esperará que el servidor le dé el saludo y la fecha. 
            Una vez recibido el programa del cliente lo mostrará.


 */
package com.miguelkiko.whatscenec;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) throws Exception {

        String Host = "192.168.0.107";                          // SERVIDOR LOCAL
        int Puerto = 6000;                                      // PUERTO REMOTO PARA CONEXTAR
        boolean salir = false;
        String mensaje = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Socket cliente = new Socket(Host, Puerto);              // ABRIMOS SOCKET CLIENTE

        System.out.println("i> PROGRAMA CLIENTE INICIADO.... ENVIANDO SALUDO..."); // MENSAJE INFORMATIVO..

        // CREO FLUJO DE SALIDA AL SERVIDOR.
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        // ENVIO UN SALUDO AL SERVIDOR, LO RECIBE Y ESTE CONTESTA
        flujoSalida.writeUTF("c> HOOOLA!! SOY MiK ... Saludos al SERVIDOR DESDE EL CLIENTE");
        System.out.println("i> ESPERANDO MENSAJE DEL SERVIDOR....");
        // ESTO PODRÍA PODUCIR UN DEATH LOCK - CANDADO DE LA MUERTE - SI LOS DOS SE QUENDA ESCUCHANDO
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        
        do {
            // EL SERVIDOR ME ENVIA UN MENSAJE   
            System.out.println("i> Recibiendo mensaje del SERVIDOR: \n" + flujoEntrada.readUTF());
            System.out.print("?> ESCRIBE UN NUEVO MENSAJE C>S: ");
            mensaje = br.readLine();
            if (mensaje.equalsIgnoreCase("salir")) {
                salir = true;
            } else {
                // ENVIO UN SALUDO AL SERVIDOR, LO RECIBE Y ESTE CONTESTA
                flujoSalida.writeUTF("c> " + mensaje);
                System.out.println("i> ESPERANDO MENSAJE DEL SERVIDOR....");

            }
        } while (!salir);

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();

    }// fin de main

}// Fin de Ejemplo1Cliente