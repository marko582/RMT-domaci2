/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
/**
 *
 * @author Windows HD
 */
public class Sender {
    private final Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public synchronized void send(Object object) throws Exception{
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (SocketException ex) {
            System.out.println("gasi");
        } catch (EOFException ex) {
            throw new EOFException("Server je zatvorio vezu: " + ex.getMessage());
        }
    }
}
