package Model;
import java.util.ArrayList;
import java.util.List;

public class Board extends Thread{
	private int[][] coordBoard;
    private List<Ship> ships;
    private List<Player> players;

    public Board() {
        this.coordBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coordBoard[i][j] = 0;
            }
        }
        this.ships = new ArrayList<Ship>();
        this.players = new ArrayList<Player>();
    }

    private int[] generateShipSize(){
        int sizes [] = {(int)(Math.random()*3+1), (int)(Math.random()*3+1)};
        if(sizes[0] != 1 && sizes[1] != 1){
            sizes[(int)Math.round(Math.random())] = 1;
        }
        return sizes;
    }

    private boolean verifyCoordShip(Ship shipVerify){
        if(shipVerify.getSizeX() > 1){
            for (int i = 0; i < shipVerify.getCoordX().length ; i++) {
                if(coordBoard[shipVerify.getCoordX()[i]][shipVerify.getCoordY()[0]]  == 1){
                    return false;
                }
            }
        } else if (shipVerify.getSizeY() > 1) {
            for (int i = 0; i < shipVerify.getCoordY().length ; i++) {
                if(coordBoard[shipVerify.getCoordX()[0]][shipVerify.getCoordY()[i]]  == 1){
                    return false;
                }
            }
        }else{
            if(coordBoard[shipVerify.getCoordX()[0]][shipVerify.getCoordY()[0]]  == 1){
                return false;
            }
        }
        return true;
    }


    public void generateShip(){
        for (int i = 0; i < 6; i++) {
            int[] sizeShip = generateShipSize();
            Ship ship = generateCoordNew(sizeShip[0], sizeShip[1]);
            while (!verifyCoordShip(ship)){
                ship = generateCoordNew(sizeShip[0], sizeShip[1]);
            }
            if(ship.getSizeX() == 1 & ship.getSizeY() == 1){
                coordBoard[ship.getCoordX()[0]][ship.getCoordY()[0]] = 1;
            }else if (ship.getSizeX() == 1 & ship.getSizeY() > 1)  {
                for (int j = 0; j < ship.getSizeY(); j++) {
                    coordBoard[ship.getCoordX()[0]][ship.getCoordY()[j]] = 1;
                }
            }else{
                for (int j = 0; j < ship.getSizeX(); j++) {
                    coordBoard[ship.getCoordX()[j]][ship.getCoordY()[0]] = 1;
                }
            }
            ships.add(ship);
        }
    }

    public Ship generateCoordNew(int sizeX, int sizeY){
        int[] x = new int[sizeX];
        int[] y = new int[sizeY];

        if(x.length == 1 & y.length == 1){
            saveFirstCoord(x,y);
        }else if (x.length == 1 & y.length > 1)  {
            saveFirstCoord(x,y);
            saveListCoord(y);
        }else{
            saveFirstCoord(x,y);
            saveListCoord(x);
        }
        return new Ship(sizeX,sizeY,x,y, true);
    }

    public void saveFirstCoord(int [] x, int [] y){
        x[0] = (int)(Math.random()*8);
        y[0] = (int)(Math.random()*8);
    }

    public void saveListCoord(int [] list){
        for (int j = 1; j <list.length ; j++) {
            list[j] = list[0]+j;
        }
    }


    public List<Ship> getShips() {
        return ships;
    }
    
    public int[][] getCoordBoard(){
    	return coordBoard;
    }
    
    public void setCoordBoard(int[][] coordBoard) {
    	this.coordBoard = coordBoard;
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.generateShip();
        System.out.println(board.getShips());
        int[][] coor = board.getCoordBoard();
        for(int i = 0; i < coor.length; i++) {
        	for(int j = 0; j < coor[i].length; j++) {
        		System.out.print(coor[j][i] + ", ");
        	}
        	System.out.println();
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
