/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguelkiko.whatscenec;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultiHilo {

    public static void main(String args[]) throws IOException {

        try {
            ServerSocket servidor;                                              // CREAMOS SOCKET DE CONEXION LADO SERVIDOR (SERVERSOCKET)
            servidor = new ServerSocket(6000);                                  // PUERTO DE ESCUCHA ....

            System.out.println("\nSERVIDOR MULTIHILO INICIADO\n===========================\n");     // MENSAJE INFORMATIVO..
            System.out.println("i> ESPERANDO CONEXIÓN ENTRANTE DE CLIENTE..."); // MOSTRAMOS MENSAJE
            while (true) {                                                      // HACEMOS ESTO DURANTE EL SERVIDOR ESTÉ CONECTADO
                Socket cliente = new Socket();                                // CREAMOS SOCKET DE CONEXION LADO CLIENTE
                cliente = servidor.accept();                                    // ESPERAMOS A UN CLIENTE
                HiloServidor hilo = new HiloServidor(cliente);                // CUANDO LLEGUE, LE CREAMOS UN NUEVO HILO!!
                hilo.start();                                                 // LO LANZAMOS Y SE ATIENDE AL CLIENTE
            }// END WHILE
        } catch (BindException ex) {
            System.err.println("!> ERROR BIND!: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("!> ERROR!: " + ex.getMessage());
        }

    } // END MAIN

}   // END SERVIDORMULTIHILO
