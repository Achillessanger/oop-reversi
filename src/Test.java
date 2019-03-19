import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {
    static long startTime;
    ArrayList<String> recode = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        startTime = System.currentTimeMillis()/1000;

        //initialize
        Board board = new Board();
        Computer computer = new Computer();
        Human human = new Human();
        Judgment judgment = new Judgment();
        human.setOpponent(computer);
        human.setType("human");
        computer.setOpponent(human);
        computer.setJudgment(judgment);
        computer.setType("computer");



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        boolean ifInputVaild = true;
        int dimension = 0;
        while (ifInputVaild){
            System.out.print("Enter the board dimension(2-10even number): ");
            inputStr = br.readLine();
            try{
                dimension = Integer.parseInt(inputStr);
                ifInputVaild = false;
                if(dimension % 2 == 1 || dimension < 4 || dimension > 10)
                    ifInputVaild = true;
            }catch (NumberFormatException e){
                ifInputVaild = true;
            }
        }
        ifInputVaild = true;
        while (ifInputVaild){
            System.out.print("Computer plays (X/O): ");
            inputStr = br.readLine();
            if("X".equals(inputStr)){
                computer.setPlayerPiece(Piece.BLACK.getPiece());
                human.setPlayerPiece(Piece.WHITE.getPiece());
                judgment.setCurrentRound(computer);
                ifInputVaild = false;
            }else if("O".equals(inputStr)){
                computer.setPlayerPiece(Piece.WHITE.getPiece());
                human.setPlayerPiece(Piece.BLACK.getPiece());
                judgment.setCurrentRound(human);
                ifInputVaild = false;
            }else {
                ifInputVaild = true;
            }
        }

        board.initGame(dimension);
        board.printBoard();

        //start the game
        while (true){ ///////////wrong
            if(judgment.getCurrentRound() == computer){
                judgment.reverse(computer.nextStep(board),board,computer);
                board.printBoard();
              // System.out.println(human.getChessNum());////////??!!!!
                if(judgment.isWon(board,computer)){
                    System.out.println("Both players have no valid move.\nGame over\n"+
                            human.getPlayerPiece()+":"+computer.getPlayerPiece()+" = "+human.getChessNum()+":"+computer.getChessNum()+"\n"
                            +computer.getPlayerPiece()+" player wins.");
                   endGame(dimension,computer,human,false);
                }else if(judgment.isFailed(board,computer)){
                    System.out.println("Both players have no valid move.\nGame over\n"+
                            human.getPlayerPiece()+":"+computer.getPlayerPiece()+" = "+human.getChessNum()+":"+computer.getChessNum()+"\n"
                            +computer.getOpponent().getPlayerPiece()+" player wins.");
                    endGame(dimension,human,computer,false);
                } else {
                    judgment.whoIsNext(board);
                }

            }else {

                if(judgment.reverse(human.nextStep(board),board,human)){ //judgment.reverse(human.nextStep(board),board,human)
                    board.printBoard();
                  //  System.out.println(human.getChessNum());////////??!!!!
                    if(judgment.isWon(board,human)){
                        System.out.println("Both players have no valid move.\nGame over\n"+
                                human.getPlayerPiece()+":"+computer.getPlayerPiece()+" = "+human.getChessNum()+":"+computer.getChessNum()+"\n"
                                +human.getPlayerPiece()+" player wins.");
                       endGame(dimension,human,computer,false);
                    }else if(judgment.isFailed(board,human)){
                        System.out.println("Both players have no valid move.\nGame over\n"+
                                human.getPlayerPiece()+":"+computer.getPlayerPiece()+" = "+human.getChessNum()+":"+computer.getChessNum()+"\n"
                                +human.getOpponent().getPlayerPiece()+" player wins.");
                        endGame(dimension,computer,human,false);
                    } else {
                        judgment.whoIsNext(board);
                    }


                }else {
                    System.out.println("invalid move.\nGame over.\n"+computer.getPlayerPiece()+" player wins.");
                    endGame(dimension,computer,human,true);
                }
               // System.out.println(judgment.isVaildAndOperate(human.nextStep(),board,human));
               // human.nextStep();
            }
        }

    }

    private static void endGame(int dimension, Player winner, Player loser, boolean ifGiveUp)throws FileNotFoundException,IOException{
        String path = "recode.csv";
        long durTime = System.currentTimeMillis()/1000 - startTime;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//        recode.add(df.format(new Date()));
//        recode.add(durTime+"");
//        recode.add(dimension+"*"+dimension);
        Player X = ('X' == winner.getPlayerPiece())?winner:loser;
//        recode.add(X.getType());
//        recode.add(X.getOpponent().getType());
//        recode.add(X.getChessNum()+" to "+X.getOpponent().getChessNum());


        BufferedWriter out = new BufferedWriter(new FileWriter(path,true));
        String[] logger = {df.format(new Date()),
                durTime+"",
                dimension+"*"+dimension,
                X.getType(),
                X.getOpponent().getType(),
                ""
        } ;

//        out.write(df.format(new Date()));
//        out.write(df.format(durTime));
//        String dim = dimension+"*"+dimension;
//        out.write(df.format(dim));
//        out.write(df.format(X.getType()));
//        out.write(df.format(X.getOpponent().getType()));
        if(ifGiveUp)
            logger[5] = "human give up";
        else
            logger[5] = X.getChessNum()+" to "+X.getOpponent().getChessNum();

        //out.writeRecord
        System.exit(0);





    }


}
