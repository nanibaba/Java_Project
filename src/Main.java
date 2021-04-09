/*
* This class sets up a stage for the main menu and launches the application.
* It starts off by defining an instance of the Main class. This instance
* is later used to launch the game's default level and the speed run.
* It is a static variable as the start method has no access
* to the instance of main. The application uses a variable state
* to toggle between the menu and the two levels of the game.
* The start method sets a scene with a heading, three buttons,
* and a text, indicating the exit button to the user. The scene
* uses CSS to style and align the buttons and the text.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    // Defines variables for the main instance and the state
   public static Main main = new Main();
   private String state;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Initializes all elements inside the stage
        StackPane root = new StackPane();
        Scene mainMenu = new Scene(root,600,600);
        HBox headingBox = new HBox();
        Text heading = new Text("Serpentine Dream");
        Text exitText = new Text("Press M To Exit At Any Given Moment");
        Button play = new Button("PLAY");
        Button speedRun = new Button("SPEEDRUN");
        Button quit = new Button("QUIT");
        VBox buttonBox = new VBox(30);

        // Adds event listeners to the buttons
        play.setOnAction(e -> launchGame());
        speedRun.setOnAction(e -> launchSpeedRun());
        quit.setOnAction(e -> exitGame());
        mainMenu.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M)
                exitGame();
        });

        // Sets the colors and alignment for the title, heading, and exit text
        primaryStage.setTitle("Serpentine Dream");
        heading.setFill(Color.rgb(142, 4, 4));
        exitText.setFill(Color.WHITE);
        headingBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        // Sets ids for CSS
        play.setId("play-button");
        speedRun.setId("speed-run");
        quit.setId("quit-button");
        buttonBox.setId("button-box");
        headingBox.setId("heading-box");
        exitText.setId("exit-text");

        // Adds elements inside the containers
        buttonBox.getChildren().addAll(play, speedRun, quit, exitText);
        headingBox.getChildren().add(heading);
        root.getChildren().addAll(headingBox, buttonBox);

        // Sets CSS file and displays stage
        mainMenu.getStylesheets().add("demo.css");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    // Getters and setters for the state
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    // Launches default game level
    public void launchGame(){
        Platform.exit();
        main.setState("Game");
    }
    // Launches speed run game level
    public void launchSpeedRun(){
        Platform.exit();
        main.setState("Speed Run");
    }
    // Exits the game
    public void exitGame(){
       System.exit(0);
    }

    public static void main(String[] args) {
        // Sets initial state to menu
        main.setState("Main Menu");

        // Launches different scenes depending on the state
        if (main.getState().equals("Main Menu"))
        launch(args);

        if (main.getState().equals("Game"))
            new DefaultFrame();

        if (main.getState().equals("Speed Run"))
            new SpeedFrame();
    }
}

