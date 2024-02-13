package com.ch.study.leecode;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        return validRow(board) && validCol(board) && validBox(board);
    }

    public boolean validRow(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            boolean[] flag = new boolean[9];
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != '.') {
                    if (flag[board[row][col] - '1']) {
                        return false;
                    }
                    flag[board[row][col] - '1'] = true;
                }
            }
        }
        return true;
    }

    public boolean validCol(char[][] board) {
        for (int col = 0; col < board[0].length; col++) {
            boolean[] flag = new boolean[9];
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != '.') {
                    if (flag[board[row][col] - '1']) {
                        return false;
                    }
                    flag[board[row][col] - '1'] = true;
                }
            }
        }
        return true;
    }


    public boolean validBox(char[][] board) {
        for (int row = 0; row < board.length; row += 3) {
            for (int col = 0; col < board[0].length; col += 3) {
                if (!validBox(board, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validBox(char[][] board, int row, int col) {
        boolean[] flag = new boolean[9];
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (board[i][j] != '.') {
                    if (flag[board[i][j] - '1']) {
                        return false;
                    }
                    flag[board[i][j] - '1'] = true;
                }
            }
        }
        return true;
    }

}
