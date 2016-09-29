package proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Turpitude on 28/09/2016.
 */


public class serverfunciones extends  Thread  {

    Socket clientsocket;
    PrintWriter out;
    BufferedReader in;
    String inputLine;

    public serverfunciones(Socket clientsocket) throws IOException {
        this.clientsocket = clientsocket;

    }

    public void run ()
    {

        try {
            in = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.toUpperCase();
                for(int i=0;i<global.clientes.size();i++) {

                    out = new PrintWriter(global.clientes.get(i).getOutputStream(),true);
                    if(global.clientes.get(i)!=this.clientsocket) {
                        out.println(inputLine);
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
