package com.example.steedsnsteelfx.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Start  {
    public void run(Stage newWindow) throws IOException {
        URL url = new File("src/main/resources/com/example/steedsnsteelfx/WorldMapResized.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        newWindow.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        newWindow.setFullScreen(true);
        //newWindow.setMaximized(true);
        newWindow.show();
    }
}
