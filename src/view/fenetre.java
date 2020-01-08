package view;
import java.time.LocalDateTime;

import application.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fenetre extends Application {
	
	Stage window;
	TableView<Tweets> table;
	private static BaseDeTweets t;
	public static int c = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("My title");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
