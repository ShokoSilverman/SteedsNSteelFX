package com.example.steedsnsteelfx.Controllers;

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
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    boolean attacking;
    public ArrayList<Button> allButtons = new ArrayList<>();
    public ArrayList<Unit_Normal> allUnits = new ArrayList<>();
    public Button[] focusedUnitBTN = new Button[1];
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
//        placeEnemies();
        battleGrid.add(trafficConeImageView(), 0,0);
    }

    @FXML
    private void unitUp(ActionEvent event) {
        if (!attacking) {
            int row = battleGrid.getRowIndex(focusedUnitBTN[0]);
            int column = battleGrid.getColumnIndex(focusedUnitBTN[0]);
            if(getNodeFromGridPane(battleGrid, column, row-1) == null){
                battleGrid.add(focusedUnitBTN[0], column, row-1);
            }else{}
        } else {
            Battle(getUnitFromNode(focusedUnitBTN[0]), getUnitFromNode(getAdjacent(focusedUnitBTN[0],1)));
        }
    }

    @FXML
    private void unitDown(ActionEvent event) {
        if (!attacking) {
            int row = battleGrid.getRowIndex(focusedUnitBTN[0]);
            int column = battleGrid.getColumnIndex(focusedUnitBTN[0]);
            if (row >= 8){
                return;
            }
            if(getNodeFromGridPane(battleGrid, column, row+1) == null){
                battleGrid.add(focusedUnitBTN[0], column, row+1);
            }else{}
        } else {
            Battle(getUnitFromNode(focusedUnitBTN[0]), getUnitFromNode(getAdjacent(focusedUnitBTN[0],3)));
        }
    }

    @FXML
    private void unitRight(ActionEvent event) {
        if (!attacking){
            int row = battleGrid.getRowIndex(focusedUnitBTN[0]);
            int column = battleGrid.getColumnIndex(focusedUnitBTN[0]);
            if (column >= 8){
                return;
            }
            if(getNodeFromGridPane(battleGrid, column+1, row) == null){
                battleGrid.add(focusedUnitBTN[0], column+1, row);
            }else{}
        } else {
            Battle(getUnitFromNode(focusedUnitBTN[0]), getUnitFromNode(getAdjacent(focusedUnitBTN[0],2)));
        }
    }

    @FXML
    private void unitLeft(ActionEvent event) {
        if (!attacking) {
            int row = battleGrid.getRowIndex(focusedUnitBTN[0]);
            int column = battleGrid.getColumnIndex(focusedUnitBTN[0]);
            if(getNodeFromGridPane(battleGrid, column-1, row) == null){
                battleGrid.add(focusedUnitBTN[0], column-1, row);
            }else{}
        } else {
            Battle(getUnitFromNode(focusedUnitBTN[0]), getUnitFromNode(getAdjacent(focusedUnitBTN[0],4)));
        }
    }

    /**
     * Check for adjacent figures in a defined direction
     * @param unitBTN Grid node to look from
     * @param direction Direction to look in - 1:North 2:East 3:South 4:West
     * @return Whether something is there.
     */
    private Button getAdjacent(Node unitBTN, int direction){
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
        try {
            return (Button)getNodeFromGridPane(battleGrid, col, row);
        } catch (Exception e){
            System.out.println("nothing in direction=" + direction);
        }
        return null;
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

    private void playerUnitSelect(ActionEvent e) {
        Button selectedBtn = (Button)e.getSource();
        Unit_Normal selectedUnit = getUnitFromNode(selectedBtn);
        System.out.println(selectedBtn.toString()); // dsaf
        System.out.println(selectedUnit.get_UnitID()); //fds
        System.out.println(selectedUnit.get_Type()); //fdhsf
        horseHealthLbl.setText(selectedUnit.get_HP()+"");
        maxHorseHealthLbl.setText(selectedUnit.get_MaxHealth()+"");
        turnDisplay.setText(selectedUnit.get_Name() + "'s Turn");
        if (focusedUnitBTN[0] != null){
            System.out.println("Uh Oh");
            focusedUnitBTN[0].setGraphic(getUnselectedView(focusedUnitBTN[0]));
        }
        selectedBtn.setGraphic(getSelectedView(selectedBtn));
        focusedUnitBTN[0] = selectedBtn;
        if (selectedUnit.get_Type() == eTileType.UNIT_P){
            buttonVisibility(true);
            healthVisibility(true);
        } else if (selectedUnit.get_Type() == eTileType.UNIT_E){
            buttonVisibility(false);
            healthVisibility(true);
        }
    }

    public void generateUnit(Unit_Normal unit, String ID, int spawnCol, int spawnRow){
        Button newBTN = new Button("", trafficConeImageView());
        unit.set_UnitID(ID);
        newBTN.setId(ID);

        battleGrid.add(newBTN, spawnCol,spawnRow);
        allButtons.add(newBTN);
        allUnits.add(unit);

        newBTN.setGraphic(getUnselectedView(newBTN));
        newBTN.setStyle("-fx-background-color: transparent;");
        newBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerUnitSelect(e);
            }
        });
    }

    private void placePieces(){

        Unit_Normal newUnit = new Unit_Normal(eTileType.UNIT_P, 30, 30, 3, 2, RandNameGen.generateName());
        generateUnit(newUnit, "unit" + 1, 1, 1);
        Unit_Normal newUnit2 = new Unit_Normal(eTileType.UNIT_P, 30, 30, 7, 2, RandNameGen.generateName());
        generateUnit(newUnit2, "unit" + 2, 1, 3);
        Unit_Normal newUnit3 = new Unit_Normal(eTileType.UNIT_P, 30, 30, 12, 2, RandNameGen.generateName());
        generateUnit(newUnit3, "unit" + 3, 1, 5);
        Unit_Normal newUnit4 = new Unit_Normal(eTileType.UNIT_P, 30, 30, 22, 2, RandNameGen.generateName());
        generateUnit(newUnit4, "unit" + 4, 1, 7);

        Unit_Normal newUnit5 = new Unit_Normal(eTileType.UNIT_E, 30, 30, 2, 2, RandNameGen.generateName());
        generateUnit(newUnit5, "unit" + 5, 6, 1);
        Unit_Normal newUnit6 = new Unit_Normal(eTileType.UNIT_E, 30, 30, 2, 2, RandNameGen.generateName());
        generateUnit(newUnit6, "unit" + 6, 6, 3);
        Unit_Normal newUnit7 = new Unit_Normal(eTileType.UNIT_E, 30, 30, 2, 2, RandNameGen.generateName());
        generateUnit(newUnit7, "unit" + 7, 6, 5);
        Unit_Normal newUnit8 = new Unit_Normal(eTileType.UNIT_E, 30, 30, 2, 2, RandNameGen.generateName());
        generateUnit(newUnit8, "unit" + 8, 6, 7);
    }

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private ImageView getSelectedView(Button btn) {
        Unit_Normal unit = getUnitFromNode(btn);
        switch (unit.get_Type()){
            case UNIT_P:
                return blueNightImageView();
            case UNIT_E:
                return redRockImageView();
        }
        return trafficConeImageView();
    }

    private ImageView getUnselectedView(Button btn) {
        Unit_Normal unit = getUnitFromNode(btn);
        switch (unit.get_Type()){
            case UNIT_P:
                return horseNightImageView();
            case UNIT_E:
                return rockImageView();
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
            upBtn.setDisable(!isEnemy(focusedUnitBTN[0], getAdjacent(focusedUnitBTN[0],1)));
            downBtn.setDisable(!isEnemy(focusedUnitBTN[0], getAdjacent(focusedUnitBTN[0],3)));
            leftBtn.setDisable(!isEnemy(focusedUnitBTN[0], getAdjacent(focusedUnitBTN[0],4)));
            rightBtn.setDisable(!isEnemy(focusedUnitBTN[0], getAdjacent(focusedUnitBTN[0],2)));
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

    public void Battle(Unit_Normal attacker, Unit_Normal defender) {
        int _damage = 0; //The amount of damage to be applied to defender hp
        int _resultHP = 0; //The amount of health the defender will have left, unless below 0, then 0.

        _damage = attacker.get_Atk() - defender.get_Def(); //Unit1 attack - Unit2 defence
        if (_damage < 0) _damage = 0; //If the defence is stronger than attack, prevent heal
        _resultHP = defender.get_HP() - _damage; //Find leftover HP defender will have

        if (_resultHP <= 0) { //Check if dead

            //TODO Killing a unit
            defender.set_HP(0); //Set health to no less than 0
            System.out.println("AHHH I'm dead! _resultHP=" + _resultHP + ", _damage=" + _damage); //Debug message

        } else { //Not dead! Deal damage.

            defender.set_HP(_resultHP); //Set new health
            System.out.println("Ouch I took damage! _resultHP=" + _resultHP + ", _damage=" + _damage); //Debug message
        }

        attacking = false;
        cancelAttack();
    }

    public boolean isEnemy(Button origin, Button target) {
        return origin != null && target != null
                && getUnitFromNode(origin).get_Type() != getUnitFromNode(target).get_Type();
    }
    public boolean isEnemy(Unit_Normal origin, Unit_Normal target){return origin.get_Type() != target.get_Type();}
}
