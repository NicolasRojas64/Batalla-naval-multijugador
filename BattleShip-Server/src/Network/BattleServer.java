package Network;

import Model.Board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BattleServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private List<DataOutputStream> ctOutputs;

    public void start(int port, String ip) {
        try {
            serverSocket = new ServerSocket(port);
            this.clients = new ArrayList<ClientHandler>();
            this.ctOutputs = new ArrayList<DataOutputStream>();
            int clientNumber = 1;
            Board board = new Board();
            System.out.println("Servidor activo");

            while (true) {
                ClientHandler ch = new ClientHandler(serverSocket.accept(), clientNumber, board, ip);
                clients.add(ch);
                ch.start();

                Socket chSocket = serverSocket.accept();
                DataInputStream chInput = new DataInputStream(chSocket.getInputStream());

                Socket clientThreadSocket = serverSocket.accept();
                DataOutputStream ctOutput = new DataOutputStream(clientThreadSocket.getOutputStream());
                ctOutputs.add(ctOutput);

                //Conexión  con client handler
                new ScoreThread(chInput, clients, ctOutputs).start();

                clientNumber += 1;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
