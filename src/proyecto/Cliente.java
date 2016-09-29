package proyecto;
import java.io.*;
import java.net.*;

public class Cliente  {

    public static void main(String[] args)throws  IOException {
        String hostIP = "127.0.0.1";
        int portNumber = 1234;
        Thread listen;

        try {
            Socket echoSocket = new Socket(hostIP, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            listen = new listener(echoSocket);
            listen.start();
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
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