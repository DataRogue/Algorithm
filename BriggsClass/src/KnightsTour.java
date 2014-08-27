import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class KnightsTour {
    /*
     * Uses recursive search to find a knight's tour of an empty chessboard.
     * 
     * (you can see the implementation notes for some suggestions)
     */
    private static int BOARD_SIZE;
    private static int[][] B;
    private static int backtrackCount = 0;
    private static int moves = 0;

    private static class Cell implements Comparable<Cell> {
        /*
         * For representing a cell in the board during the search for a tour.
         */
        // all data fields are public and can be
        // directly accessed from within KnightTour.java
        public int row, col, vacantNbrCount;

        public Cell() {
            row = col = vacantNbrCount = 0;
        }

        public Cell(int r, int c) {
            row = r;
            col = c;
            vacantNbrCount = 0;
        }

        public Cell(int r, int c, int vNC) {
            row = r;
            col = c;
            vacantNbrCount = vNC;
        }

        /*
         * Compares cells first by vacantNbrCount, then by row, then by column.
         */
        public int compareTo(Cell other) {
            int countDiff = vacantNbrCount - other.vacantNbrCount, rowDiff = row
                    - other.row, colDiff = col - other.col;

            if (countDiff != 0)
                return countDiff;
            else if (rowDiff != 0)
                return rowDiff;
            else
                return colDiff;
        }
    }

    private static boolean isLegit(int _currRow, int _currCol) {
        // Tests if the cell is allowed for consideration
        Cell object = new Cell(_currRow, _currCol);
        if (((object.row < 0) || (object.row >= BOARD_SIZE) || (object.col < 0) || (object.col >= BOARD_SIZE))) {
            return false;
        } else if ((B[object.row][object.col] >= 1)) {
            return false;
        } else {
            return true;
        }
    }

    // ------------------------------------------------------------
    private static boolean findTour(int row, int col) {

        moves++;
        B[row][col] = moves;

        if (moves == (BOARD_SIZE * BOARD_SIZE)) {
            return true;
        }

        List<Cell> candidateNextMoves = new ArrayList<Cell>();
        if (isLegit((row - 2), (col - 1))) {
            candidateNextMoves.add(new Cell((row - 2), (col - 1)));
        }
        ;
        if (isLegit((row + 2), (col - 1))) {
            candidateNextMoves.add(new Cell((row + 2), (col - 1)));
        }
        ;
        if (isLegit((row - 2), (col + 1))) {
            candidateNextMoves.add(new Cell((row - 2), (col + 1)));
        }
        ;
        if (isLegit((row + 2), (col + 1))) {
            candidateNextMoves.add(new Cell((row + 2), (col + 1)));
        }
        ;
        if (isLegit((row - 1), (col - 2))) {
            candidateNextMoves.add(new Cell((row - 1), (col - 2)));
        }
        ;
        if (isLegit((row + 1), (col - 2))) {
            candidateNextMoves.add(new Cell((row + 1), (col - 2)));
        }
        ;
        if (isLegit((row - 1), (col + 2))) {
            candidateNextMoves.add(new Cell((row - 1), (col + 2)));
        }
        ;
        if (isLegit((row + 1), (col + 2))) {
            candidateNextMoves.add(new Cell((row + 1), (col + 2)));
        }
        ;
        ListIterator<Cell> listIt = candidateNextMoves.listIterator();

        while (listIt.hasNext()) {
            Cell testCell = listIt.next();
            boolean selfSuccess = findTour(testCell.row, testCell.col);
            if (selfSuccess) {
                return true;
            } else {
                B[testCell.row][testCell.col] = 0;
                backtrackCount++;
                moves--;
            }
        }
        return false;
    }

    private static List<Cell> heuristic(List<Cell> _mahList, Cell testCell) {
        // This method takes in a list and a cell.
        // It then places the cell in the list in the location
        // Based on how many moves it has
        // Going from least to greatest

        List<Cell> mahList = _mahList;
        if (!isLegit(testCell.row, testCell.col)) {
            return mahList;
        }
        if (mahList.isEmpty()) {
            mahList.add(testCell);
            return mahList;
        }

        ListIterator<Cell> listIt = mahList.listIterator();
        while (listIt.hasNext()) {
            Cell tempCell = listIt.next();
            if (getSize(testCell) < getSize(tempCell)) {
                mahList.add(listIt.previousIndex() + 1, testCell);
                return mahList;
            }
        }
        mahList.add(testCell);
        return mahList;
    }

    private static int getSize(Cell testCell) {
        int size = 0;
        if (isLegit((testCell.row - 2), (testCell.col - 1))) {
            size++;
        }
        ;
        if (isLegit((testCell.row + 2), (testCell.col - 1))) {
            size++;
        }
        ;
        if (isLegit((testCell.row - 2), (testCell.col + 1))) {
            size++;
        }
        ;
        if (isLegit((testCell.row + 2), (testCell.col + 1))) {
            size++;
        }
        ;
        if (isLegit((testCell.row - 1), (testCell.col - 2))) {
            size++;
        }
        ;
        if (isLegit((testCell.row + 1), (testCell.col - 2))) {
            size++;
        }
        ;
        if (isLegit((testCell.row - 1), (testCell.col + 2))) {
            size++;
        }
        ;
        if (isLegit((testCell.row + 1), (testCell.col + 2))) {
            size++;
        }
        ;
        return size;
    }

    // --------------------------------------------------------------------------------
    private static boolean heuristicFindTour(int row, int col) {
        // Compresses the list generation to a method
        // Uses a heuristic method to organize the list
        moves++;
        B[row][col] = moves;

        if (moves == (BOARD_SIZE * BOARD_SIZE)) {
            return true;
        }

        List<Cell> candidateNextMoves = generateList(new Cell(row, col));
        ListIterator<Cell> listIt = candidateNextMoves.listIterator();

        while (listIt.hasNext()) {
            Cell testCell = listIt.next();
            boolean selfSuccess = heuristicFindTour(testCell.row, testCell.col);
            if (selfSuccess) {
                return true;
            } else {
                B[testCell.row][testCell.col] = 0;
                backtrackCount++;
                moves--;
            }
        }

        return false;

    }

    private static List<Cell> generateList(Cell cell) {
        // generates a list for the recusrive method to iterate through
        int col = cell.col;
        int row = cell.row;
        List<Cell> myList = new ArrayList<Cell>();
        myList = heuristic(myList, new Cell((row - 2), (col - 1)));
        myList = heuristic(myList, new Cell((row + 2), (col - 1)));
        myList = heuristic(myList, new Cell((row - 2), (col + 1)));
        myList = heuristic(myList, new Cell((row + 2), (col + 1)));
        myList = heuristic(myList, new Cell((row - 1), (col - 2)));
        myList = heuristic(myList, new Cell((row + 1), (col - 2)));
        myList = heuristic(myList, new Cell((row - 1), (col + 2)));
        myList = heuristic(myList, new Cell((row + 1), (col + 2)));
        /*
         * if(isLegit((row-2),(col-1))){myList.add(new Cell((row-2),(col-1)));};
         * if(isLegit((row+2),(col-1))){myList.add(new Cell((row+2),(col-1)));};
         * if(isLegit((row-2),(col+1))){myList.add(new Cell((row-2),(col+1)));};
         * if(isLegit((row+2),(col+1))){myList.add(new Cell((row+2),(col+1)));};
         * if(isLegit((row-1),(col-2))){myList.add(new Cell((row-1),(col-2)));};
         * if(isLegit((row+1),(col-2))){myList.add(new Cell((row+1),(col-2)));};
         * if(isLegit((row-1),(col+2))){myList.add(new Cell((row-1),(col+2)));};
         * if(isLegit((row+1),(col+2))){myList.add(new Cell((row+1),(col+2)));};
         */
        return myList;
    }

    public static void main(String[] args) {
        // Takes in sized board extra-credit
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter size of board between 4 and 11: ");
        while (!((BOARD_SIZE >= 4) && (BOARD_SIZE <= 11))) {
            BOARD_SIZE = sc.nextInt();
            B = new int[BOARD_SIZE][BOARD_SIZE];
        }
        System.out.println("");
        /*
         * Tries both search methods and reports the results.
         */

        boolean success;

        // initialize state of the search
        // so that the first two moves are made to [0][0]
        // and [2][1]; both B and L should be initialized to
        // reflect that.

        // perform the search
        success = findTour(0, 0);

        if (success) {
            // report success and display tour
            System.out.println("HUZZAH SWEET GLORIOUS SUCESS!");
            for (int i = 0; i <= BOARD_SIZE - 1; i++) {
                for (int j = 0; j <= BOARD_SIZE - 1; j++) {
                    System.out.print(B[i][j] + " ");

                }
                System.out.println("");
            }
            System.out.println("And to compare backtracks: " + backtrackCount);

        }

        else {
            System.out.println("At least it compiles!");
            System.out.println(backtrackCount);
            System.out.println(moves);
            /*
             * report failure
             * 
             * report backtrackCount System.out.println(backtrackCount);
             * initialize state of the search as before
             * 
             * perform the search
             */

        }
        System.out.println("");
        moves = 0;
        backtrackCount = 0;
        B = new int[BOARD_SIZE][BOARD_SIZE];

        success = heuristicFindTour(0, 0);

        if (success) {
            System.out.println("HUZZAH SWEET GLORIOUS SUCESS!");
            for (int i = 0; i <= BOARD_SIZE - 1; i++) {
                for (int j = 0; j <= BOARD_SIZE - 1; j++) {
                    System.out.print(B[i][j] + " ");

                }
                System.out.println("");
            }
            System.out.println("And to compare backtracks: " + backtrackCount);

        } else {
            System.out.println("At least it compiles!");
            System.out.println(backtrackCount);
            System.out.println(moves);
            // report failure

            // report backtrackCount
        }
    }
}
