package com.example.steedsnsteelfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Image image = new Image(new FileInputStream("C:\\Users\\ssilverman\\IdeaProjects\\SteedsNSteelFX\\Data\\SteedsNSteelmainMenu.png"));
        //GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));//set root

        GridPane newRoot = new GridPane();
        primaryStage.setTitle("Steeds N' Steel");//title
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


        Button startButton = new Button("Accept");//creating button
        startButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        startButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        startButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        startButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/-25);//negative to go down, higher number more lower
        startButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                System.out.println("hello");
                primaryStage.close();
            }
        });
        newRoot.add(startButton,0,0);

        Button instructButton = new Button("Instructions");
        instructButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        instructButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        instructButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        instructButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/-50);//negative to go down, higher number more lower
        newRoot.add(instructButton,0,0);

        Scene scene = new Scene(newRoot, 600, 400, Color.BLACK);





        //primaryStage.setScene(new Scene(newRoot, 300, 275));//create and set scene
        primaryStage.setScene(scene);//sets scene
        primaryStage.setMaximized(true);//fullscreen
        primaryStage.show();//make screen visible


    }

    public static void main(String[] args) {
        launch();
    }


}