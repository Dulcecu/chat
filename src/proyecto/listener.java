package proyecto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Turpitude on 29/09/2016.
 */
public class listener extends  Thread{
    Socket clientsocket;
    BufferedReader in;
    String inputLine;

    public listener(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public  void run()
    {
        try {
            in =new BufferedReader( new InputStreamReader(clientsocket.getInputStream()));
            while ((inputLine = in.readLine())!=null) {
                System.out.println("echo: " + inputLine);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
