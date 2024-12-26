/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows HD
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection conn;

    private DatabaseConnection() throws SQLException {
        String url="jdbc:mysql://localhost:3306/rmt2";
        String user="root";
        String pass="";
        conn=DriverManager.getConnection(url, user, pass);
    }
    
    public static Connection getConnection(){
        if(instance==null){
            try {
                instance=new DatabaseConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance.conn;
    }
    
    
}
