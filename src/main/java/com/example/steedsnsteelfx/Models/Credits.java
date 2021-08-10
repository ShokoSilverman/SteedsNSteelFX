package com.example.steedsnsteelfx.Models;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Credits {

    public void run(){
        Stage htpStage = new Stage();

        Image image = null;
        try {
            image = new Image(new FileInputStream("Data/Credits.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));//set root

        GridPane newRoot = new GridPane();
        htpStage.setTitle("Steeds N' Steel How To Play");//title
        //root.add(button2,1,1);//adding button
        ImageView imageView1 = new ImageView(image);//creates image
        imageView1.setX(0);//set image in top left corner
        imageView1.setY(0);//
        imageView1.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());//fill screen vertically
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        imageView1.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());//fill screen horizontally
        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        imageView1.setPreserveRatio(true);
        //Group newRoot = new Group(imageView1);
        newRoot.add(imageView1,0,0);//adds image to gridpane

        Scene scene = new Scene(newRoot, 600, 400, Color.BLACK);

        htpStage.setScene(scene);//sets scene
        htpStage.setMaximized(true);//fullscreen
        htpStage.show();//make screen visible

//        htpStage.setMaximized(true);

    }
}
