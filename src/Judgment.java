import java.util.ArrayList;

public class Judgment {
    private Player currentRound;

    public Player getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Player currentRound) {
        this.currentRound = currentRound;
    }

    public boolean reverse(int[] xy, Board board, Player player){//char[][] board, char piece

        if(checkLeftUp(xy,board,player,true)[0] == 1 |
                checkUp(xy,board,player,true)[0] == 1 |
                checkRightUp(xy,board,player,true)[0] == 1 |
                checkLeft(xy,board,player,true)[0] == 1 |
                checkRight(xy,board,player,true)[0] == 1 |
                checkLeftBottom(xy,board,player,true)[0] == 1 |
                checkBottom(xy,board,player,true)[0] == 1 |
                checkRightBottom(xy,board,player,true)[0] == 1){
            board.setPieceNumOnBoard(board.getPieceNumOnBoard() + 1);
            player.setChessNum(player.getChessNum() + 1);
            board.getBoard()[xy[0]][xy[1]] = player.getPlayerPiece();
            return true;
        } else
            return false;
    }
    public boolean isWon(Board board, Player player){
        if(!ifHavePossibleChoice(player,board) && !ifHavePossibleChoice(player.getOpponent(),board)){
            if((player.getChessNum() > player.getOpponent().getChessNum())
                    || ((player.getPlayerPiece() == 'O') && player.getChessNum() == player.getOpponent().getChessNum()))
                return true;
            else if(player.getOpponent().getChessNum() == 0)
                return true;
            else
                return false;
        }else
            return false;

    }
    public boolean isFailed(Board board, Player player){
        if(!ifHavePossibleChoice(player,board) && !ifHavePossibleChoice(player.getOpponent(),board)){
            if((player.getChessNum() < player.getOpponent().getChessNum())
                    || ((player.getOpponent().getPlayerPiece() == 'O') && player.getChessNum() == player.getOpponent().getChessNum()))
                return true;
            else
                return false;
        }else
            return false;
    }
    public int[] checkLeftUp(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]-i >= 0 && xy[1]-i >= 0 && board.getBoard()[xy[0]-i][xy[1]-i] == counter)
                i++;
            else if(i != 1 && xy[0]-i >= 0 && xy[1]-i >= 0 && board.getBoard()[xy[0]-i][xy[1]-i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]-j][xy[1]-j] = player.getPlayerPiece();
                }
            }
        }
        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkUp(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]-i >= 0 && xy[1] >= 0 && board.getBoard()[xy[0]-i][xy[1]] == counter)
                i++;
            else if(i != 1 && xy[0]-i >= 0 && xy[1] >= 0 && board.getBoard()[xy[0]-i][xy[1]] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]-j][xy[1]] = player.getPlayerPiece();
                }
            }
        }
        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkRightUp(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]-i >= 0 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]-i][xy[1]+i] == counter)
                i++;
            else if(i != 1 && xy[0]-i >= 0 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]-i][xy[1]+i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]-j][xy[1]+j] = player.getPlayerPiece();
                }
            }
        }
        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkLeft(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[1]-i >= 0 && board.getBoard()[xy[0]][xy[1]-i] == counter)
                i++;
            else if(i != 1 && xy[1]-i >= 0 && board.getBoard()[xy[0]][xy[1]-i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]][xy[1]-j] = player.getPlayerPiece();
                }
            }
        }

        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkRight(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]][xy[1]+i] == counter)
                i++;
            else if(i != 1 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]][xy[1]+i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]][xy[1]+j] = player.getPlayerPiece();
                }
            }
        }

        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkLeftBottom(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]+i < board.getBoard().length && xy[1]-i >= 0 && board.getBoard()[xy[0]+i][xy[1]-i] == counter)
                i++;
            else if(i != 1 && xy[0]+i < board.getBoard().length && xy[1]-i >= 0 && board.getBoard()[xy[0]+i][xy[1]-i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]+j][xy[1]-j] = player.getPlayerPiece();
                }
            }
        }

        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkBottom(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]] == counter)
                i++;
            else if(i != 1 && xy[0]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]+j][xy[1]] = player.getPlayerPiece();
                }
            }
        }

        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }
    public int[] checkRightBottom(int[] xy, Board board, Player player, boolean wantReverse){
        char counter = player.getOpponent().getPlayerPiece();
        int[] retArr = {0,0};
        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.'
        )
            return retArr;
        boolean ret = false;
        int i = 1;
        while (!ret){
            if(xy[0]+i < board.getBoard().length && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]+i] == counter)
                i++;
            else if(i != 1 && xy[0]+i < board.getBoard().length && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]+i] == player.getPlayerPiece()) {
                i--;
                ret = true;
            } else
                break;
        }
        if(wantReverse){
            if(ret){
                player.setChessNum(player.getChessNum() + i);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
                for(int j = 1; j <= i; j++){
                    board.getBoard()[xy[0]+j][xy[1]+j] = player.getPlayerPiece();
                }
            }
        }

        if(ret){
            retArr[0] = 1;
            retArr[1] = i;
        }

        return retArr;
    }

    public Player whoIsNext(Board board){
        if(ifHavePossibleChoice(currentRound.getOpponent(),board))
            this.currentRound = currentRound.getOpponent();

        return currentRound;

    }

    private boolean ifHavePossibleChoice(Player player, Board board){
        for(int i = 1; i < board.getBoard().length; i++){
            for (int j = 1; j < board.getBoard().length; j++){
                if(board.getBoard()[i][j] != '.')
                    continue;
                else  {
                    int[] xy = {i,j};
                    if(checkLeftUp(xy,board,player,false)[0] == 1 |
                            checkUp(xy,board,player,false)[0] == 1 |
                            checkRightUp(xy,board,player,false)[0] == 1 |
                            checkLeft(xy,board,player,false)[0] == 1 |
                            checkRight(xy,board,player,false)[0] == 1 |
                            checkLeftBottom(xy,board,player,false)[0] == 1 |
                            checkBottom(xy,board,player,false)[0] == 1 |
                            checkRightBottom(xy,board,player,false)[0] == 1)
                        return true;
                }
            }
        }
        return false;

    }


}
