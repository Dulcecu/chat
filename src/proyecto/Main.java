package proyecto;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

        public static void main(String[] args) throws IOException {
            int portNumber = 1234;
            Thread[] th = new Thread[100];
            int i=0;

            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);

                while(true) {
                    Socket clientSocket = serverSocket.accept();
                    global.clientes.add(clientSocket);
                    th[i]=new serverfunciones(clientSocket);
                    th[i].start();
                    i++;
                }
            }
                catch (IOException e) {
                System.out.println("Error al escuchar puerto "+ portNumber);

                System.out.println(e.getMessage());
            }
        }
}

