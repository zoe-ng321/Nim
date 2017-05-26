package game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application {
	private ArrayList<ArrayList<Circle>> pegs=new ArrayList<ArrayList<Circle>>();
	private TextField comm=null;
	//turn==true; Player 1 turn
	//turn==false; Player 2 turn
	boolean turn=true;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane control = new GridPane();
		control.setHgap(10);
		control.setVgap(10);
		control.setStyle("-fx-background-color: lightgrey");
		primaryStage.setTitle("Nim");
		Label empty = new Label("");
		control.add(empty, 0, 3);

		TextField comment = new TextField();
		comment.setEditable(false);
		comm=comment;
		control.add(comment, 0, 4);

		Label p1 = new Label("Player 1:");
		control.add(p1, 0, 0);
		TextField p1input = new TextField();
		control.add(p1input, 0, 1);
		Button p1enter = new Button("Player 1 Submit");
		p1enter.setStyle("-fx-background-color: cornflowerblue");
		control.add(p1enter, 0, 2);

		Label p2 = new Label("Player 2:");
		control.add(p2, 1, 0);
		TextField p2input = new TextField();
		control.add(p2input, 1, 1);
		Button p2enter = new Button("Player 2 Submit");
		p2enter.setDisable(true);
		p2enter.setStyle("-fx-background-color: lightgreen");
		control.add(p2enter, 1, 2);

		Button quit = new Button("Quit");
		control.add(quit, 0, 5);
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		p1enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String input1 = p1input.getText();
				p1input.setText("");
				updateBoard(input1);
				p1enter.setDisable(true);
				p2enter.setDisable(false);
				turn=false;
			}
		});
		p2enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String input2 = p2input.getText();
				p2input.setText("");
				updateBoard(input2);
				p2enter.setDisable(true);
				p1enter.setDisable(false);
				turn=true;
			}
		});

		Scene scene1 = new Scene(control, 300, 300);
		primaryStage.setScene(scene1);
		primaryStage.show();

		Stage board = new Stage();
		board.setTitle("Nim");
		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setStyle("-fx-background-color: lightgrey");
		
		HBox box1=new HBox(10);
		Label al=new Label("A:");
		grid.add(al, 0, 0);
		ArrayList<Circle> row1=new ArrayList<Circle>();
		for (int i=0;i<3;i++){
			Circle c=new Circle(10);
			c.setFill(Color.MEDIUMPURPLE);
			row1.add(c);
			box1.getChildren().add(c);
		}
		pegs.add(row1);
		grid.add(box1, 1, 0);
		
		HBox box2=new HBox(10);
		Label bl=new Label("B:");
		grid.add(bl, 0, 1);
		ArrayList<Circle> row2=new ArrayList<Circle>();
		for (int i=0;i<5;i++){
			Circle c=new Circle(10);
			c.setFill(Color.MEDIUMPURPLE);
			row2.add(c);
			box2.getChildren().add(c);
		}
		pegs.add(row2);
		grid.add(box2, 1, 1);
		
		HBox box3=new HBox(10);
		Label cl=new Label("C:");
		grid.add(cl, 0, 2);
		ArrayList<Circle> row3=new ArrayList<Circle>();
		for (int i=0;i<8;i++){
			Circle c=new Circle(10);
			c.setFill(Color.MEDIUMPURPLE);
			row3.add(c);
			box3.getChildren().add(c);
		}
		pegs.add(row3);
		grid.add(box3, 1, 2);
		
		Scene scene2 = new Scene(grid, 300, 300);
		board.setScene(scene2);
		board.show();
	}

	private void updateBoard(String input) {
		char [] in=input.toCharArray();
		ArrayList<Circle>target=null;
		switch (in[0]){
		case 'A':
			target=pegs.get(0);
			break;
		case 'B':
			target=pegs.get(1);
			break;
		case 'C':
			target=pegs.get(2);
			break;
		}
		int num=Integer.parseInt(in[1]+"");
		int check=0;
		
		for (int i=target.size()-1;check!=num && i>=0;i--){
			if (target.get(i).getFill().equals(Color.MEDIUMPURPLE)){
				target.get(i).setFill(Color.LIGHTGRAY);
				check++;
			}
		}
		for (ArrayList<Circle> cl:pegs){
			for (Circle c:cl){
				if (c.getFill().equals(Color.MEDIUMPURPLE)){
					return;
				}
			}
		}
		if (turn){
			comm.setText("Player 2 wins!");
		}
		else{
			comm.setText("Player 1 wins!");
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
