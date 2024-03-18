package lk.ijse.dep.service;

public class BoardImpl implements Board{

    private Piece [][] pieces  =new Piece[NUM_OF_COLS][NUM_OF_ROWS];

    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI=boardUI;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                pieces [i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < 5; i++) {
            if (pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        int flog= this.findNextAvailableSpot(col);

        if (flog==-1){
            return false;
        }

        return true;
    }

    @Override
    public boolean existLegalMoves(){

        for (int i = 0; i < 6; i++) {
            boolean flog=isLegalMove(i);

            if (flog){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

        int flog=this.findNextAvailableSpot(col);

        pieces[col][flog]=move;

    }

    @Override
    public Winner findWinner() {

        int humanCount=0;
        int aiCount =0;

        for (int i = 0; i < 6; i++) {
            humanCount=0;
            aiCount=0;

            for (int j = 0; j < 5; j++) {
               if (pieces[i][j]==Piece.BLUE) {
                   humanCount++;
                   aiCount = 0;
               }
               else if(pieces[i][j]==Piece.GREEN){
                   aiCount++;
                   humanCount =0;

               }
               else {
                   humanCount = 0;
                   aiCount = 0;
               }

               if(humanCount==4){
                   return new Winner(Piece.BLUE,i,j-3,i,j);

               }
               if (aiCount==4){

                   return new Winner(Piece.GREEN,i,j-3,i,j);
               }

            }
        }
        for (int i = 0; i < 5; i++) {
            humanCount=0;
            aiCount=0;

            for (int j = 0; j < 6; j++) {
                if (pieces[j][i]==Piece.BLUE) {
                    humanCount++;
                    aiCount=0;

                } else if (pieces[j][i]==Piece.GREEN) {

                    aiCount++;
                    humanCount=0;
                }
                else{

                    humanCount=0;
                    aiCount=0;
                }
                if(humanCount==4) {

                    return new Winner(Piece.BLUE, j-3, i, j, i);
                }
                if (aiCount==4) {

                    return new Winner(Piece.GREEN,j-3,i,j,i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

}

