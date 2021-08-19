package com.example.steedsnsteelfx.Controllers;

import com.example.steedsnsteelfx.Models.Credits;
import com.example.steedsnsteelfx.Models.RandNameGen;
import com.example.steedsnsteelfx.Models.Unit_Normal;
import com.example.steedsnsteelfx.Models.eTileType;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    int unitTurn;
    boolean attacking;
    public ArrayList<Button> allButtons = new ArrayList<>();
    public ArrayList<Unit_Normal> allUnits = new ArrayList<>();
//
//    @FXML
//    private Stage

    @FXML
    private AnchorPane mainPane;

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
    private Button attackBtn;

    @FXML
    private Button waitbutton;

    @FXML
    private Label turnDisplay;

    @FXML
    private ImageView heartImgView;

    @FXML
    private Line healthSlashLine;

    @FXML
    private Label horseHealthLbl;

    @FXML
    private Label maxHorseHealthLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Battle Initiated!");
        buttonVisibility(false);
        healthVisibility(false);
        placePieces();
        placeEnemies();
        battleGrid.add(trafficConeImageView(), 0,0);
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
        if (column >= 8){
            return;
        }
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

    /**
     * Check for adjacent figures in a defined direction
     * @param unitBTN Grid node to look from
     * @param direction Direction to look in - 1:North 2:East 3:South 4:West
     * @return Whether something is there.
     */
    private boolean isEnemyAdjacent(Node unitBTN, int direction){
        Integer col = GridPane.getColumnIndex(unitBTN);
        Integer row = GridPane.getRowIndex(unitBTN);
        switch (direction){
            case 1:
                row--;
                break;
            case 2:
                col++;
                break;
            case 3:
                row++;
                break;
            case 4:
                col--;
                break;
        }
        Unit_Normal originUnit = getUnitFromNode(unitBTN);
        Unit_Normal adjacentUnit = getUnitFromNode(getNodeFromGridPane(battleGrid, col, row));

        if (originUnit.get_Type() != adjacentUnit.get_Type()) {
            return true;
        }
        return false;
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
        attackBtn.setVisible(visibility);
        waitbutton.setVisible(visibility);
    }


    Unit_Normal unitOneObject = new Unit_Normal(eTileType.UNIT_P,"unitOne" , 20, 20,3, 4, RandNameGen.generateName());
    Unit_Normal unitTwoObject = new Unit_Normal(eTileType.UNIT_P,"unitTwo" , 15, 20,3, 4, RandNameGen.generateName());
    Unit_Normal unitThreeObject = new Unit_Normal(eTileType.UNIT_P,"unitThree" , 10, 20,3, 4, RandNameGen.generateName());

    Button unitOne;
    Button unitTwo;
    Button unitThree;

    private void placePieces(){


        unitOne = new Button("",horseNightImageView());
        unitOne.setId("unitOne");
        unitOne.setStyle("-fx-background-color: transparent;");
        unitOne.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                healthVisibility(true);
                horseHealthLbl.setText(unitOneObject.get_HP()+"");
                maxHorseHealthLbl.setText(unitOneObject.get_MaxHealth()+"");
                turnDisplay.setText(unitOneObject.get_Name() + "'s Turn");
                unitTurn = 1;
                unitOne.setGraphic(blueNightImageView());
                unitTwo.setGraphic(horseNightImageView());
                unitThree.setGraphic(horseNightImageView());


            }
        });
        battleGrid.add(unitOne, generateRandomIntIntRange(1,2),generateRandomIntIntRange(1,2));
        allButtons.add(unitOne);
        allUnits.add(unitOneObject);

        unitTwo = new Button("",horseNightImageView());
        unitTwo.setId("unitTwo");
        unitTwo.setStyle("-fx-background-color: transparent;");
        unitTwo.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                healthVisibility(true);
                horseHealthLbl.setText(unitTwoObject.get_HP()+"");
                maxHorseHealthLbl.setText(unitTwoObject.get_MaxHealth()+"");
                turnDisplay.setText(unitTwoObject.get_Name() + "'s Turn");
                unitTurn = 2;
                unitTwo.setGraphic(blueNightImageView());
                unitOne.setGraphic(horseNightImageView());
                unitThree.setGraphic(horseNightImageView());
            }
        });
        battleGrid.add(unitTwo, generateRandomIntIntRange(0,2),generateRandomIntIntRange(3,5));
        allButtons.add(unitTwo);
        allUnits.add(unitTwoObject);

        unitThree = new Button("",horseNightImageView());
        unitThree.setId("unitThree");
        unitThree.setStyle("-fx-background-color: transparent;");
        unitThree.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(true);
                healthVisibility(true);
                horseHealthLbl.setText(unitThreeObject.get_HP()+"");
                maxHorseHealthLbl.setText(unitThreeObject.get_MaxHealth()+"");
                turnDisplay.setText(unitThreeObject.get_Name() + "'s Turn");
                unitTurn = 3;
                unitThree.setGraphic(blueNightImageView());
                unitTwo.setGraphic(horseNightImageView());
                unitOne.setGraphic(horseNightImageView());
            }
        });
        battleGrid.add(unitThree, generateRandomIntIntRange(0,2),generateRandomIntIntRange(6,8));
        allButtons.add(unitThree);
        allUnits.add(unitThreeObject);
    }

    Unit_Normal unitFour = new Unit_Normal(eTileType.UNIT_E, "unitFour", 20, 20, 2, 2, "Burt's Dad");
    Unit_Normal unitFive = new Unit_Normal(eTileType.UNIT_E, "unitFive", 20, 20, 2, 2, "Burt 2");
    Unit_Normal unitSix = new Unit_Normal(eTileType.UNIT_E, "unitSix", 20, 20, 2, 2, "Burt 17");

    Button enemyOne;
    Button enemyTwo;
    Button enemyThree;

    private void placeEnemies(){
        enemyOne = new Button("",rockImageView());
        enemyOne.setId("unitFour");
        enemyOne.setStyle("-fx-background-color: transparent;");
        enemyOne.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                buttonVisibility(false);
                healthVisibility(true);
                horseHealthLbl.setText(unitFour.get_HP()+"");
                maxHorseHealthLbl.setText(unitFour.get_MaxHealth()+"");
                turnDisplay.setText(unitFour.get_Name() + "'s Turn");
                unitTurn = 3;
                for (Button btn : allButtons) {
                    btn.setGraphic(getUnselectedView(btn.getId()));
                }
                enemyOne.setGraphic(redRockImageView());
            }
        });
        battleGrid.add(enemyOne, generateRandomIntIntRange(6,8),generateRandomIntIntRange(0,2));
        allButtons.add(enemyOne);
        allUnits.add(unitFour);

        enemyTwo = new Button("",rockImageView());
        enemyTwo.setId("unitFive");
        enemyTwo.setStyle("-fx-background-color: transparent;");
        enemyTwo.setOnAction(new EventHandler<ActionEvent>() {//set what button does
            @Override public void handle(ActionEvent e) {
                System.out.println(this.getClass() + " | " + this); //AJIFDABFJDBAVNJI
                buttonVisibility(false);
                healthVisibility(true);
                horseHealthLbl.setText(unitFive.get_HP()+"");
                maxHorseHealthLbl.setText(unitFive.get_MaxHealth()+"");
                turnDisplay.setText(unitFive.get_Name() + "'s Turn");
                unitTurn = 3;
                for (Button btn : allButtons) {
                    btn.setGraphic(getUnselectedView(btn.getId()));
                }
                enemyTwo.setGraphic(redRockImageView());
            }
        });
        battleGrid.add(enemyTwo, generateRandomIntIntRange(6,8),generateRandomIntIntRange(3,5));
        allButtons.add(enemyTwo);
        allUnits.add(unitFive);

        enemyThree = new Button("",rockImageView());
        enemyThree.setId("unitSix");
        enemyThree.setStyle("-fx-background-color: transparent;");
        battleGrid.add(enemyThree, generateRandomIntIntRange(6,8),generateRandomIntIntRange(6,8));
        allButtons.add(enemyThree);
        allUnits.add(unitSix);

    }

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private ImageView getUnselectedView(String btnID) {
        for (Unit_Normal unit : allUnits) {
            if (unit.get_UnitID().equals(btnID)){
                if (unit.get_Type() == eTileType.UNIT_P){
                    return horseNightImageView();
                } else if (unit.get_Type() == eTileType.UNIT_E) {
                    return rockImageView();
                }
            }
        }
        return trafficConeImageView();
    }

    private ImageView horseNightImageView(){

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

    private ImageView blueNightImageView(){
        Image blueImg = null;
        try {
            blueImg = new Image(new FileInputStream("Data/HLHorsePaladin.png"));
        } catch (FileNotFoundException ef) {
            ef.printStackTrace();
        }
        ImageView unitImageView = new ImageView(blueImg);
        System.out.println(536/9);
        unitImageView.setFitHeight(536/6);
        unitImageView.setPreserveRatio(true);
        return unitImageView;
    }

    private ImageView redRockImageView(){

        try {
            Image unitOneImage = new Image(new FileInputStream("Data/RedRockFarmer.png"));
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

    private ImageView rockImageView(){

        try {
            Image unitOneImage = new Image(new FileInputStream("Data/RockFarmer.png"));
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

    private ImageView trafficConeImageView(){
        Image unitOneImage = null;
        try {
            unitOneImage = new Image(new FileInputStream("Data/TrafficCone.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView unitImageView = new ImageView(unitOneImage);
        System.out.println(536/9);
        unitImageView.setFitHeight(536/6);
        unitImageView.setPreserveRatio(true);
        return unitImageView;
    }

    private void healthVisibility(boolean visibility){
        heartImgView.setVisible(visibility);
        healthSlashLine.setVisible(visibility);
        horseHealthLbl.setVisible(visibility);
        maxHorseHealthLbl.setVisible(visibility);
    }

    /**
     * Returns Unit paired with button by ID
     * @param button Unit to look for
     * @return Unit with matching ID
     */
    public Unit_Normal getUnitFromNode(Node button){
        for (Unit_Normal unit : allUnits) {
            if (button.getId().equals(unit.get_UnitID())) return unit;
        }
        return null;
    }

    public void attack(ActionEvent actionEvent) {
        if (attacking) {
            cancelAttack();
        } else {
            attacking = true;
            attackBtn.setText("Cancel");
            upBtn.setDisable(true);
            downBtn.setDisable(true);
            leftBtn.setDisable(true);
            rightBtn.setDisable(true);

            //Check around player and see where enemies are
        }
    }

    public void disable(ActionEvent actionEvent) {
    }

    public void cancelAttack(){
        attacking = false;
        attackBtn.setText("Attack");
        upBtn.setDisable(false);
        downBtn.setDisable(false);
        leftBtn.setDisable(false);
        rightBtn.setDisable(false);
    }
}
