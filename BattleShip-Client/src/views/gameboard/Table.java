package views.gameboard;

import controller.IData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Table extends JPanel{

    private MyBtn[][] buttons;

    public Table(ActionListener actionListener,IData iData) {
        initTable(actionListener, iData);
    }

    private void initTable(ActionListener actionListener,IData iData) {
        buttons = new MyBtn[10][10];
        setBackground(Color.decode("#2596be"));
        setLayout(new GridLayout(11, 11, 2, 2));
        add(new JPanel());
        for (int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(new JLabel(String.valueOf(i),SwingConstants.CENTER));
            add(panel);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    panel.add(new JLabel(String.valueOf(i),SwingConstants.CENTER));
                    add(panel);
                }else {
                    MyBtn button = new MyBtn("(" + (i) + "," + (j-1) + ")");
                    button.addActionListener(e -> {
                        iData.setCol(button.getColumnNumber());
                        iData.setRow(button.getRowNumber());
                        iData.sendInfo();
                        button.setState(iData.getStatus());
                    });
                    button.setRowNumber(i);
                    button.setColumnNumber(j-1);
                    buttons[i][j-1] = button;
                    add(buttons[i][j-1]);
                }
            }
        }
    }

    public void sendCoordinate(){

    }
}
