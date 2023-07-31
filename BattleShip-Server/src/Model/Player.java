package Model;

public class Player {

    private String namePlayer;
    private boolean isPLaying;
    private int score;

    public Player(String namePlayer, boolean isPLaying, int score) {
        this.namePlayer = namePlayer;
        this.isPLaying = isPLaying;
        this.score = score;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public boolean isPLaying() {
        return isPLaying;
    }

    public void setPLaying(boolean PLaying) {
        isPLaying = PLaying;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
