package controller;

import network.Client;
import views.gameboard.JFWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, IData {

    private String name;
    private int row, col, score;
    private boolean isWinner;
    private int state; // 0=> sin estado   1=>Le dio a un barco  2=>Falló
    private String allInfo;
    private final JFWindow window;
    private final Client client;

    public Controller(String ipServer) {
        do {
            name = JOptionPane.showInputDialog("Ingrese Nombre:");
        } while (name == null || name.isEmpty());
        this.window = new JFWindow(this, this);
        this.client = new Client(this,ipServer);
        this.isWinner = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public Integer getRow() {
        return row;
    }

    @Override
    public Integer getCol() {
        return col;
    }

    @Override
    public Integer getStatus() {
        return state;
    }

    @Override
    public void setStatus(int status) {
        this.state = status;
    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public void sendInfo() {
        client.sendInfo(this);
    }

    @Override
    public boolean isWinner() {
        return isWinner;
    }

    @Override
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAllInfo() {
        return allInfo;
    }

    @Override
    public void setAllInfo(String str) {
        this.allInfo = str;
        window.cleanRows();
        String[] users = str.split("/");
        for (String user : users) {
            String[] info = user.split(",");
            info[2] = (info[2].equals("false")) ? "Ganó" : "Jugando";
            this.window.updateScoreTable(info);
        }
    }
}
