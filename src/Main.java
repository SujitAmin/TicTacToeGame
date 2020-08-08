import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        int N = 3;
        int [][] boardsT = AssortedMethods.randomMatrix(N,N, 0,2);
        //notice here it is enum
        Piece[][] board = new Piece[N][N];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                int x = boardsT[i][j];

                board[i][j] = convertIntToPiece(x);
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        Piece p1 = hasWon(board);
        System.out.println(p1);
    }

    private static Piece hasWon(Piece[][] board) {
        if ( board.length != board[0].length ) {
            return Piece.Empty;
        }
        int size = board.length;
        //PositionIterator(Position p,int rowIncrement, int colIncrement, int size)
        ArrayList<PositionIterator> instructions = new ArrayList<PositionIterator>();
        for(int i = 0 ; i < board.length; i++) {
            //for every row we have column are row iterator
            instructions.add(new PositionIterator(new Position(0, i), 1, 0, size));
            instructions.add(new PositionIterator(new Position(i, 0), 0, 1, size));
        }
        instructions.add(new PositionIterator(new Position(0,0), 1,1, size ));
        instructions.add(new PositionIterator(new Position(0, size - 1 ), 1, - 1, size));
        for (PositionIterator iterator : instructions) {
            Piece winner = hasWon(board, iterator);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        return Piece.Empty;
    }

    private static Piece hasWon(Piece[][] board, PositionIterator iterator) {
        Position firstPosition = iterator.next() ;
        Piece first = board[firstPosition.row][firstPosition.column];
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (board[position.row][position.column] != first) {
                return Piece.Empty;
            }
        }
        return first;
    }

    public static Piece convertIntToPiece(int i) {
        if (i == 1) {
            return Piece.Blue;
        } else if (i == 2) {
            return Piece.Red;
        } else {
            return Piece.Empty;
        }
    }

}
