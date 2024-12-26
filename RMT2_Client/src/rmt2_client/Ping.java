/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmt2_client;

import controller.Controller;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows HD
 */
public class Ping extends Thread{

    @Override
    public synchronized void run() {
        try {
            Controller.getInstance().ping();
        } catch (SocketException ex) {
            System.exit(0);
        }catch (StreamCorruptedException ex){ 
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
