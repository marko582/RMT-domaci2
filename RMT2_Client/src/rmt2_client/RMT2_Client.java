/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rmt2_client;

import forms.MainFrm;

/**
 *
 * @author Windows HD
 */
public class RMT2_Client {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws Exception {
//        new MainForm().setVisible(true);
        
        new MainFrm().setVisible(true);
        while(true){
            new Ping().start();
            Thread.sleep(1000);
            
            
        }
    }
    
}
