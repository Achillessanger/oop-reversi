import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Human extends Player {
    private char playerPiece;
    private int chessNum;
    private Player opponent;
    private String type;
    Human(){
        setChessNum(2);
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChessNum() {
        return chessNum;
    }

    public void setChessNum(int chessNum) {
        this.chessNum = chessNum;
    }

    public char getPlayerPiece() {
        return playerPiece;
    }

    public void setPlayerPiece(char playerPiece) {
        this.playerPiece = playerPiece;
    }
    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    int[] nextStep(Board board) throws IOException {
        boolean ifVailed = true;
        while (ifVailed){
            System.out.print("Enter move for " + playerPiece + " (RowCol): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputStr = null;
            inputStr = br.readLine();
            if(inputStr.matches("[a-z]+") && inputStr.length() == 2){
                char[] retChr = inputStr.toCharArray();
                int[] ret = {(int)retChr[0] - 96, (int)retChr[1] - 96} ;
                return ret;
            }else
                ifVailed = true;
        }
        return null;
    }

}
