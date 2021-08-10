package com.example.steedsnsteelfx.Models;

import com.example.steedsnsteelfx.Controllers.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Instructions {

    public void run() throws IOException {

        Stage htpStage = new Stage();

        Image image = new Image(new FileInputStream("Data/Pre-HTP.png"));
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

        Image quitButtonImage = new Image(new FileInputStream("Data/BackButton.png"));
        ImageView quitButtonImageView = new ImageView(quitButtonImage);
        Button quitButton = new Button("", quitButtonImageView);
        quitButton.setStyle("-fx-background-color: transparent;");
        quitButton.setLayoutX(0);
        quitButton.setTranslateY(-(Screen.getPrimary().getVisualBounds().getHeight())/2.5);
        quitButton.setMinWidth(quitButtonImage.getWidth()/2);

        quitButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                Stage primStage = new Stage();
                try {
                    new Main().start(primStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                htpStage.close();
            }
        });
        newRoot.add(quitButton,0,0);

        Scene scene = new Scene(newRoot, 600, 400, Color.BLACK);

        htpStage.setScene(scene);//sets scene
        htpStage.setMaximized(true);//fullscreen
        htpStage.show();//make screen visible

//        htpStage.setMaximized(true);

    }
}

