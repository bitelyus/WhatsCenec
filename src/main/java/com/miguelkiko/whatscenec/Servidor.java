/*
 * Projecto de Conexion entre Equipos - Cliente : Servidor
 * (c) - 2017 | Miguel KiKO @ www.miguelkiko.com - 2ºDAM
 */
package com.miguelkiko.whatscenec;

import java.io.*;
import java.net.*;
import org.joda.time.*;

public class Servidor {

    public static void main(String[] arg) throws IOException {

        //String Host = "192.168.0.107";                          // SERVIDOR LOCAL
        
        String mensaje = "";
        boolean salir = false;
        DateTime d = new DateTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream flujoSalida = null;
        DataInputStream flujoEntrada = null;
        InputStream entrada = null;
        OutputStream salida = null;
        int dia, mes, anio, hora, minutos, puerto;
        puerto = 6000;                                // PUERTO DE CONEXION!! 45.000 miles de puertos posibles
        Socket clienteConectado = null;               // PONEMOS A NULL EL SOCKET PARA EL CLIENTE

        ServerSocket servidor = new ServerSocket(puerto);   // ABRIMOS SOCKET SERVER PARA ESCUCHA EN PUERTO INDICADO
        
        System.out.println("\nSERVIDOR INICIADO\n=================\n"); // MENSAJE INFORMATIVO..

        System.out.println("i> ESPERANDO CONEXIÓN DEL CLIENTE...");     // MOSTRAMOS MENSAJE

        clienteConectado = servidor.accept();   // ACEPTAMOS LAS CONEXIONES ENTRANTES AL SERVIDOR.
        // EN ESTE PUNTO, EL FLUZO SE PARA HASTA QUE UN CLIENTE SE CONECTE, ENTONCES CONTINUARÁ...                                                                       // CUANDO RECIBA RESPUESTA, EL FLUJO SIGUE 

        // USAMOS joda-time PARA MANUPULAR FECHAS FÁCILMENTE
        dia = d.getDayOfMonth();
        mes = d.getMonthOfYear();
        anio = d.getYear();
        hora = d.getHourOfDay();
        minutos = d.getMinuteOfHour();

        if (hora > 0 && hora < 10) {
            mensaje = "BUENAS MADRUGADAS!!... HAY UN POCO SUEÑO, VERDAD??";
        } else if (hora > 10 && hora < 18) {
            mensaje = "BUENAS TARDES... PAKETE!!";
        } else if (hora > 18 && hora < 24) {
            mensaje = "BUENAS NOCHES!!! TIRA PARA LA KAMITA YA, GANDÚL, ZARRAPASTROZO!!! ... ANDA ANDA...!!";
        }

        mensaje += "\ns> TE HAS CONECTADO A LAS '" + hora + ":" + minutos + "' DEL '" + dia + "-" + mes + "-" + anio + "'";
        
        // CREO FLUJO DE SALIDA AL CLIENTE
        salida = clienteConectado.getOutputStream();
        flujoSalida = new DataOutputStream(salida);

        // CREO FLUJO DE ENTRADA DEL CLIENTE
        entrada = clienteConectado.getInputStream();
        flujoEntrada = new DataInputStream(entrada);
        
        // ENVIAMOS RESPUESTA AL SALUDO HANDSHAKE DEL CLIENTE Y ESPERAMOS ENTRADA
        flujoSalida.writeUTF("s> " + mensaje);        

        // EL CLIENTE ME ENVIA UN MENSAJE
        System.out.println("i> Recibiendo HANDSHAKE del CLIENTE: \n" + flujoEntrada.readUTF());
        System.out.println("i> SALUDO DE BIENVENDIA ENVIADO!! ... ESPERANDO MENSAJE DEL CLIENTE....");
       
        do {
            System.out.println("i> Recibiendo mensaje del CLIENTE: \n" + flujoEntrada.readUTF());
            // ENVIO EL MENSAJE AL CLIENTE
            System.out.print("?> ESCRIBE UN NUEVO MENSAJE S>C: ");
            mensaje = br.readLine();
            if (mensaje.equalsIgnoreCase("salir")) {
                salir = true;
            } else {
                // ENVIO UN SALUDO AL SERVIDOR, LO RECIBE Y ESTE CONTESTA
                flujoSalida.writeUTF("s> " + mensaje);        
                System.out.println("i> MENSAJE ENVIADO... ESPERANDO RESPUESTA DEL CLIENTE....");
            }

        } while (!salir);

        // CERRAR STREAMS Y SOCKETS
        entrada.close();
        salida.close();

        flujoEntrada.close();
        flujoSalida.close();

        clienteConectado.close();
        servidor.close();

    }// main

}// fin de Ejemplo1Servidor
