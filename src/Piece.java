public enum Piece {
    BLACK('X'),WHITE('O');
    private char piece;
    private Piece(char piece){
        this.piece = piece;
    }
    public char getPiece(){
        return this.piece;
    }
}
