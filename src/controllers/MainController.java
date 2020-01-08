package controllers;
import application.*;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
	
	TableView<Tweets> table;
	private static BaseDeTweets t;
	Stage window;
	
	public void genererTweets(ActionEvent event) {
		
		window = new Stage();
		window.setTitle("Base de tweets");
				
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
		table.getColumns().addAll(colIdTweet, colIdUser, colDate, colContent, colIdUserRt);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();	
		
		
	}

}
