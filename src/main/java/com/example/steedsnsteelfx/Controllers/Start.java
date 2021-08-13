package com.example.steedsnsteelfx.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Start  {


    public void run(Stage newWindow) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("hello-view.fxml"));

//Set view in window
        newWindow.setScene(new Scene(root, 400, 400));
        //Launch
        newWindow.show();

//        Stage stage = new Stage();
//        stage.show();
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));//set root
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.setMaximized(true);
////            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(Start.class.getResource("hello-view.fxml"));//set root
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//    }
}
