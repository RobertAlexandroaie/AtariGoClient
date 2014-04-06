package atarigoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Draw drawmethod;
    int[][] table;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public Client(String adress, int myport) {

        String serverAdress = adress;
        int PORT = myport;
        String cerere, raspuns;
        try {
            socket = new Socket(serverAdress, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println(" Serverul nu poate fi gasit \n " + e);
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawmethod = new Draw();
        drawmethod.associateOutputFile(in);
        drawmethod.start();
    }

    public void play() {
        while (true) {
            Scanner input = new Scanner(System.in);
            if (socket.isClosed()) {
                break;
            }
            String request = input.next();
            out.println(request);
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
