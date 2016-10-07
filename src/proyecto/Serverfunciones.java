package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by Turpitude on 28/09/2016.
 */


public class Serverfunciones extends  Thread {

    Socket clientsocket;
    PrintWriter out;
    BufferedReader in;
    String inputLine;

    public Serverfunciones(Socket clientsocket) throws IOException {
        this.clientsocket = clientsocket;

    }

    public void run() {

        try {

            int id = 0;
            User micliente = null;
            in = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
            out = new PrintWriter(clientsocket.getOutputStream(), true);
            out.println("Sala 0: " +Salas.sala1.size() + " Sala 1: " + Salas.sala2.size() + " Sala 2: " + Salas.sala3.size() + " Sala 3: " + Salas.sala4.size());
            while ((inputLine = in.readLine()) != null) {

                String[] commands = inputLine.split(" ");
                if (Objects.equals("CONNECT",commands[0])) {

                    int i = Integer.parseInt(commands[1]);
                    List<User> usuariosala=null;
                    if(i==0)
                    {
                        usuariosala=Salas.sala1;
                    }
                    if(i==1)
                    {
                        usuariosala=Salas.sala2;
                    }
                    if(i==2)
                    {
                        usuariosala=Salas.sala3;
                    }
                    if(i==3)
                    {
                        usuariosala=Salas.sala4;
                    }

                    int len=usuariosala.size();

                    for (int j = 0; j<=len; j++) {
                        if (j == 4) {
                            out.println("ERROR Sala " + i + " llena");
                        }
                        else {
                           try {

                               if (Objects.nonNull(usuariosala.get(j))) {
                                   micliente = new User(j, commands[2], clientsocket);
                                   usuariosala.add(micliente);
                                   out.println("OK Connectado correctamente a la sala: " + i);
                                   j=len;

                               }
                           }
                           catch (Exception e)
                           {
                               j=len;
                               micliente = new User(id, commands[2], clientsocket);
                               usuariosala.add(micliente);
                               out.println("OK Connectado correctamente a la sala: " + i);
                           }
                        }


                    }
                    if(i==0)
                    {
                        Salas.sala1=usuariosala;
                    }
                    if(i==1)
                    {
                        Salas.sala2=usuariosala;
                    }
                    if(i==2)
                    {
                        Salas.sala3=usuariosala;
                    }
                    if(i==3)
                    {
                        Salas.sala4=usuariosala;
                    }


                }

                /*while ((inputLine = in.readLine()) != null) {
                    inputLine = inputLine.toUpperCase();
                    for (int i = 0; i < Global.socketclientes.size(); i++) {

                        out = new PrintWriter(Global.socketclientes.get(i).getOutputStream(), true);
                        if (Global.socketclientes.get(i) != this.clientsocket) {
                            out.println(inputLine);
                        }
                    }
                }*/

                if (Objects.equals("TEXT",commands[0])) {
                    String input=null;
                    int sal = Integer.parseInt(commands[1]);
                    String[] mensaje=inputLine.split("TEXT "+sal);
                    input=mensaje[1];

                        if(Objects.equals("EXIT",commands[2])){
                            if(sal==0){
                                int len=Salas.sala1.size();
                                for(int j=0;j<len;j++) {
                                    if(Objects.equals(Salas.sala1.get(j),micliente))
                                    Salas.sala1.remove(j);
                                    j=len;
                                }
                            }
                            if(sal==1){
                                int len=Salas.sala2.size();
                                for(int j=0;j<len;j++) {
                                    if(Objects.equals(Salas.sala2.get(j).name,micliente))
                                        Salas.sala2.remove(j);
                                    j=len;
                                }
                            }
                            if(sal==2){
                                int len=Salas.sala3.size();
                                for(int j=0;j<len;j++) {
                                    if(Objects.equals(Salas.sala3.get(j).name,micliente))
                                        Salas.sala3.remove(j);
                                    j=len;
                                }
                            }
                            if(sal==3){
                                int len=Salas.sala4.size();
                                for(int j=0;j<len;j++) {
                                    if(Objects.equals(Salas.sala4.get(j).name,micliente))
                                        Salas.sala4.remove(j);
                                    j=len;
                                }
                            }

                        }
                    List<User> misala=null;
                    if (sal==0)
                    {
                        misala=Salas.sala1;
                    }
                    if (sal==1)
                    {
                        misala=Salas.sala2;
                    }
                    if (sal==2)
                    {
                        misala=Salas.sala3;
                    }
                    if (sal==3)
                    {
                        misala=Salas.sala4;
                    }
                    for (int i = 0; i<misala.size(); i++) {

                        out = new PrintWriter(misala.get(i).con.getOutputStream(), true);

                        if (misala.get(i).con != this.clientsocket) {
                            out.println(input);
                        }
                    }
                }
            }
        }
        catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }






