package controllers;
import application.*;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
	
	@FXML //Pour faire le lien avec le fichier FXML
	TableView<Tweets> table;
	/*@FXML
	TableColumn<Tweets, String> colIdTweet;
	@FXML
	TableColumn<Tweets, String> colIdUser;
	@FXML
	TableColumn<Tweets, LocalDateTime> colDate;
	@FXML
	TableColumn<Tweets, String> colContent;
	@FXML
	TableColumn<Tweets, String> colIdUserRt;*/
	
	//Stage window;
	private static BaseDeTweets t;
	
	public void genererTweets(ActionEvent event) {
		
		/*window = new Stage();
		window.setTitle("Base de tweets");*/
				
		//Colonne idTweet
		TableColumn<Tweets, String> colIdTweet = new TableColumn<>("ID");
		colIdTweet.setMinWidth(200);
		colIdTweet.setCellValueFactory(new PropertyValueFactory<>("idTweet"));
		
		//Colonne idUser
		TableColumn<Tweets, String> colIdUser = new TableColumn<>("Utilisateur");
		colIdUser.setMinWidth(100);
		colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

		//Colonne dateTweet
		TableColumn<Tweets, LocalDateTime> colDate = new TableColumn<>("Date");
		colDate.setMinWidth(100);
		colDate.setCellValueFactory(new PropertyValueFactory<>("dateTweet"));
		
		//Colonne content
		TableColumn<Tweets, String> colContent = new TableColumn<>("Tweet");
		colContent.setMinWidth(2000);
		colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
		
		//Colonne idUserRT
		TableColumn<Tweets, String> colIdUserRt = new TableColumn<>("Utilisateur retweeté");
		colIdUserRt.setMinWidth(100);
		colIdUserRt.setCellValueFactory(new PropertyValueFactory<>("idUserRt"));
		
		t = new BaseDeTweets();
		t = t.lire("climat2.txt");
		
		//table = new TableView<>();
		table.setItems(t.getTweets());
		table.getColumns().addAll(colIdTweet, colIdUser, colDate, colContent, colIdUserRt);
		
		/*Button nbTweets = new Button("Nombre de tweets");
		nbTweets.setText("Nombre de tweets");
		
		Button popularite = new Button("Popularité des utilisateurs");
		popularite.setText("Popularité des utilisateurs");
		
		Button frequenceHtag = new Button("Fréquence des Hashtags");
		frequenceHtag.setText("Fréquence des Hashtags");
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table, nbTweets, popularite, frequenceHtag);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();	*/
		
		
	}

}
