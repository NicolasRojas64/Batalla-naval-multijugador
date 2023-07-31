package Network;

import Model.Board;
import Model.Ship;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream serverOutput;
    private DataInputStream serverInput;
    private final int clientNumber;
    private String clientName;
    private final List<Ship> ships;
    private final int[][] coor;
    private final int maxScore;
    private String score;
    private Socket s;

    private final String serverIp;

    public ClientHandler(Socket socket, int clientNumber, Board board, String ip) {
        this.serverIp = ip;
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
        this.clientName = "";
        board.generateShip();
        this.ships = board.getShips();
        this.coor = board.getCoordBoard();
        this.maxScore = maxScore();
        this.score = "";
    }

    public void run() {
        while (true) {
            connection();
            closeConection();
        }
    }

    private void connection() {
        try {
            //Socket para conectar clientHandler y servidor (se usa para pasar los streams a ScoreThread y recivir información cada vez que un cliente hace una jugada)
            s = new Socket(serverIp, 9999);
            serverOutput = new DataOutputStream(s.getOutputStream());
            serverOutput.writeUTF("Socket CdH de cliente " + clientNumber + " conectado.");
            serverInput = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            //Streams para recivir y enviar información al cliente.
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            clientName = dataInputStream.readUTF();
            System.out.println("cliente " + clientName + " conectado a servidor");
            dataOutputStream.writeInt(maxScore);
            sendInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConection();
        }
    }

    private void sendInfo() {
        while (true) {
            try {
                String coordinates = dataInputStream.readUTF();
                String[] c = coordinates.split(",");
                int x = Integer.parseInt(c[0]);
                int y = Integer.parseInt(c[1]);
                dataOutputStream.writeInt(verifyCoordinates(x, y));
                //score = "Score de cliente "+ clientName+ ": " + dataInputStream.readInt() + ", ";
                score = clientName + "," + dataInputStream.readInt() + "," + dataInputStream.readBoolean();
                serverOutput.writeUTF(score);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int verifyCoordinates(int x, int y) {
        int success = 0;
        for (Ship s : ships) {
            int[] coordX = s.getCoordX();
            int[] coordY = s.getCoordY();

            for (int i = 0; i < coordX.length; i++) {
                if (x == coordX[i]) {
                    for (int j = 0; j < coordY.length; j++) {
                        if (y == coordY[j]) {
                            success = 1;
                            break;
                        }
                    }
                }
            }
        }
        return success;
    }

    private int maxScore() {
        int max = 0;
        for (int i = 0; i < coor.length; i++) {
            for (int j = 0; j < coor[i].length; j++) {
                if (coor[j][i] == 1) {
                    max += 1;
                }
            }
        }
        return max;
    }

    private void closeConection() {
        try {
            serverInput.close();
            dataOutputStream.close();
            dataInputStream.close();
            serverOutput.close();
            clientSocket.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getScore() {
        return this.score;
    }

    public int getNumber() {
        return this.clientNumber;
    }

    public DataOutputStream getOutputStream() {
        return this.dataOutputStream;
    }
}
