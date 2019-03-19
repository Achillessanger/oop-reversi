public class Computer extends Player {
    private char playerPiece;
    private int chessNum;
    private Player opponent;
    private Judgment judgment;
    private String type;

    Computer(){
        setChessNum(2);
    }

    public void setJudgment(Judgment judgment) {
        this.judgment = judgment;
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

    @Override
    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    int[] nextStep(Board board) {
        int[] xy = {1,1};
        int max = 0;
        for(int i = 1; i < board.getBoard().length; i ++){
            for(int j = 1; j < board.getBoard().length; j++){
                if(board.getBoard()[i][j] != '.')
                    continue;
                if(checkScore(i,j,board) == 0)
                    continue;
                else {
                    if(max < checkScore(i,j,board)){
                        max = checkScore(i,j,board);
                        xy[0] = i;
                        xy[1] = j;
                    }

                }
            }
        }
        System.out.println("Computer palces "+playerPiece+" at "+ (char)(xy[0]+96)+(char)(xy[1]+96)+".");
        return xy;
    }

    private int checkScore(int x, int y, Board board){
        int[] xy = {x,y};
        int ret = 0;

        int[][] checkArr = judgment.check(xy,board,this);

        for(int i = 0; i < 8; i++){
            ret += checkArr[i][1];
        }
        return ret;
    }
}
