package controller;

public interface IData {
    Integer getRow();

    void setRow(int row);

    Integer getCol();

    void setCol(int col);

    Integer getStatus();

    void setStatus(int status);

    Integer getScore();

    void setScore(int score);

    void sendInfo();

    boolean isWinner();

    void setIsWinner(boolean isWinner);

    String getName();

    void setName(String name);

    String getAllInfo();

    void setAllInfo(String str);
}