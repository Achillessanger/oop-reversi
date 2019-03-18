import java.io.IOException;

public abstract class Player {
    abstract int[] nextStep(Board board) throws IOException;
    private char playerPiece;
    private int chessNum;
    private Player opponent;
    private String type;

    abstract void setChessNum(int chessNum);
    abstract int getChessNum();
    abstract char getPlayerPiece();
    abstract Player getOpponent();
    abstract String getType();
}
