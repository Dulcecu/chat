package proyecto;
import java.io.*;
import java.net.*;
import java.util.Objects;


public class Cliente {



    public static void main(String[] args)throws  IOException {
        String hostIP = "127.0.0.1";
        int portNumber = 1234;
        Thread listen;
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Como te llamas?");
        String nombre=stdIn.readLine();


        try {

            Socket echoSocket = new Socket(hostIP, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            listen = new Listener(echoSocket,nombre,out,stdIn);
            listen.start();
            System.out.println("Que sala quieres?");

            out.println("CONNECT "+stdIn.readLine()+" "+nombre);
           String sala=stdIn.readLine();

            while ((userInput = stdIn.readLine()) != null) {

                out.println("TEXT "+sala+" "+stdIn.readLine());

            }
        } catch (UnknownHostException e) {
            System.err.println("No conozco la IP : " + hostIP);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No puedo conectarme a : " +hostIP);

            System.exit(1);
        }
    }


}

