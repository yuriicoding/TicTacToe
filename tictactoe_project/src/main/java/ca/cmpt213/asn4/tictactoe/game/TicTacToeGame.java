package ca.cmpt213.asn4.tictactoe.game;

import java.util.Random;

import ca.cmpt213.asn4.tictactoe.ui.GameBoardUI;

/**
 * Represents the Tic Tac Toe game logic, including the game board, player turns, and win/lose conditions.
 */
public class TicTacToeGame {
    private final int BOARD_SIZE = 4;
    private int[][] board;
    private int currentPlayer;

    /**
     * Constructs a new TicTacToeGame instance with an initialized game board and a randomly assigned starting player.
     */
    public TicTacToeGame() {
        Random random = new Random();
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = random.nextInt(2) + 1;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = 0;
            }
        }
    }

    /**
     * Gets the size of the game board.
     * @return The size of the game board.
     */
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Gets the current player number.
     * @return The current player number.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if a move is valid at the specified row and column.
     * @param row The row index.
     * @param column The column index.
     * @return True if the move is valid, false otherwise.
     */
    public boolean validMove(int row, int column) {
        return (board[row][column] == 0);
    }

    /**
     * Makes a move at the specified row and column, updating the game board and switching to the next player.
     * @param row The row index.
     * @param column The column index.
     * @return The player number who made the move.
     */
    public int makeMove(int row, int column) {
        int recordMove = currentPlayer;
        board[row][column] = recordMove;
        updatePlayer();
        return recordMove;
    }

    private void updatePlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else if (currentPlayer == 2) {
            currentPlayer = 1;
        }
    }

    /**
     * Checks if the game has ended by examining rows, columns, and diagonals. Shows an end game dialog if a winner is found.
     * @param gameBoardUI The GameBoardUI instance for displaying end game dialogs.
     */
    public void checkGameEnd(GameBoardUI gameBoardUI) {
        // Check rows and columns
        for (int i = 0; i < 4; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2], board[i][3])) {
                gameBoardUI.showEndGameDialog(board[i][0]);
            }
            if (checkRowCol(board[0][i], board[1][i], board[2][i], board[3][i])) {
                gameBoardUI.showEndGameDialog(board[0][i]);
            }
        }

        // Check diagonals
        if (checkRowCol(board[0][0], board[1][1], board[2][2], board[3][3])) {
            gameBoardUI.showEndGameDialog(board[0][0]);
        }
        if (checkRowCol(board[0][3], board[1][2], board[2][1], board[3][0])) {
            gameBoardUI.showEndGameDialog(board[0][3]);
        }

        boolean boardFull = true;
        // Check if the board is full
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    boardFull = false;
                }
            }
        }

        if (boardFull) {
            gameBoardUI.showEndGameDialog(0);
        }
    }

    private boolean checkRowCol(int c1, int c2, int c3, int c4) {
        return (c1 != 0 && c1 == c2 && c2 == c3 && c3 == c4);
    }

    /**
     * Resets the Tic Tac Toe game, including the game board and the starting player.
     */
    public void resetGame() {
        Random random = new Random();
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = random.nextInt(2) + 1;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = 0;
            }
        }
    }
}
