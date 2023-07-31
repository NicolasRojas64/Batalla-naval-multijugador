package views.gameboard;

import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MyBtn extends Button {

    private final Image okimage;
    private final Image failImage;

    private  Integer rowNumber;
    private Integer columnNumber;
    private Integer state;
    private final String myLabel;

    public MyBtn(String label) throws HeadlessException {
        String okImagePath = "/resources/images/explode.png";
        okimage = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(okImagePath))).getImage(), 30, 30));
        String failImagePath = "/resources/images/fail.png";
        failImage = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(failImagePath))).getImage(), 30, 30));
        myLabel = label;
        this.state = 0;
        setBackground(Color.decode("#2596be"));
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (this.state != 0) {
            if (state == 1) {
                g2.drawImage(okimage, (getWidth() / 2) - 15, 5, 30, 30, this);
                g2.drawString(myLabel, (getWidth() / 2) - 12, getHeight() - 8);
            } else if (state == 2) {
                g2.drawImage(failImage, (getWidth() / 2) - 15, 5, 30, 30, this);
                g2.drawString(myLabel, (getWidth() / 2) - 12, getHeight() - 8);
            }
        }

    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMyLabel() {
        return myLabel;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }
}
