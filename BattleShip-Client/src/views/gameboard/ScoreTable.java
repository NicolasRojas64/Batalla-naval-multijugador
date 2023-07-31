package views.gameboard;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreTable extends JDialog {
    private JTable jtElements;
    private JScrollPane jsTable;
    private DefaultTableModel dtmElements;

    public ScoreTable() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(new Dimension(300,700));
        this.setLocation(820,10);
        setAlwaysOnTop(true);
        this.setBackground(Color.decode("#30373D"));
        String[] headers = {"Nombre", "Score", "Estado"};
        dtmElements = new DefaultTableModel();
        dtmElements.setColumnIdentifiers(headers);

        jtElements = new JTable();
        jtElements.setModel(dtmElements);
        jtElements.getTableHeader().setReorderingAllowed(false);
        jtElements.getTableHeader().setBackground(Color.LIGHT_GRAY);
        jtElements.getTableHeader().setPreferredSize(new Dimension(0, 60));
        jtElements.getTableHeader().setForeground(Color.BLACK);
        jtElements.setBackground(Color.white);
        jtElements.setFillsViewportHeight(true);
        jtElements.setRowHeight(35);
        jtElements.setBorder(null);

        centerTextInCell();

        jsTable = new JScrollPane(jtElements);
        jsTable.setForeground(Color.white);
        jsTable.setBorder(null);
        jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jsTable, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public void addElementToTable(Object[] vector) {
        dtmElements.addRow(vector);
    }

    public void cleanRowsTable() {
        dtmElements.setNumRows(0);
    }

    private void centerTextInCell() {
        DefaultTableCellRenderer centerModel = new DefaultTableCellRenderer();
        centerModel.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < dtmElements.getColumnCount(); i++) {
            jtElements.getColumnModel().getColumn(i).setCellRenderer(centerModel);
        }
    }
}
