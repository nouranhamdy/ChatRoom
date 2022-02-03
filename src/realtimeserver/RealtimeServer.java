/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package realtimeserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author noura
 */
public class RealtimeServer  {
    
    ServerSocket myServerSocket; // Main server
    Socket socket; // Virtual server
    DataInputStream dis; // Server ears
    PrintStream ps; // Server mouth 
    public RealtimeServer(){
    
        try {
            myServerSocket = new ServerSocket(5000);
            System.out.println(myServerSocket.getLocalPort());
            while(true){
                Socket s = myServerSocket.accept();
                new ChatHandler(s);
            }
            
            
        } catch (IOException ex) {
            
        }
    }
    public static void main(String[] args) {
        RealtimeServer server=new RealtimeServer();
    }
    
}
