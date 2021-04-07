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
   public static Main main = new Main();
   private String state;

    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane root = new StackPane();
        Scene mainMenu = new Scene(root,600,600);
        HBox headingBox = new HBox();
        Text heading = new Text("SNAKE");
        Text exitText = new Text("Press M To Exit At Any Given Moment");
        Button play = new Button("PLAY");
        Button speedRun = new Button("SPEEDRUN");
        Button quit = new Button("QUIT");
        VBox buttonBox = new VBox(30);

        play.setOnAction(e -> launchGame());
        speedRun.setOnAction(e -> launchSpeedRun());
        quit.setOnAction(e -> exitGame());
        mainMenu.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.M)
                exitGame();
        });

        primaryStage.setTitle("SNAKE");
        heading.setFill(Color.rgb(142, 4, 4));
        exitText.setFill(Color.WHITE);

        headingBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        play.setId("play-button");
        speedRun.setId("speed-run");
        quit.setId("quit-button");
        buttonBox.setId("button-box");
        headingBox.setId("heading-box");
        exitText.setId("exit-text");

        buttonBox.getChildren().addAll(play, speedRun, quit, exitText);
        headingBox.getChildren().add(heading);
        root.getChildren().addAll(headingBox, buttonBox);

        mainMenu.getStylesheets().add("demo.css");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void launchGame(){
        Platform.exit();
        main.setState("Game");
    }
    public void launchSpeedRun(){
        Platform.exit();
        main.setState("Speed Run");
    }
    public void exitGame(){
       System.exit(0);
    }

    public static void main(String[] args) {
        main.setState("Main Menu");

        if (main.getState().equals("Main Menu"))
        launch(args);

        if (main.getState().equals("Game"))
            new DefaultFrame();

        if (main.getState().equals("Speed Run"))
            new SpeedFrame();
    }
}

