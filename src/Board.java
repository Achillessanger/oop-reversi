public class Board {
    private char[][] board;
    private int pieceNumOnBoard = 4;

    public int getPieceNumOnBoard() {
        return pieceNumOnBoard;
    }

    public void setPieceNumOnBoard(int pieceNumOnBoard) {
        this.pieceNumOnBoard = pieceNumOnBoard;
    }

    public char[][] getBoard() {
        return board;
    }

    public void initGame(int dimension){
        char x = 'X';
        char o = 'O';
        board = new char[dimension+1][dimension+1];
        board[0][0] = ' ';
        for(int i = 1 ; i < dimension+1; i++){
            board[0][i] = (char)(96 + i);
            board[i][0] = (char)(96 + i);
        }
        for(int i = 1 ; i < dimension+1; i++){
            for(int j = 1; j < dimension+1; j++){
                board[i][j] = '.';
            }
        }
        board[dimension/2][dimension/2] = o;
        board[dimension/2][dimension/2+1] = x;
        board[dimension/2+1][dimension/2] = x;
        board[dimension/2+1][dimension/2+1] = o;
    }
    public void printBoard(){
        for(char[] col : board){
            for (char piece : col){
                System.out.print(piece);
            }
            System.out.print('\n');
        }
    }

}
