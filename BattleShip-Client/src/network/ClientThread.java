package network;

import controller.IData;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientThreadSocket;
    private DataInputStream dataInputStream;
    private String ip;
    private int port;
    private String rivalScores;

    private IData data;

    public ClientThread(String ip, int port, String rivalScores, IData data) {
        this.ip = ip;
        this.port = port;
        this.rivalScores = rivalScores;
        this.data = data;
        connection();
    }

    public void connection() {
        try {
            clientThreadSocket = new Socket(ip, port);
            InputStream input = clientThreadSocket.getInputStream();
            dataInputStream = new DataInputStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Esperando score de rivales");
        while (true) {
            this.getScores();
        }
    }

    private void getScores() {
        try {
            rivalScores = dataInputStream.readUTF();
            System.out.println(rivalScores);
            data.setAllInfo(rivalScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRivalScores() {
        return this.rivalScores;
    }

}
