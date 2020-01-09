package controllers;
import java.util.ArrayList;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PopulariteController {
	
    @FXML
    private ScatterChart<String, Integer> nuagePoints;
	
	private BaseDeTweets b;
	private ArrayList a;
	
	public void bAfficher(ActionEvent event) {
		
		//nuagePoints.getData().clear();
		
		b = new BaseDeTweets();
		b = b.lire("climat3.txt");
		
		nuagePoints.setData(b.getChartData());
		
		/*XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data(new PropertyValueFactory<>("nom"), new PropertyValueFactory<>("nbRt")));		
		//series.setData(b.getUsersRt());
		
		b = new BaseDeTweets();
		b = b.lire("climat2.txt");
		
		//nuagePoints.setData(ObservableList<Series<Object,Object>>value);
		nuagePoints.setUserData(b.getUsersRt());
		nuagePoints.getData().add(series);		*/		
		
		/*
		b = new BaseDeTweets();
		a = b.populaires();
		
		int i = 0;
		String nom;
		Integer nbRt;
		XYChart.Series series = new XYChart.Series();
		for(i = 0; i < a.size(); i++) {
			UtilisateursRt u = (UtilisateursRt)a.get(i);
			nom = u.getNom();
			nbRt = (Integer) u.getNbRt();
			series.getData().add(new XYChart.Data(nom, nbRt));
		}
		//series.getData().add(new XYChart.Data("1", 12));
		nuagePoints.getData().add(series);
		*/
	}
	
	
	

}
