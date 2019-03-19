import java.util.ArrayList;

public class Judgment {
    private Player currentRound;
    private int[][] direction = {
            {-1,-1},{-1,0},{-1,1},
            {0,-1},{0,1},
            {1,-1},{1,0},{1,1}
    };

    public Player getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Player currentRound) {
        this.currentRound = currentRound;
    }

    public boolean reverse(int[] xy, Board board, Player player){//char[][] board, char piece

//        if(checkLeftUp(xy,board,player,true)[0] == 1 |
//                checkUp(xy,board,player,true)[0] == 1 |
//                checkRightUp(xy,board,player,true)[0] == 1 |
//                checkLeft(xy,board,player,true)[0] == 1 |
//                checkRight(xy,board,player,true)[0] == 1 |
//                checkLeftBottom(xy,board,player,true)[0] == 1 |
//                checkBottom(xy,board,player,true)[0] == 1 |
//                checkRightBottom(xy,board,player,true)[0] == 1){
//            board.setPieceNumOnBoard(board.getPieceNumOnBoard() + 1);
//            player.setChessNum(player.getChessNum() + 1);
//            board.getBoard()[xy[0]][xy[1]] = player.getPlayerPiece();
//            return true;
//        } else
//            return false;
        int[][] checkResult = check(xy,board,player);

        changeBoard(xy,checkResult,board,player);

        boolean ret = false;

        for(int i = 0; i < 8; i++){
            if(checkResult[i][0] == 1){
                ret = true;
            }
        }
        if(ret){
            board.setPieceNumOnBoard(board.getPieceNumOnBoard() + 1);
            player.setChessNum(player.getChessNum() + 1);
            board.getBoard()[xy[0]][xy[1]] = player.getPlayerPiece();
        }
        return ret;
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

    public int[][] check(int[] xy, Board board, Player player){
        char counter = player.getOpponent().getPlayerPiece();
        int[][] retArr = new int[8][2];
        int[] iniArr = {0,0};
        for(int k = 0; k < 8; k++){
            if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.'){
                retArr[k] = iniArr;
                continue;
            }
            boolean ret = false;
            int i = 1;
            while (!ret){
                if(xy[0] + i * direction[k][0] >0 && xy[0] + i * direction[k][0] < board.getBoard().length
                && xy[1] + i * direction[k][1] > 0 && xy[1] + i * direction[k][1] < board.getBoard().length &&
                        board.getBoard()[xy[0] + i * direction[k][0]][xy[1] + i * direction[k][1]] == counter){
                    i++;
                }else if(xy[0] + i * direction[k][0] >0 && xy[0] + i * direction[k][0] < board.getBoard().length
                        && xy[1] + i * direction[k][1] > 0 && xy[1] + i * direction[k][1] < board.getBoard().length &&
                        i != 1 && board.getBoard()[xy[0] + i * direction[k][0]][xy[1] + i * direction[k][1]] == player.getPlayerPiece()){
                    i--;
                    ret = true;
                }else
                    break;
            }
            if(ret){
                retArr[k][0] = 1;
                retArr[k][1] = i;
            }else {
                retArr[k] = iniArr;
            }
        }
        return retArr;
    }

    private void changeBoard(int[] xy, int[][] checkResult, Board board, Player player){
        for(int k = 0; k < 8; k++){
            if(checkResult[k][0] == 1){
                player.setChessNum(player.getChessNum() + checkResult[k][1]);
                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - checkResult[k][1]);
                for(int i = 1; i <= checkResult[k][1]; i++){
                    board.getBoard()[xy[0] + i * direction[k][0]][xy[1] + i * direction[k][1]] = player.getPlayerPiece();
                }
            }
        }
    }



