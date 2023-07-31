package views.gameboard;

import controller.IData;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private int score;
    private JLabel sc;
    private Table panelTable;

    private ScoreTable scoreTable;

    public GamePanel(ActionListener actionListener, IData iData) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setLayout(new GridLayout(3,0,2,2));
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(new JLabel("BattleShip", SwingConstants.CENTER));
        sc = new JLabel("Score: " + score, SwingConstants.CENTER);
        panel.add(sc);
        JButton openScoreTable = new JButton("Puntajes Globales");
        openScoreTable.setBackground(Color.lightGray);
        openScoreTable.setBorder(new BevelBorder(BevelBorder.RAISED));
        setSize(new Dimension(200, 20));
        openScoreTable.addActionListener(e -> {
            if (scoreTable.isVisible()) {
                openScoreTable.setText("Ver Puntajes Globales");
                scoreTable.setVisible(false);
            } else {
                openScoreTable.setText("Ocultar Puntajes Globales");
                scoreTable.setVisible(true);
            }
        });
        panel.add(openScoreTable);
        add(panel, BorderLayout.NORTH);


        panelTable = new Table(actionListener, iData);
        add(panelTable, BorderLayout.CENTER);

        scoreTable = new ScoreTable();
    }

    public void updateScore(IData data) {
        sc.setText("Score: " + data.getScore());
    }


    public void addElementToTable(Object[] v) {
        scoreTable.addElementToTable(v);
    }

    public void cleanRows() {
        scoreTable.cleanRowsTable();
    }
}
