package com.example.steedsnsteelfx.Controllers;

import com.example.steedsnsteelfx.Models.Credits;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    int unitTurn;

    @FXML
    private GridPane battleGrid;

    @FXML
    private Button upBtn;

    @FXML
    private Button downBtn;

    @FXML
    private Button rightBtn;

    @FXML
    private Button leftBtn;

    @FXML
    private Label turnDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Battle Initiated!");
        buttonVisibility(false);
        placePieces();
    }

    public Node turnTrack(){
        if (unitTurn == 1){
            return unitOne;
        }else if(unitTurn == 2){
            return unitTwo;
        }else{
            return unitThree;
        }
    }

    @FXML
    private void unitUp(ActionEvent event) {
        int row = battleGrid.getRowIndex(turnTrack());
        int column = battleGrid.getColumnIndex(turnTrack());
        if(getNodeFromGridPane(battleGrid, column, row-1) == null){
            battleGrid.add(turnTrack(), column, row-1);
        }else{

        }
    }

    @FXML
    private void unitDown(ActionEvent event) {
        int row = battleGrid.getRowIndex(turnTrack());
        int column = battleGrid.getColumnIndex(turnTrack());
        if (row >= 8){
            return;
        }
        if(getNodeFromGridPane(battleGrid, column, row+1) == null){
            battleGrid.add(turnTrack(), column, row+1);
        }else{

        }


    }

    @FXML
    private void unitRight(ActionEvent event) {
        int row = battleGrid.getRowIndex(turnTrack());
        int column = battleGrid.getColumnIndex(turnTrack());
        if(getNodeFromGridPane(battleGrid, column+1, row) == null){
            battleGrid.add(turnTrack(), column+1, row);
        }else{

        }

    }

    @FXML
    private void unitLeft(ActionEvent event) {
        int row = battleGrid.getRowIndex(turnTrack());
        int column = battleGrid.getColumnIndex(turnTrack());
        if(getNodeFromGridPane(battleGrid, column-1, row) == null){
            battleGrid.add(turnTrack(), column-1, row);
        }else{

        }



    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            if (columnIndex == null)
                columnIndex = 0;
            if (rowIndex == null)
                rowIndex = 0;

            if (columnIndex == col && rowIndex == row) {
                return node;
            }
        }
        return null;
    }

    public void buttonVisibility(boolean visibility){
        upBtn.setVisible(visibility);
        downBtn.setVisible(visibility);
        rightBtn.setVisible(visibility);
        leftBtn.setVisible(visibility);
    }

    Button unitOne;
    Button unitTwo;
    Button unitThree;

    private void placePieces(){

        unitOne = new Button("",horseNightImageView());
        unitOne.setStyle("-fx-background-color: transparent;");
        unitOne.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                turnDisplay.setText("Unit 1's Turn");
                unitTurn = 1;
            }
        });
        battleGrid.add(unitOne, 0,1);

        unitTwo = new Button("",horseNightImageView());
        unitTwo.setStyle("-fx-background-color: transparent;");
        unitTwo.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                turnDisplay.setText("Unit 2's Turn");
                unitTurn = 2;
            }
        });
        battleGrid.add(unitTwo, 1,4);

        unitThree = new Button("",horseNightImageView());
        unitThree.setStyle("-fx-background-color: transparent;");
        unitThree.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                turnDisplay.setText("Unit 3's Turn");
                unitTurn = 3;
            }
        });
        battleGrid.add(unitThree, 1,7);

    }

    public ImageView horseNightImageView(){

        try {
            Image unitOneImage = new Image(new FileInputStream("Data/HorsePaladin.png"));
            ImageView unitImageView = new ImageView(unitOneImage);
            System.out.println(536/9);
            unitImageView.setFitHeight(536/6);
            unitImageView.setPreserveRatio(true);
            return unitImageView;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
