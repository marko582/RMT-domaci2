/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Windows HD
 */
public class Server {

    
    public void startServer(){
        ServerSocket serverSocket=null;
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server je pokrenut, cekam klijenta...");
            
            while(true){
                try{
                    
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao na server.");
                new ClientHandler(socket).start();
                }catch (EOFException | SocketException ex) {
                    System.out.println("socket exception server");
                }
            }
            
        } catch (IOException ex) {
            System.out.println("server greska");
     }
    }
}


