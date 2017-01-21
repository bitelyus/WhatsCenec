/*
 * Proyecto de Conexion entre Equipos - Cliente : Servidor
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

public class Cliente2 {
     
      public static void main(String[] args) throws Exception {
           
            String Host = "192.168.1.165";                          // SERVIDOR LOCAL
            int Puerto = 6001;                                      // PUERTO REMOTO PARA CONEXTAR
           
            System.out.println("i> PROGRAMA CLIENTE INICIADO....");
            Socket Cliente = new Socket(Host, Puerto);              // ABRIMOS SOCKET CLIENTE
            
            // CREO FLUJO DE SALIDA AL SERVIDOR.
            DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
           
            // ENVIO UN SALUDO AL SERVIDOR, LO RECIBE Y ESTE CONTESTA
            //flujoSalida.writeUTF("c> Saludos al SERVIDOR DESDE EL CLIENTE");
            
            // ESTO PODRÍA PODUCIR UN DEATH LOCK - CANDADO DE LA MUERTE - SI LOS DOS SE QUENDA ESCUCHANDO
           
            // CREO FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
           
            // EL SERVIDOR ME ENVIA UN MENSAJE   
            System.out.println("i> Recibiendo mensaje del SERVIDOR: \n" + flujoEntrada.readUTF());
           
            // CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            Cliente.close();
           
      }// fin de main
     
}// Fin de Ejemplo1Cliente