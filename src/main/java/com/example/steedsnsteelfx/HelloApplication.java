package com.example.steedsnsteelfx;

import Controllers.Start;
import Models.Credits;
import Models.Instructions;
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
        Image image = new Image(new FileInputStream("C:\\Users\\jwilliams\\OneDrive\\Documents\\Y1 Neumont\\Quarter 4 Summer\\3 - Projects\\SteedsNSteelFX\\Data\\SteedsNSteelmainMenu.png"));
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


        Button startButton = new Button("Start");//creating button
        startButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        startButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        startButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        startButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/-25);//negative to go down, higher number more lower
        startButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                new Start().run();
                primaryStage.close();
            }
        });
        newRoot.add(startButton,0,0);

        //this is an obvious change

        Button creditsButton = new Button("Credits");
        creditsButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        creditsButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        creditsButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        creditsButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/5);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                new Credits().run();
                primaryStage.close();
            }
        });
        newRoot.add(creditsButton,0,0);


        Button instructButton = new Button("How To Play");
        instructButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        instructButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        instructButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        instructButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/13);
        instructButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                try {
                    new Instructions().run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                primaryStage.close();
            }
        });
        newRoot.add(instructButton,0,0);

        Button quitButton = new Button("Quit");
        quitButton.setMinHeight(Screen.getPrimary().getVisualBounds().getHeight()/13);//higher number, smaller button
        quitButton.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth()/2.4);//higher number, smaller button
        quitButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/3.45);//higher number, more left
        quitButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/3.2);
        quitButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                System.out.println("Quiting");
                primaryStage.close();
                System.exit(0);
            }
        });
        newRoot.add(quitButton,0,0);


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