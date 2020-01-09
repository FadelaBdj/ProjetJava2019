package controllers;
import java.util.ArrayList;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
		b = b.lire("climat2.txt");

		nuagePoints.setData(b.getChartData());
		
	}
	
	
	

}
