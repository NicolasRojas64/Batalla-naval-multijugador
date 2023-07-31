package network;

import controller.IData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket clientSocket;
    private DataOutputStream dataOutputStream;
    private DataInputStream serverInput;
    private int score = 0;
    private int maxScore = 0;
    private IData data;
    private String ip;
    private int port;
    private String rivalScores;
    private ClientThread clientThread;
    private int[][] coordMatrix;

    public Client(IData data, String ipServer) {
        this.ip = ipServer;
        this.port = 9999;
        this.rivalScores = "";
        this.coordMatrix = new int[10][10];
        this.data = data;
        this.connection();
    }

    private void connection() {
        try {
            clientSocket = new Socket(ip, port);
            InputStream input = clientSocket.getInputStream();
            serverInput = new DataInputStream(input);
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF(data.getName());
            maxScore = serverInput.readInt();
            clientThread = new ClientThread(ip, port, rivalScores, data);
            clientThread.start();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void sendInfo(IData data) {
        this.data = data;
        int row = data.getRow();
        int col = data.getCol();
        String coordinates = col + "," + row;
        try {
            dataOutputStream.writeUTF(coordinates);
            getInfo(row, col);
            dataOutputStream.writeInt(score);
            dataOutputStream.writeBoolean(data.isWinner());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void getInfo(int row, int col) {
        int success = 0;
        try {
            success = serverInput.readInt();
            changeStatus(success, row, col);
            finishGame();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void changeStatus(int success, int row, int col) {
        if (success == 1 && coordMatrix[row][col] == 0) {
            data.setStatus(1);
            score += 1;
            data.setScore(score);
            coordMatrix[row][col] = 1;
        } else if (success == 1 && coordMatrix[row][col] == 1) {
            data.setStatus(1);
        } else {
            data.setStatus(2);
        }
    }

    private void finishGame() {
        if (score == maxScore) {
            data.setIsWinner(true);
            closeConection();
        }
    }

    private void closeConection() {
        try {
            serverInput.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream getOutputStream() {
        return this.dataOutputStream;
    }

    public DataInputStream getInputStream() {
        return this.serverInput;
    }

}
