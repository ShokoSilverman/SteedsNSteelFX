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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    static int MAP_SETUP=0; //0 - debug, 1 - level 1
    boolean attacking, playerTurn, enemyTurn, turnCycle, win, lose;

    public ArrayList<Button> allButtons = new ArrayList<>();
    public ArrayList<Unit_Normal> allUnits = new ArrayList<>();
    public Button[] focusedUnitBTN = new Button[1];
//
//    @FXML
//    private Stage

    @FXML
    private Label unitATKlbl, unitDEFlbl, turnDisplay, horseHealthLbl, maxHorseHealthLbl
            , movesLbl, numberOfActionsLbl, BRatklbl, BRdeflbl, BRatkhplbl, BRdefhplbl;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private GridPane battleGrid;

    @FXML
    private Button upBtn, downBtn, rightBtn, leftBtn, attackBtn, waitbutton;

    @FXML
    private ImageView heartImgView, BRatkimg, BRdefimg, imgLoseScreen, imgVictoryScreen;

    @FXML
    private Line healthSlashLine;

    @FXML
    private HBox battlelogbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Battle Initiated!");
        buttonVisibility(false);
        statVisibility(false);
        statVisibility(false);
        placePieces();
        playerTurn = true;
        turnCycle = false;
//        placeEnemies();
        battleGrid.add(setImageView("Data/TrafficCone.png"), 0,0);
        battlelogbox.setVisible(false);
        createObstacles();
    }

    @FXML
    private void unitUp(ActionEvent event) {
        move(focusedUnitBTN[0], 1);
        turnOver(focusedUnitBTN[0]);
    }

    @FXML
    private void unitDown(ActionEvent event) {
        move(focusedUnitBTN[0], 3);
        turnOver(focusedUnitBTN[0]);
    }

    @FXML
    private void unitRight(ActionEvent event) {
        move(focusedUnitBTN[0], 2);
        turnOver(focusedUnitBTN[0]);
    }

    @FXML
    private void unitLeft(ActionEvent event) {
        move(focusedUnitBTN[0], 4);
        turnOver(focusedUnitBTN[0]);
    }

    /**
     * Call to move focused unit in the desired direction if available
     * @param direction Direction to move in - 1:North 2:East 3:South 4:West
     */
    private void move(Button btn, int direction) {
        Unit_Normal unit = getUnitFromNode(btn);

        try {
            if (!attacking) {
                int row = battleGrid.getRowIndex(btn);
                int column = battleGrid.getColumnIndex(btn);
                switch (direction) {
                    case 1: if(row <= 0){return;} else{row--;} break;
                    case 2: if(column >= 8){return;} else{column++;} break;
                    case 3: if(row >= 8){return;} else{row++;} break;
                    case 4: if(column <= 0){return;} else{column--;} break;
                }
                if (getNodeFromGridPane(battleGrid, column, row) == null) {
                    setMovesLeft(unit);
                    battleGrid.add(btn, column, row);
                }
            } else {
                Battle(unit, getUnitFromNode(getAdjacent(btn, direction)));
                setMovesLeft(unit);
            }
        }catch(Exception e){
            System.err.println("fUck");
        }
    }

    /**
     * Check for adjacent figures in a defined direction
     * @param unitBTN Grid node to look from
     * @param direction Direction to look in - 1:North 2:East 3:South 4:West
     * @return Whether something is there.
     */
    private Node getAdjacent(Node unitBTN, int direction){
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
            return getNodeFromGridPane(battleGrid, col, row);
        } catch (Exception e){
            System.out.println("nothing in direction=" + direction);
        }
        return null;
    }

    /**
     * Looks adjacent to Node in all directions, if another node is found false : otherwise true
     * @param unitBTN Node to check from
     * @return array of true or false in sequence of North East South West
     */
    private boolean[] checkAvailableAdjacent(Node unitBTN){
        boolean[] adj = new boolean[4];
        for (int i = 0; i < 4; i++) {
            try {
                Node node = getAdjacent(unitBTN, i + 1);
                if (node == null) {
                    adj[i] = true;
                } else {
                    adj[i] = false;
                }
            } catch (Exception e) {
                System.err.println("ah sHit");
            }
        }
        return adj;
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
        movesLbl.setVisible(visibility);
        numberOfActionsLbl.setVisible(visibility);
    }

    private void playerUnitSelect(ActionEvent e) {
        cancelAttack();
        Button selectedBtn = (Button)e.getSource();
        Unit_Normal selectedUnit = getUnitFromNode(selectedBtn);
        System.out.println(selectedBtn.toString()); // dsaf
        System.out.println(selectedUnit.get_UnitID()); //fds
        System.out.println(selectedUnit.get_Type()); //fdhsf
        horseHealthLbl.setText(selectedUnit.get_HP()+"");
        maxHorseHealthLbl.setText(selectedUnit.get_MaxHealth()+"");
        turnDisplay.setText(selectedUnit.get_Name() + "'s Turn");
        numberOfActionsLbl.setText(selectedUnit.get_Actions()+"");

        if (focusedUnitBTN[0] != null){
            System.out.println("Uh Oh");
            Unit_Normal unit = getUnitFromNode(focusedUnitBTN[0]);
            focusedUnitBTN[0].setGraphic(setImageView(unit.getImagePath(false)));
        }
        selectedBtn.setGraphic(setImageView(selectedUnit.getImagePath(true)));
        focusedUnitBTN[0] = selectedBtn;
        if(getUnitFromNode(focusedUnitBTN[0]).get_Actions()<=0){
            buttonVisibility(false);
            return;
        }
        if (selectedUnit.get_Type() == eTileType.UNIT_P){
            buttonVisibility(true);
            statVisibility(true);
        } else if (selectedUnit.get_Type() == eTileType.UNIT_E){
            buttonVisibility(false);
            statVisibility(true);
        }
        unitATKlbl.setText("Atk: " + selectedUnit.get_Atk());
        unitDEFlbl.setText("Def: " + selectedUnit.get_Def());
    }

    public void generateUnit(Unit_Normal unit, String ID, int spawnCol, int spawnRow){
        Button newBTN = new Button("", setImageView(unit.getImagePath(false)));
        unit.set_UnitID(ID);
        newBTN.setId(ID);

        battleGrid.add(newBTN, spawnCol,spawnRow);
        allButtons.add(newBTN);
        allUnits.add(unit);

        newBTN.setGraphic(setImageView(unit.getImagePath(false)));
        newBTN.setStyle("-fx-background-color: transparent;");
        newBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                playerUnitSelect(e);
            }
        });
    }

    private void placePieces(){
        switch (MAP_SETUP) {
            case 0:
                Unit_Normal newUnit01 = new Unit_Normal(eTileType.UNIT_P, 10, 4, 2
                        , RandNameGen.generateName(), 3, "/Data/HorseImagePaths.txt");
                generateUnit(newUnit01, "unit01", 1, 3);

                Unit_Normal newUnit02 = new Unit_Normal(eTileType.UNIT_E, 10, 4, 2
                        , RandNameGen.generateName(), 3, "/Data/RFarmerImagePaths.txt");
                generateUnit(newUnit02, "unit02", 7, 5);
                break;
            case 1:
                Unit_Normal newUnit11 = new Unit_Normal(eTileType.UNIT_P, 30, 3, 2
                        , RandNameGen.generateName(), 5, "/Data/HorseImagePaths.txt");
                generateUnit(newUnit11, "unit" + 1, 1, 1);
                Unit_Normal newUnit12 = new Unit_Normal(eTileType.UNIT_P, 30, 7, 2
                        , RandNameGen.generateName(), 5, "/Data/HorseImagePaths.txt");
                generateUnit(newUnit12, "unit" + 2, 1, 3);
                Unit_Normal newUnit13 = new Unit_Normal(eTileType.UNIT_P, 30, 12, 2
                        , RandNameGen.generateName(), 5, "/Data/HorseImagePaths.txt");
                generateUnit(newUnit13, "unit" + 3, 1, 5);
                Unit_Normal newUnit14 = new Unit_Normal(eTileType.UNIT_P, 30, 22, 2
                        , RandNameGen.generateName(), 5, "/Data/HorseImagePaths.txt");
                generateUnit(newUnit14, "unit" + 4, 1, 7);

                Unit_Normal newUnit15 = new Unit_Normal(eTileType.UNIT_E, 30, 22, 2
                        , RandNameGen.generateName(), 5, "/Data/RFarmerImagePaths.txt");
                generateUnit(newUnit15, "unit" + 5, 7, 1);
                Unit_Normal newUnit16 = new Unit_Normal(eTileType.UNIT_E, 30, 12, 2
                        , RandNameGen.generateName(), 5, "/Data/RFarmerImagePaths.txt");
                generateUnit(newUnit16, "unit" + 6, 7, 3);
                Unit_Normal newUnit17 = new Unit_Normal(eTileType.UNIT_E, 30, 7, 2
                        , RandNameGen.generateName(), 5, "/Data/RFarmerImagePaths.txt");
                generateUnit(newUnit17, "unit" + 7, 7, 5);
                Unit_Normal newUnit18 = new Unit_Normal(eTileType.UNIT_E, 30, 3, 2
                        , RandNameGen.generateName(), 5, "/Data/RFarmerImagePaths.txt");
                generateUnit(newUnit18, "unit" + 8, 7, 7);
                break;
        }
    }

    public int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean headsOrTails() {
        boolean b = Math.random() < 0.5;
        System.out.println(b);
        return b;
    }

    private ImageView setImageView(String fileName){
        Image unitOneImage = null;
        try {
            unitOneImage = new Image(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView unitImageView = new ImageView(unitOneImage);
        unitImageView.setFitHeight(536/6);
        unitImageView.setPreserveRatio(true);
        return unitImageView;
    }

    private void statVisibility(boolean visibility){
        heartImgView.setVisible(visibility);
        healthSlashLine.setVisible(visibility);
        horseHealthLbl.setVisible(visibility);
        maxHorseHealthLbl.setVisible(visibility);
        unitATKlbl.setVisible(visibility);
        unitDEFlbl.setVisible(visibility);
        turnDisplay.setVisible(visibility);
    }

    /**
     * Returns Unit paired with Node by ID
     * @param node Node to use for search
     * @return Unit with matching ID
     */
    public Unit_Normal getUnitFromNode(Node node){
        for (Unit_Normal unit : allUnits) {
            Button btn = (Button) node;
            if (btn.getId().equals(unit.get_UnitID())) return unit;
        }
        return null;
    }

    /**
     * Returns Node paired with Unit by ID
     * IF IT'S ERRING, TRY CASTING AS A BUTTON BEFORE TELLING ME IT DOESN'T WORK, PLS.
     * @param unit Unit to use for search
     * @return Node with matching ID
     */
    public Node getNodeFromUnit(Unit_Normal unit){
        for (Node node : allButtons){
            if (unit.get_UnitID().equals(node.getId())) return node;
        }
        return null;
    }

    /**
     * Returns ArrayList of Buttons with matching type
     * @param type eTileType - compares to
     * @return all buttons of equal type
     */
    public ArrayList<Button> getAllType(eTileType type){
        ArrayList<Button> allType = new ArrayList<>();
        for (Button btn : allButtons) {
            if (getUnitFromNode(btn).get_Type() == type) {
                allType.add(btn);
            }
        }
        return allType;
    }

    public void attack(ActionEvent actionEvent) {
        if (attacking) {
            cancelAttack();
        } else {
            attacking = true;
            attackBtn.setText("Cancel");
            upBtn.setDisable(!isEnemy(focusedUnitBTN[0], (Button)getAdjacent(focusedUnitBTN[0],1)));
            downBtn.setDisable(!isEnemy(focusedUnitBTN[0], (Button)getAdjacent(focusedUnitBTN[0],3)));
            leftBtn.setDisable(!isEnemy(focusedUnitBTN[0], (Button)getAdjacent(focusedUnitBTN[0],4)));
            rightBtn.setDisable(!isEnemy(focusedUnitBTN[0], (Button)getAdjacent(focusedUnitBTN[0],2)));
        }
    }

    public void disable(ActionEvent actionEvent) {
        getUnitFromNode(focusedUnitBTN[0]).set_Actions(0);
        turnOver(focusedUnitBTN[0]);
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
        int _damageATK = 0; //The amount of damage to be applied to defender hp
        int _resultHPATK = 0; //The amount of health the attacker will have left, unless below 0, then 0.
        int _damageDEF = 0; //The amount of damage to be applied to attacker hp
        int _resultHPDEF = 0; //The amount of health the defender will have left, unless below 0, then 0.

        _damageATK = attacker.get_Atk() - defender.get_Def(); //Unit1 attack - Unit2 defence
        _damageDEF = defender.get_Atk() - attacker.get_Def(); //Unit2 attack - Unit1 defence
        if (_damageATK < 0) _damageATK = 0; //If the defence is stronger than attack, prevent heal
        if (_damageDEF < 0) _damageDEF = 0; //If the defence is stronger than attack, prevent heal
        _resultHPDEF = defender.get_HP() - _damageATK; //Find leftover HP defender will have
        _resultHPATK = attacker.get_HP() - _damageDEF; //Find leftover HP attacker will have

        BRatkhplbl.setText(attacker.get_HP() + " -> ");
        BRdefhplbl.setText(defender.get_HP() + " -> ");

        if (_resultHPDEF <= 0) { //Check if dead

            defender.set_HP(0); //Set health to no less than 0
            System.out.println("AHHH I'm dead! _resultHP=" + _resultHPDEF + ", _damageATK=" + _damageATK); //Debug message
            defender.set_Alive(false);
            battleGrid.getChildren().remove(getNodeFromUnit(defender));

        } else { //Not dead! Deal damage.

            defender.set_HP(_resultHPDEF); //Set new health
            System.out.println("Ouch I took damage! _resultHP=" + _resultHPDEF + ", _damageATK=" + _damageATK); //Debug message

            if (_resultHPATK <= 0) { //Check if dead

                attacker.set_HP(0); //Set health to no less than 0
                System.out.println("AHHH I'm dead! _resultHP=" + _resultHPATK + ", _damageATK=" + _damageDEF); //Debug message
                attacker.set_Alive(false);
                battleGrid.getChildren().remove(getNodeFromUnit(attacker));

            } else { //Not dead! Deal damage.

                attacker.set_HP(_resultHPATK); //Set new health
                System.out.println("Ouch I took damage! _resultHP=" + _resultHPATK + ", _damageATK=" + _damageDEF); //Debug message

            }
        }

        horseHealthLbl.setText(_resultHPATK + "");
        System.out.println("we made it to atk");

        battlelogbox.setVisible(true);

        BRatkimg.setImage(setImageView(attacker.getImagePath(false)).getImage()); //Image set with ImageView.getImage()

        BRdefimg.setImage(setImageView(defender.getImagePath(false)).getImage());

        BRatklbl.setText(attacker.get_Name());
        BRatkhplbl.setText(BRatkhplbl.getText() + attacker.get_HP());

        BRdeflbl.setText(defender.get_Name());
        BRdefhplbl.setText(BRdefhplbl.getText() + defender.get_HP());

        attacking = false;
        attacker.set_SpecialAction(false);
        winLoss();
        cancelAttack();
    }

    public boolean isEnemy(Button origin, Button target) {
        return origin != null && target != null
                && getUnitFromNode(origin).get_Type() != getUnitFromNode(target).get_Type();
    }

    public boolean isEnemy(Unit_Normal origin, Unit_Normal target){return origin.get_Type() != target.get_Type();}

    public void setMovesLeft(Unit_Normal unit_normal){
        System.out.print(unit_normal.get_UnitID() + " : " + unit_normal.get_Actions() + " -> ");
        unit_normal.set_Actions(unit_normal.get_Actions()-1);
        numberOfActionsLbl.setText(unit_normal.get_Actions()+"");
        System.out.println(unit_normal.get_Actions());
    }

    public void turnOver(Button btn){
        Unit_Normal unit = getUnitFromNode(btn);
        if (unit.is_Expended()) {
            btn.setGraphic(setImageView(unit.getImagePath(true)));
            buttonVisibility(false);
        }

        int allUnitActions = 0;
        for (Button team : getAllType(unit.get_Type())) {
            if (getUnitFromNode(team).is_Alive()){
                allUnitActions += getUnitFromNode(team).get_Actions();
            }
        }
        System.out.println(allUnitActions);

        if (unit.get_Type() == eTileType.UNIT_P) {
            playerTurn = allUnitActions > 0;
        } else if (unit.get_Type() == eTileType.UNIT_E) {
            enemyTurn = allUnitActions > 0;
        }
        System.out.println("playerTurn:" + playerTurn + " | enemyTurn:" + enemyTurn + " | turnCycle:" + turnCycle);
        if (!playerTurn && !enemyTurn && !turnCycle){
            enemyTurn = true;
            reloadAllImageViews();
            enemyRound();
        } else if (!playerTurn && !enemyTurn && turnCycle){
            turnCycle = false;
            playerTurn = true;
            for (Unit_Normal figure : allUnits) {
                figure.set_Actions(figure.get_MaxActions());
                figure.set_SpecialAction(true);
            }
            reloadAllImageViews();
        }
    }
    public void reloadAllImageViews(){for(Button btn:allButtons){
        Unit_Normal u = getUnitFromNode(btn);
        btn.setGraphic(setImageView(u.getImagePath(false)));
        statVisibility(false);
        buttonVisibility(false);
    }}

    public int generateRandomIntIntRangeNotInt(int min, int max, int notInt) {
        int compareInt = generateRandomIntIntRange(min, max);
        if(compareInt == notInt){
            compareInt = generateRandomIntIntRangeNotInt(min, max, notInt);
        }else{
            return compareInt;
        }
        return compareInt;
    }

    private void createObstacles(){
        int numObstacles = generateRandomIntIntRange(1, 2);
        int notRow = generateRandomIntIntRange(2, 7);
        for (int i = 0; i < numObstacles; i++) {
            int column = generateRandomIntIntRange(2,6);
            int row = generateRandomIntIntRangeNotInt(0,8, notRow);
            if(getNodeFromGridPane(battleGrid, column, row) == null){
                battleGrid.add(setImageView("Data/Rocks" + generateRandomIntIntRange(1,4) + ".png"), column , row);
            }
        }
        battleGrid.add(setImageView("Data/Rocks" + generateRandomIntIntRange(1,4) + ".png"), 8 , generateRandomIntIntRange(0,2));
        battleGrid.add(setImageView("Data/Rocks" + generateRandomIntIntRange(1,4) + ".png"), 8 , generateRandomIntIntRange(3,4));
        battleGrid.add(setImageView("Data/Rocks" + generateRandomIntIntRange(1,4) + ".png"), 8 , generateRandomIntIntRange(5,6));
        battleGrid.add(setImageView("Data/Rocks" + generateRandomIntIntRange(1,4) + ".png"), 8 , generateRandomIntIntRange(7,8));
    }

    private void winLoss() {
        int amountPlrAlive = 0;
        for (Button btn : getAllType(eTileType.UNIT_P)) {
            amountPlrAlive += (getUnitFromNode(btn).is_Alive()) ? 1 : 0;
        }
        imgLoseScreen.setVisible(amountPlrAlive <= 0);

        int amountNmyAlive = 0;
        for (Button btn : getAllType(eTileType.UNIT_E)) {
            amountNmyAlive += (getUnitFromNode(btn).is_Alive()) ? 1 : 0;
        }
        imgVictoryScreen.setVisible(amountNmyAlive <= 0);
    }

    // FUCK YOU

    public void enemyRound(){
        System.out.println("ENEMYROUND");
        for (Button btn : getAllType(eTileType.UNIT_E)) {
            if (getUnitFromNode(btn).is_Alive()){
                enemyTurn(btn);
            }
        }
        System.out.println("ENEMYROUND ENDED!!!!!!!!!!!!!!!!!!");
        turnCycle = true;
        turnOver(focusedUnitBTN[0]);
    }

    public void enemyTurn(Button btn){
        System.out.println("ENEMY: " + btn.getId());
        focusedUnitBTN[0] = btn;
        while (!getUnitFromNode(btn).is_Expended()) {
            System.out.println("Enemy while not expended.");
            if (getUnitFromNode(btn).is_SpecialAction()) {
                System.out.println("Enemy while has special action");
                tryAttacking(btn);
            }
            if (!getUnitFromNode(btn).is_Expended()) {
                tryMoving(btn);
            }
        }
        System.out.println("Enemy:" + btn.getId() + " Turn Over");
    }

    public Button findEnemy() {
        Button target = null;
        int lowestHP = 999;
        for (Button btn : getAllType(eTileType.UNIT_P)) {
            Unit_Normal u = getUnitFromNode(btn);
            if (u.is_Alive()) {
                if (u.get_HP() < lowestHP) {
                    lowestHP = u.get_HP();
                    target = btn;
                }
            }
        }
        return target;
    }

    public int[] movePriority(Button origin, Button destination) {
        int originRow = battleGrid.getRowIndex(origin);
        int originCol = battleGrid.getColumnIndex(origin);
        int destinRow = battleGrid.getRowIndex(destination);
        int destinCol = battleGrid.getColumnIndex(destination);
        boolean alignHorz = originRow == destinRow;
        boolean alignVert = originCol == destinCol;

        int[] orderOfMove = new int[4];
        System.out.println("alignHorz:" + alignHorz + " | alignVert:" + alignVert);

        if (alignVert) {
            if (originRow > destinRow) {
                orderOfMove[0] = 1;
                orderOfMove[3] = 3;
            } else {
                orderOfMove[0] = 3;
                orderOfMove[3] = 1;
            }
            if (headsOrTails()) {
                orderOfMove[1] = 2;
                orderOfMove[2] = 4;
            } else {
                orderOfMove[1] = 4;
                orderOfMove[2] = 2;
            }
        } else if (alignHorz) {
            if (originCol > destinCol) {
                orderOfMove[0] = 4;
                orderOfMove[3] = 2;
            } else {
                orderOfMove[0] = 2;
                orderOfMove[3] = 4;
            }
            if (headsOrTails()) {
                orderOfMove[1] = 1;
                orderOfMove[2] = 3;
            } else {
                orderOfMove[1] = 3;
                orderOfMove[2] = 1;
            }
        } else {
            if (headsOrTails()) {
                if (originRow > destinRow) {
                    orderOfMove[0] = 1;
                    orderOfMove[2] = 3;
                } else {
                    orderOfMove[0] = 3;
                    orderOfMove[2] = 1;
                }
                if (originCol > destinCol) {
                    orderOfMove[1] = 4;
                    orderOfMove[3] = 2;
                } else {
                    orderOfMove[1] = 2;
                    orderOfMove[3] = 4;
                }
            } else {
                if (originCol > destinCol) {
                    orderOfMove[0] = 4;
                    orderOfMove[2] = 2;
                } else {
                    orderOfMove[0] = 2;
                    orderOfMove[2] = 4;
                }
                if (originRow > destinRow) {
                    orderOfMove[1] = 1;
                    orderOfMove[3] = 3;
                } else {
                    orderOfMove[1] = 3;
                    orderOfMove[3] = 1;
                }
            }
        }

        return orderOfMove;
    }

    public void tryAttacking(Button btn) {
        int direction = 0;
        for (boolean b : checkAvailableAdjacent(btn)) {
            if (!b) {
                try {
                    Button adjBtn = (Button) getAdjacent(btn, direction + 1);
                    if (getUnitFromNode(adjBtn).get_Type() == eTileType.UNIT_P){
                        if (adjBtn.getId().equals(findEnemy().getId())) {
                            System.out.println("Attacking TARGET enemy");
                            Battle(getUnitFromNode(btn), getUnitFromNode(findEnemy()));
                            setMovesLeft(getUnitFromNode(btn));
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Cant attack that");
                }

            }
            direction++;
        }
        if (getUnitFromNode(btn).is_SpecialAction()) {
            int directionNC = 0;
            for (boolean b : checkAvailableAdjacent(btn)) {
                if (!b) {
                    try {
                        Button adjBtn = (Button) getAdjacent(btn, directionNC + 1);
                        if (getUnitFromNode(adjBtn).get_Type() == eTileType.UNIT_P){
                            System.out.println("Attacking RANDOM enemy");
                            Battle(getUnitFromNode(btn), getUnitFromNode(getAdjacent(btn, directionNC)));
                            setMovesLeft(getUnitFromNode(btn));
                        }
                    } catch (Exception e) {
                        System.err.println("Cant attack that");
                    }
                }
                directionNC++;
            }
        }
    }

    public void tryMoving(Button btn) {
        System.out.println("Trying to move");
        boolean[] choices = checkAvailableAdjacent(btn);
        System.out.println(Arrays.toString(choices));
        boolean hasChoice = false;
        for (boolean b : choices) {
            if (!hasChoice) {
                hasChoice = b;
            }
        }
        if (hasChoice) {
            int[] request = movePriority(btn, findEnemy());
            System.out.println(Arrays.toString(request));
            boolean moved = false;
            int i = 0;
            while (!moved) {
                int direction = request[i];
                if (choices[direction - 1]) {
                    System.out.println("Moving in " + direction);
                    move(btn, direction);
                    moved = true;
                }
                i++;
            }
        } else {
            getUnitFromNode(btn).set_Actions(0);
        }
    }
}