//    public int[] checkLeftUp(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]-i >= 0 && xy[1]-i >= 0 && board.getBoard()[xy[0]-i][xy[1]-i] == counter)
//                i++;
//            else if(i != 1 && xy[0]-i >= 0 && xy[1]-i >= 0 && board.getBoard()[xy[0]-i][xy[1]-i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]-j][xy[1]-j] = player.getPlayerPiece();
//                }
//            }
//        }
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkUp(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]-i >= 0 && xy[1] >= 0 && board.getBoard()[xy[0]-i][xy[1]] == counter)
//                i++;
//            else if(i != 1 && xy[0]-i >= 0 && xy[1] >= 0 && board.getBoard()[xy[0]-i][xy[1]] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]-j][xy[1]] = player.getPlayerPiece();
//                }
//            }
//        }
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkRightUp(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]-i >= 0 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]-i][xy[1]+i] == counter)
//                i++;
//            else if(i != 1 && xy[0]-i >= 0 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]-i][xy[1]+i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]-j][xy[1]+j] = player.getPlayerPiece();
//                }
//            }
//        }
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkLeft(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[1]-i >= 0 && board.getBoard()[xy[0]][xy[1]-i] == counter)
//                i++;
//            else if(i != 1 && xy[1]-i >= 0 && board.getBoard()[xy[0]][xy[1]-i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]][xy[1]-j] = player.getPlayerPiece();
//                }
//            }
//        }
//
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkRight(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]][xy[1]+i] == counter)
//                i++;
//            else if(i != 1 && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]][xy[1]+i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]][xy[1]+j] = player.getPlayerPiece();
//                }
//            }
//        }
//
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkLeftBottom(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]+i < board.getBoard().length && xy[1]-i >= 0 && board.getBoard()[xy[0]+i][xy[1]-i] == counter)
//                i++;
//            else if(i != 1 && xy[0]+i < board.getBoard().length && xy[1]-i >= 0 && board.getBoard()[xy[0]+i][xy[1]-i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]+j][xy[1]-j] = player.getPlayerPiece();
//                }
//            }
//        }
//
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkBottom(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.')
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]] == counter)
//                i++;
//            else if(i != 1 && xy[0]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]+j][xy[1]] = player.getPlayerPiece();
//                }
//            }
//        }
//
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }
//    public int[] checkRightBottom(int[] xy, Board board, Player player, boolean wantReverse){
//        char counter = player.getOpponent().getPlayerPiece();
//        int[] retArr = {0,0};
//        if(xy[0] >= board.getBoard().length || xy[1] >= board.getBoard().length || board.getBoard()[xy[0]][xy[1]] != '.'
//        )
//            return retArr;
//        boolean ret = false;
//        int i = 1;
//        while (!ret){
//            if(xy[0]+i < board.getBoard().length && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]+i] == counter)
//                i++;
//            else if(i != 1 && xy[0]+i < board.getBoard().length && xy[1]+i < board.getBoard().length && board.getBoard()[xy[0]+i][xy[1]+i] == player.getPlayerPiece()) {
//                i--;
//                ret = true;
//            } else
//                break;
//        }
//        if(wantReverse){
//            if(ret){
//                player.setChessNum(player.getChessNum() + i);
//                player.getOpponent().setChessNum(player.getOpponent().getChessNum() - i);
//                for(int j = 1; j <= i; j++){
//                    board.getBoard()[xy[0]+j][xy[1]+j] = player.getPlayerPiece();
//                }
//            }
//        }
//
//        if(ret){
//            retArr[0] = 1;
//            retArr[1] = i;
//        }
//
//        return retArr;
//    }

    public Player whoIsNext(Board board){
        if(ifHavePossibleChoice(currentRound.getOpponent(),board))
            this.currentRound = currentRound.getOpponent();
        else
            System.out.println(currentRound.getOpponent().getPlayerPiece()+" player has no valid move");

        return currentRound;

    }

    private boolean ifHavePossibleChoice(Player player, Board board){
        for(int i = 1; i < board.getBoard().length; i++){
            for (int j = 1; j < board.getBoard().length; j++){
                if(board.getBoard()[i][j] != '.')
                    continue;
                else  {
                    int[] xy = {i,j};
                    int[][] checkArr = check(xy,board,player);
                    for(int k = 0; k < 8; k++){
                        if(checkArr[k][0] == 1)
                            return true;
                    }
//                    if(checkLeftUp(xy,board,player,false)[0] == 1 |
//                            checkUp(xy,board,player,false)[0] == 1 |
//                            checkRightUp(xy,board,player,false)[0] == 1 |
//                            checkLeft(xy,board,player,false)[0] == 1 |
//                            checkRight(xy,board,player,false)[0] == 1 |
//                            checkLeftBottom(xy,board,player,false)[0] == 1 |
//                            checkBottom(xy,board,player,false)[0] == 1 |
//                            checkRightBottom(xy,board,player,false)[0] == 1)
//                        return true;
                }
            }
        }
        return false;

    }


}
