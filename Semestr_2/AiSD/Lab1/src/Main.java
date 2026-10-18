//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int[][] createBoard(int rows, int cols) {
        int[][] board = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = 0;
            }
        }

        return board;
    }

    public static void printBoard(int[][] board) {
        final String whiteSquare = "□";
        final String blackSquare = "￭";

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) {
                    System.out.print(whiteSquare);
                } else {
                    System.out.print(blackSquare);
                }
            }
            System.out.println();
        }
    }

    public static int numberOfNeighbours(int[][] board, int row, int col) {
        int count = 0;

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (board[i][j] == 1 && !(i == row && j == col)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int[][] updateBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] newBoard = createBoard(rows, cols);

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int numberOfNeighbours = numberOfNeighbours(board, i, j);
                if (numberOfNeighbours == 3) {
                    newBoard[i][j] = 1;
                } else if (board[i][j] == 1 && numberOfNeighbours == 2) {
                    newBoard[i][j] = 1;
                } else if (board[i][j] == 1){
                    newBoard[i][j] = 0;
                }
            }
        }

        return newBoard;
    }

    public static void gameOfLife(int[][] initialBoard, int numberOfIterations) {
        printBoard(initialBoard);
        System.out.println();

        for (int i = 0; i < numberOfIterations; i++) {
            int[][] newBoard = updateBoard(initialBoard);
            printBoard(newBoard);
            System.out.println();
            initialBoard = newBoard;
        }

    }

    public static void main(String[] args) {
        final int rows = 30;
        final int cols = 30;

        int[][] board = createBoard(rows, cols);
        board[1][3] = 1;
        board[2][1] = 1;
        board[2][3] = 1;
        board[3][2] = 1;
        board[3][3] = 1;

        gameOfLife(board, 50);
    }
}