package com.example.steedsnsteelfx.Controllers;



import com.example.steedsnsteelfx.Models.Credits;
import com.example.steedsnsteelfx.Models.Instructions;
import com.example.steedsnsteelfx.Controllers.Start;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
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

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {




        Image image = new Image(new FileInputStream("Data/SteedsNSteelbasicmm.png"));
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


        Image startButtonImage = new Image(new FileInputStream("Data/StartBoard.png"));
        ImageView startButtonImageView = new ImageView(startButtonImage);
        Button startButton = new Button("", startButtonImageView);//creating button
        startButton.setStyle("-fx-background-color: transparent;");
        startButton.setMinHeight(startButtonImage.getHeight());
        startButton.setMinWidth(startButtonImage.getWidth());
        startButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/2.77);//higher number, more left
        startButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/-23);//negative to go down, higher number more lower
        startButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
//                Stage stage = new Stage();
                try {
                    new Start().run(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //primaryStage.close();
            }
        });
        newRoot.add(startButton,0,0);

        //this is an obvious change

        Image creditsButtonImage = new Image(new FileInputStream("Data/CreditsBoard.png"));
        ImageView creditsButtonImageView = new ImageView(creditsButtonImage);
        Button creditsButton = new Button("",creditsButtonImageView);
        creditsButton.setStyle("-fx-background-color: transparent;");
        creditsButton.setMinHeight(creditsButtonImage.getHeight());
        creditsButton.setMinWidth(creditsButtonImage.getWidth());
        creditsButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/2.77);//higher number, more left
        creditsButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/5);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                new Credits().run(primaryStage);
                //primaryStage.close();
            }
        });
        newRoot.add(creditsButton,0,0);


        Image howToPlayButtonImage = new Image(new FileInputStream("Data/HowToPlayBoard.png"));
        ImageView howToPlayButtonImageView = new ImageView(howToPlayButtonImage);
        Button instructButton = new Button("", howToPlayButtonImageView);
        instructButton.setStyle("-fx-background-color: transparent;");
        instructButton.setMinHeight(howToPlayButtonImage.getHeight());
        instructButton.setMinWidth(howToPlayButtonImage.getWidth());
        instructButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/2.77);//higher number, more left
        instructButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/13);
        instructButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                try {
                    new Instructions().run(primaryStage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //primaryStage.close();
            }
        });
        newRoot.add(instructButton,0,0);

        Image quitButtonImage = new Image(new FileInputStream("Data/ExitBoard.png"));
        ImageView quitButtonImageView = new ImageView(quitButtonImage);
        Button quitButton = new Button("",quitButtonImageView);
        quitButton.setStyle("-fx-background-color: transparent;");
        quitButton.setMinHeight(quitButtonImage.getHeight());
        quitButton.setMinWidth(quitButtonImage.getWidth());
        quitButton.setTranslateX(Screen.getPrimary().getVisualBounds().getWidth()/2.77);//higher number, more left
        quitButton.setTranslateY(Screen.getPrimary().getVisualBounds().getHeight()/3);
        quitButton.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                System.out.println("Hello");
                primaryStage.close();
                System.exit(0);
            }
        });
        newRoot.add(quitButton,0,0);


        Scene scene = new Scene(newRoot, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight(), Color.BLACK);
//
//        Image img = new Image(getClass().getResourceAsStream("C:\\Users\\jwilliams\\OneDrive\\Documents\\Y1 Neumont\\Quarter 4 Summer\\3 - Projects\\SteedsNSteelFX\\Data\\pixelGauntletCursor.png"));
////        Image imageCursor = new Image("Data/pixelGauntletCursor.png");  //pass in the image path
//        ImageCursor imgCurs = new ImageCursor(img, 46, 46);
//        scene.setCursor(imgCurs);



        //primaryStage.setScene(new Scene(newRoot, 300, 275));//create and set scene
        primaryStage.setScene(scene);//sets scene
        primaryStage.setMaximized(true);//fullscreen

        primaryStage.show();//make screen visible


    }

    public static void main(String[] args) {
        launch();
    }


}