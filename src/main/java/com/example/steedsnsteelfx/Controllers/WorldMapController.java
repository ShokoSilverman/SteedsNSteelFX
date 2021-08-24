package com.example.steedsnsteelfx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WorldMapController {
    @FXML
    private Button lvlOneBtn;
    @FXML
    private Button lvlTwoBtn;
    @FXML
    private Button lvlThreeBtn;
    @FXML
    private Button lvlFourBtn;
    @FXML
    private Button lvlFiveBtn;
    @FXML
    private BorderPane mainPane;
    Stage newWindow = new Stage();

    @FXML
    private void LvlOneBtnClick(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/steedsnsteelfx/demoBattleGrid.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        newWindow.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        newWindow.setFullScreen(true);
        //newWindow.setMaximized(true);
        newWindow.show();

    }

    @FXML
    private void LvlTwoBtnClick(ActionEvent event){
        System.out.println("Gregs");

    }

    @FXML
    private void LvlThreeBtnClick(ActionEvent event){
        System.out.println("Megs");

    }

    @FXML
    private void LvlFourBtnClick(ActionEvent event){
        System.out.println("Sags");

    }

    @FXML
    private void LvlFiveBtnClick(ActionEvent event){
        System.out.println("Simon I could kick your ass");

    }






    /*
    URL url = new File("src/main/resources/com/example/steedsnsteelfx/demoBattleGrid.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(url);
        newWindow.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        newWindow.setFullScreen(true);
    //newWindow.setMaximized(true);
        newWindow.show();
    */
}
