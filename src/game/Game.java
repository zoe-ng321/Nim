package game;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game extends Application {

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane control=new GridPane();
		control.setHgap(10);
		control.setVgap(10);
		control.setStyle("-fx-background-color: lightgrey");
		primaryStage.setTitle("Nim");
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
