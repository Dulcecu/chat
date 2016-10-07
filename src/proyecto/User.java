package proyecto;

import java.net.Socket;

/**
 * Created by Turpitude on 29/09/2016.
 */
public class User {
    public int id;
    public String name;
    public Socket con;

    public User(int id, String name, Socket con) {
        this.id = id;
        this.name = name;
        this.con = con;
    }

}
