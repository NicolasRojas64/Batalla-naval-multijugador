package Model;

import java.util.Arrays;

public class Ship {

    private int sizeX;
    private int sizeY;
    private int[] coordX;
    private int[] coordY;
    private boolean state;

    public Ship(int sizeX, int sizeY, int[] coordX, int[] coordY, boolean state) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.coordX = coordX;
        this.coordY = coordY;
        this.state = state;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int[] getCoordX() {
        return coordX;
    }

    public void setCoordX(int[] coordX) {
        this.coordX = coordX;
    }

    public int[] getCoordY() {
        return coordY;
    }

    public void setCoordY(int[] coordY) {
        this.coordY = coordY;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", coordX=" + Arrays.toString(coordX) +
                ", coordY=" + Arrays.toString(coordY) +
                ", state=" + state +
                '}'+"\n";
    }
}
