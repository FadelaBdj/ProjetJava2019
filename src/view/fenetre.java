package view;
import java.time.LocalDateTime;

import application.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fenetre extends Application {
	
	Stage window;
	TableView<Tweets> table;
	private static BaseDeTweets t;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Base de tweets");
		
		//Colonne cpt
		TableColumn<Tweets, String> colCpt = new TableColumn<>("Num");
		colCpt.setMinWidth(50);
		colCpt.setCellValueFactory(new PropertyValueFactory<>("cpt"));
				
		//Colonne idTweet
		TableColumn<Tweets, String> colIdTweet = new TableColumn<>("ID");
		colIdTweet.setMinWidth(300);
		colIdTweet.setCellValueFactory(new PropertyValueFactory<>("idTweet"));
		
		//Colonne idUser
		TableColumn<Tweets, String> colIdUser = new TableColumn<>("Utilisateur");
		colIdUser.setMinWidth(200);
		colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

		//Colonne dateTweet
		TableColumn<Tweets, LocalDateTime> colDate = new TableColumn<>("Date");
		colDate.setMinWidth(200);
		colDate.setCellValueFactory(new PropertyValueFactory<>("dateTweet"));
		
		//Colonne content
		TableColumn<Tweets, String> colContent = new TableColumn<>("Tweet");
		colContent.setMinWidth(2000);
		colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
		
		//Colonne idUserRT
		TableColumn<Tweets, String> colIdUserRt = new TableColumn<>("Utilisateur retweeté");
		colIdUserRt.setMinWidth(200);
		colIdUserRt.setCellValueFactory(new PropertyValueFactory<>("idUserRt"));
		
		t = new BaseDeTweets();
		t = t.lire("climat2.txt");
		
		table = new TableView<>();
		table.setItems(t.getTweets());
		table.getColumns().addAll(colCpt, colIdTweet, colIdUser, colDate, colContent, colIdUserRt);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
		

	}

}
