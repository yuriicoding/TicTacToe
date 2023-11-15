package ca.cmpt213.asn4.tictactoe.ui;

import ca.cmpt213.asn4.tictactoe.game.TicTacToeGame;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The main class for the Tic Tac Toe application using JavaFX.
 * Handles the setup of the GUI and user interactions.
 */
public class TicTac extends Application {
    private TicTacToeGame ticTacToeGame;
    private GameBoardUI gameBoard;
    private GridPane gridPane = new GridPane();

    /**
     * The main method to launch the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and sets up the primary stage for the Tic Tac Toe game.
     * @param primaryStage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the application window
        primaryStage.setTitle("Tic Tac Toe");

        // Create a new GridPane for the layout
        gridPane = new GridPane();
        gridPane.setHgap(40);
        gridPane.setVgap(40);
        gridPane.setPadding(new Insets(40));
        gridPane.setAlignment(Pos.CENTER);

        // Initialize the Tic Tac Toe game and the corresponding UI
        ticTacToeGame = new TicTacToeGame();
        gameBoard = new GameBoardUI(gridPane, ticTacToeGame);

        // Create a "Start New Game" button and set its properties
        Button newGameButton = new Button("Start New Game");
        newGameButton.setOnAction(e -> resetGame());
        newGameButton.setPrefSize(130, 20);
        gridPane.add(newGameButton, 0, ticTacToeGame.getBoardSize());
        GridPane.setColumnSpan(newGameButton, 2);

        // Create the scene and set it to the stage
        Scene scene = new Scene(gridPane, 600, 600);
        gridPane.setStyle("-fx-background-color: white;");
        primaryStage.setScene(scene);

        // Display the stage
        primaryStage.show();
    }

    /**
     * Resets the Tic Tac Toe game and updates the game board UI.
     */
    private void resetGame(){
        ticTacToeGame.resetGame();
        gameBoard.resetBoard();
    }
}
