import java.util.Scanner;

public class sudokuSolver {

    public static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // if there is no empty cell, puzzle is solved
        if (row == -1 && col == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                board[row][col] = 0; // backtrack
            }
        }

        return false;
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {
        // check if num is already present in the current row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // check if num is already present in the current column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // check if num is already present in the current 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[] findEmptyCell(int[][] board) {
        int[] cell = new int[] { -1, -1 };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    cell[0] = i;
                    cell[1] = j;
                    return cell;
                }
            }
        }
        return cell;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[9][9];

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }

        scanner.close();
    }
}
