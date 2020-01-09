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
	private TableView<Tweets> table;
	@FXML
	private Label lNbTweets;
	@FXML
	private Button bPopulaires;
	
	Stage window;
	
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
		TableColumn<Tweets, String> colIdUserRt = new TableColumn<>("Utilisateur retweet�");
		colIdUserRt.setMinWidth(100);
		colIdUserRt.setCellValueFactory(new PropertyValueFactory<>("idUserRt"));
		
		t = new BaseDeTweets();
		t = t.lire("climat3.txt");
		
		//table = new TableView<>();
		table.setItems(t.getTweets());
		table.getColumns().addAll(colIdTweet, colIdUser, colDate, colContent, colIdUserRt);
		
		String nb_tweets = "Nombre de tweets charg�s : " + Integer.toString(t.nb_tweets());
		lNbTweets.setText(nb_tweets);
		bPopulaires.setText("Popularit� des utilisateurs");
		
		/*Button nbTweets = new Button("Nombre de tweets");
		nbTweets.setText("Nombre de tweets");
		
		Button popularite = new Button("Popularit� des utilisateurs");
		popularite.setText("Popularit� des utilisateurs");
		
		Button frequenceHtag = new Button("Fr�quence des Hashtags");
		frequenceHtag.setText("Fr�quence des Hashtags");
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table, nbTweets, popularite, frequenceHtag);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();	*/
		
	}
	
	public void bPopulaires(ActionEvent event) {
		
		window = new Stage();
		window.setTitle("Popularit� des utilisateurs");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/popularite.fxml"));
			Scene scene = new Scene(root);
			window.setTitle("Popularit� des utilisateurs");
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
