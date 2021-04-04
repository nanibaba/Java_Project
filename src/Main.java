import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
   public static String state = "Main Menu";

    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane root = new StackPane();
        Scene mainMenu = new Scene(root,600,600);
        HBox headingBox = new HBox();
        Text heading = new Text("Snake Game");
        Button play = new Button("PLAY");
        Button quit = new Button("QUIT");
        VBox buttonBox = new VBox(30);

        play.setOnAction(e -> launchGame());
        quit.setOnAction(e -> exitGame());

        primaryStage.setTitle("Snake Game");
        heading.setFill(Color.rgb(142, 4, 4));

        headingBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        play.setId("play-button");
        quit.setId("quit-button");
        buttonBox.setId("button-box");
        headingBox.setId("heading-box");

        buttonBox.getChildren().addAll(play, quit);
        headingBox.getChildren().add(heading);
        root.getChildren().addAll(headingBox, buttonBox);

        mainMenu.getStylesheets().add("demo.css");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
    public static void launchGame(){
        Platform.exit();
        state = "Game";
    }
    public static void exitGame(){
        Platform.exit();
    }

    public static void main(String[] args) {

        if(state.equals("Main Menu"))
            launch(args);
        if(state.equals("Game"))
        new Frame();
    }

}

