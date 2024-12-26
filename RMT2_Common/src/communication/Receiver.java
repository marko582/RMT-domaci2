/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Windows HD
 */
public class Receiver {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public synchronized Object receive() throws Exception{
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (SocketException ex) {
            throw new SocketException("Veza sa klijentom je prekinuta: " + ex.getMessage());
        } catch (EOFException ex) {
            throw new EOFException("Klijent je zatvorio vezu: " + ex.getMessage());
        }
    
    }
}
