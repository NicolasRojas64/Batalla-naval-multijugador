package views.gameboard;

import controller.IData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JFWindow extends JFrame {

    private GamePanel gamePanel;

    public JFWindow(ActionListener actionListener, IData iData) throws HeadlessException {
        setSize(800, 700);
        setTitle("BattleShip - Grupo 2");
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(10,10);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/boat.png"))).getImage());

        gamePanel = new GamePanel(actionListener, iData);
        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);

        Timer timer = new Timer(1000, e -> {
            gamePanel.updateScore(iData);
            if (iData.isWinner()){
                JOptionPane.showMessageDialog(this,"You Win");
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();

    }

    public void updateScoreTable(String[] list) {
        gamePanel.addElementToTable(list);
    }

    public void cleanRows() {
        gamePanel.cleanRows();
    }
}
