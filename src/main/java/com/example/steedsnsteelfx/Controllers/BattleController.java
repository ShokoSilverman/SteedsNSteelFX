package com.example.steedsnsteelfx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    @FXML
    private GridPane battleGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Battle Initiated!");
        placePieces();
    }

    @FXML
    public void moveRight(ActionEvent actionEvent) {
        //TODO DONT TOUCH

    }

    private void placePieces(){
        try {
            Image unitOneImage = new Image(new FileInputStream("Data/HorsePaladin.png"));
            ImageView unitOneImageView = new ImageView(unitOneImage);
            System.out.println(536/9);
            unitOneImageView.setFitHeight(536/6);
            unitOneImageView.setPreserveRatio(true);
            Button unitOne = new Button("",unitOneImageView);

            unitOne.setStyle("-fx-background-color: transparent;");
            battleGrid.add(unitOne, 0,0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
