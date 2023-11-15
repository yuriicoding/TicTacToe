package ca.cmpt213.asn4.tictactoe.ui;

import java.util.ArrayList;
import java.util.List;

import ca.cmpt213.asn4.tictactoe.game.TicTacToeGame;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Represents the graphical user interface for the Tic Tac Toe game board.
 * Manages the visual representation of the game board, user interactions, and end game dialogs.
 */
public class GameBoardUI {
    private List<ImageView> imageViews = new ArrayList<>();
    private TicTacToeGame ticTacToeGame;
    private GridPane gridPane;

    // Images for player symbols and empty square
    private static final Image HEART = new Image("file:tictactoe_project\\images\\heart.jpg");
    private static final Image SPADE = new Image("file:tictactoe_project\\images\\spade.jpg");
    private static final Image FRAME = new Image("file:tictactoe_project\\images\\square.jpg");

    /**
     * Constructs a GameBoardUI instance.
     * @param gridPaneValue The GridPane representing the game board.
     * @param ticTacToeGameValue The TicTacToeGame instance managing the game logic.
     */
    public GameBoardUI(GridPane gridPaneValue, TicTacToeGame ticTacToeGameValue) {
        this.ticTacToeGame = ticTacToeGameValue;
        this.gridPane = gridPaneValue;
        initializeBoard();
    }

    /**
     * Inner class for handling mouse enter events.
     */
    class MouseEnterHandler implements javafx.event.EventHandler<MouseEvent> {
        private ImageView imageView;
        private int row;
        private int column;
    
        MouseEnterHandler(ImageView imageView, int rowVal, int columnVal) {
            this.imageView = imageView;
            this.row = rowVal;
            this.column = columnVal;
        }
    
        @Override
        public void handle(MouseEvent event) {
            if (ticTacToeGame.validMove(row, column)){
                int player = ticTacToeGame.getCurrentPlayer();
                if (player == 1){
                    imageView.setImage(SPADE);
                }
                else if (player == 2){
                    imageView.setImage(HEART);
                }  
                imageView.setOpacity(0.5);
            } 
        }
    }

    /**
     * Inner class for handling mouse exit events.
     */
    class MouseExitHandler implements javafx.event.EventHandler<MouseEvent> {
        private ImageView imageView;
        private int row;
        private int column;
    
        MouseExitHandler(ImageView imageView, int rowVal, int columnVal) {
            this.imageView = imageView;
            this.row = rowVal;
            this.column = columnVal;
        }
    
        @Override
        public void handle(MouseEvent event) {
            if (ticTacToeGame.validMove(row, column)){
                imageView.setImage(FRAME);
                imageView.setOpacity(1.0);
            }  
        }
    }

    /**
     * Inner class for handling mouse click events.
     */
    class MouseClickedHandler implements javafx.event.EventHandler<MouseEvent> {
        private ImageView imageView;
        private int row;
        private int column;
        private GameBoardUI gameBoardUI;
    
        MouseClickedHandler(ImageView imageView, int rowVal, int columnVal, GameBoardUI gameBoardUIVal) {
            this.imageView = imageView;
            this.row = rowVal;
            this.column = columnVal;
            this.gameBoardUI = gameBoardUIVal;
        }
    
        @Override
        public void handle(MouseEvent event) {
            if (ticTacToeGame.validMove(row, column)){
                int player = ticTacToeGame.makeMove(row, column);
                if (player == 1){
                    imageView.setImage(SPADE);
                }
                else if (player == 2){
                    imageView.setImage(HEART);
                }  
                imageView.setOpacity(1.0);
                ticTacToeGame.checkGameEnd(gameBoardUI);
            }
        }
    }

    /**
     * Initializes the game board by creating ImageViews for each square and setting up event handlers.
     */
    private void initializeBoard() {
        int BOARD_SIZE = ticTacToeGame.getBoardSize();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {

                ImageView square = new ImageView(FRAME);
                square.setFitHeight(90);
                square.setFitWidth(90);

                square.setOnMouseEntered(new MouseEnterHandler(square, row, column));
                square.setOnMouseExited(new MouseExitHandler(square, row, column));
                square.setOnMouseClicked(new MouseClickedHandler(square, row, column, this));

                int index = row * BOARD_SIZE + column;
                imageViews.add(index, square);
                gridPane.add(square, row, column);
            }
        }
    }

    /**
     * Resets the visual representation of the game board.
     */
    public void resetBoard() {
        imageViews.forEach((square) -> square.setImage(FRAME));
    }

    /**
     * Displays an end game dialog based on the winner or a draw.
     * @param winner The player who won (1 or 2) or 0 for a draw.
     */
    public void showEndGameDialog(int winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");

        if (winner == 1) {
            alert.setHeaderText("Congratulations!");
            alert.setContentText("Spades win the game and pierce hearts! \nTake your chance for a rematch!");
        } else if (winner == 2) {
            alert.setHeaderText("Congratulations!");
            alert.setContentText("Hearts win the game and turn spades red! \nTake your chance for a rematch!");
        } else if (winner == 0) {
            alert.setHeaderText("It's a Draw!");
            alert.setContentText("The board is full. \nReady for a rematch?");
        }

        alert.showAndWait();
        ticTacToeGame.resetGame();
        resetBoard();
    }
}
