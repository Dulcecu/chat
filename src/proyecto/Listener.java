package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by Turpitude on 29/09/2016.
 */
public class Listener extends  Thread{
    Socket clientsocket;
    BufferedReader in,stdIn;
    String inputLine;
    String nombre;
    PrintWriter out;

        public Listener(Socket cliente, String nombre, PrintWriter out){
            this.clientsocket = cliente;
            this. nombre = nombre;
            this.out = out;
        }
        public void run(){
            try {
                in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine); //Se muestra lo que es enviado por el servidor
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }