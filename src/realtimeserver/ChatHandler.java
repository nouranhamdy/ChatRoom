/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package realtimeserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author noura
 */
public class ChatHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsvector = new Vector<ChatHandler>();

    public ChatHandler(Socket socket) throws IOException {
        //This two lines still work even there is no request of conction from client
        dis = new DataInputStream(socket.getInputStream());
        ps = new PrintStream(socket.getOutputStream());
        clientsvector.add(this);
        start();
        //ps.close();
        //dis.close();

    }

    @Override
    public void run() {
        while (true) {
            //for(ChatHandler ch: clientsvector){
            try {
                String msg = dis.readLine(); // Get massage from the client
                //System.out.println(msg);
                if (msg == null) {
                    break;
                }
                sendToAll(msg);

            } catch (IOException ex) {

                Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
            //}

        }
        try {
            dis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ps.close();
        clientsvector.remove(this);
    }

    public void sendToAll(String msg) {
        for (ChatHandler ch : clientsvector) {
            ch.ps.println(msg);
            System.out.println(msg);
        }
    }
}
