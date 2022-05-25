package Game.Board;

import Game.Tiles.Tile;
import Util.AlertBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;


public class Board implements Initializable {
    ArrayList<HBox> panes;
    ArrayList<Tile> tiles;
    Pane player ;
    int playerPos = 0;
    int walkCount = 0;
    boolean allowedToWalk = false;
    int bonusCounter = 0;
    int malusCounter = 0;
    int sautCounter = 0;
    int difinitionCounter = 0;
    int imageCounter = 0;


    @FXML
    private Text stepsCount;

    @FXML
    private Text leftDie;

    @FXML
    private Text rightDie;

    @FXML
    private GridPane bordGrid;

    @FXML
    private Text movesAdd;

    @FXML
    private Text scoreCounter;



    private void initBoad(){
        int counter = 1;

        ArrayList<Integer> bonusTiles = new ArrayList<>();
        ArrayList<Integer> molusTiles = new ArrayList<>();
        ArrayList<Integer> sautTiles = new ArrayList<>();
        ArrayList<Integer> definitionTiles = new ArrayList<>();
        ArrayList<Integer> imageTiles = new ArrayList<>();


        int rand = 0;

        HashMap<Integer, Integer> checkDouble = new HashMap<>();
        int key = 0;
        while (checkDouble.size() < 25) {
            rand = (int)  ((Math.random() * (98 - 1)) + 1);
            checkDouble.put(key, rand);
            key ++;
        }

        for (int i = 0; i < 25; i+=5) {
            bonusTiles.add(checkDouble.get(i));
            molusTiles.add(checkDouble.get(i+1));
            sautTiles.add(checkDouble.get(i+2));
            definitionTiles.add(checkDouble.get(i+3));
            imageTiles.add(checkDouble.get(i+4));
        }

        Collections.sort(bonusTiles);
        Collections.sort(molusTiles);
        Collections.sort(sautTiles);
        Collections.sort(definitionTiles);
        Collections.sort(imageTiles);

//        System.out.println(Arrays.toString(molusTiles.toArray()));
//        System.out.println(Arrays.toString(bonusTiles.toArray()));
//        System.out.println(Arrays.toString(sautTiles.toArray()));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                HBox panei = new HBox();
                panei.setAlignment(Pos.CENTER);
                Tile t;
                if(counter > 1){
                    // create parcours then change the type if necessary
                    panei.setStyle("-fx-background-color:rgba(255, 255, 255, 0.87);-fx-border-color:black");

                    Text number = new Text();
                    number.setText(String.valueOf(counter));
                    panei.getChildren().add(number);


                    t = new Tile(counter,"Parcours","white");

                    // check the end tile:
                    if(counter==100){
                        Text l = new Text("Fin");
                        panei.getChildren().clear();
                        panei.getChildren().add(l);
                        panei.setStyle("-fx-background-color:yellow;-fx-border-color:black");
                        t.setType("Fin");
                        t.setColor("yellow");
                    }else{

                        // creating bonus:
                        if(bonusCounter<5){
                            if((bonusTiles.get(bonusCounter) == counter)){
                                // creating bonus:

                                Text l = new Text("Bonus");
                                panei.getChildren().clear();
                                panei.getChildren().add(l);

                                panei.setStyle("-fx-background-color:green;-fx-border-color:black");
                                t.setType("Bonus");
                                t.setColor("green");
                                bonusCounter++;
                            }
                        }
                        // creating malus
                        if(malusCounter < 5){
                            if(molusTiles.get(malusCounter) == counter){
                                Text l = new Text("Malus");
                                panei.getChildren().clear();
                                panei.getChildren().add(l);

                                panei.setStyle("-fx-background-color:red;-fx-border-color:black");
                                t.setType("Malus");
                                t.setColor("red");
                                malusCounter++;
                            }
                        }
                        // creating saut:
                        if(sautCounter < 5){
                            if(sautTiles.get(sautCounter) == counter){
                                Text l = new Text("Saut");
                                panei.getChildren().clear();
                                panei.getChildren().add(l);

                                panei.setStyle("-fx-background-color:orange;-fx-border-color:black");
                                t.setType("Saut");
                                t.setColor("Orange");
                                sautCounter++;
                            }
                        }
                        // creating definition:
                        if(difinitionCounter < 5){
                            if(definitionTiles.get(difinitionCounter) == counter){
                                Text l = new Text("Definition");
                                panei.getChildren().clear();
                                panei.getChildren().add(l);

                                panei.setStyle("-fx-background-color:#cdb4db;-fx-border-color:black");
                                t.setType("Definition");
                                t.setColor("#cdb4db");
                                difinitionCounter++;
                            }
                        }
                        // creating image:
                        if(imageCounter < 5){
                            if(imageTiles.get(imageCounter) == counter){
                                Text l = new Text("Image");
                                panei.getChildren().clear();
                                panei.getChildren().add(l);

                                panei.setStyle("-fx-background-color:#ffc8dd;-fx-border-color:black");
                                t.setType("Image");
                                t.setColor("#ffc8dd");
                                imageCounter++;
                            }
                        }


                    }



                }else{
                    // creating depart

                    t = new Tile(counter,"Depart","yellow");
                    panei.setStyle("-fx-background-color:FFFF00;-fx-border-color:black");
                    player = new Pane();
                    player.setMinSize(10,10);
                    player.setMaxSize(10,10);
                    player.setStyle("-fx-background-color:blue");
                    panei.getChildren().add(player);
                }
                counter++;
                panei.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
//                        System.out.println(tiles.get(panes.indexOf(event.getSource())).getType());
//                        System.out.println(panes.indexOf(event.getSource()));

                        String type = tiles.get(panes.indexOf(event.getSource())).getType();
                        if(type.equals("Saut")){
                            performeSaut();
                        }else if(type.equals("Image")){
                            performeSaut();
                        }else if(type.equals("Definition")){
                            performeSaut();
                        }else if(type.equals("Malus")){
                            performeSaut();
                        }else if(type.equals("Bonus")){
                            performeSaut();
                        }else{
                            setPlayerPosition((Integer.parseInt(rightDie.getText()) + Integer.parseInt(leftDie.getText())));
                        }
                    }
                });
                t.setP(panei);
                tiles.add(t);
                panes.add(panei);
                bordGrid.add(panei,i,j);
            }


        }

    }

    private void performeSaut(){
        setPlayerPosition((Integer.parseInt(rightDie.getText()) + Integer.parseInt(leftDie.getText())));
        allowedToWalk = true;
        int random = (int)(Math.random()*2);
        int randomSteps = (int)(Math.random()*12);
        if(random == 1){
            // avancer
            AlertBox.display("Case de SAUT","vous avez marché sur la tuile de saut :\n" +
                    "Vous devez sauter "+randomSteps+" tuiles en avant", "#e9c46a");
            setPlayerPosition(randomSteps);

        }else{
            // reculer
            AlertBox.display("Case de SAUT","vous avez marché sur la tuile de saut :\n" +
                    "Vous devez sauter "+randomSteps+" tuiles en arrière", "#e9c46a");
            setPlayerPosition(-randomSteps);
        }

    }

    private void setPlayerPosition(int moves){

        if(!allowedToWalk){
            AlertBox.display("Erreur", "You are not allowed to walk, throw the die first");
        }else{

            if((playerPos+moves) >= 100){
                moves = playerPos - moves;
            }

            Pane pold = panes.get(playerPos);
            Tile tile = tiles.get(playerPos);
            Pane p = panes.get((playerPos+moves));

            p.getChildren().clear();
            p.getChildren().add(player);

            pold.getChildren().clear();
            if(tile.getType().equals("Depart")){
                pold.getChildren().add(new Text("Depart"));
            }else if(tile.getType().equals("Bonus")){
                pold.getChildren().add(new Text("Bonus"));
            }else if(tile.getType().equals("Malus")){
                pold.getChildren().add(new Text("Malus"));
            }else if(tile.getType().equals("Saut")){
                pold.getChildren().add(new Text("Saut"));
            }else if(tile.getType().equals("Image")){
                pold.getChildren().add(new Text("Image"));
            }else if(tile.getType().equals("Definition")){
                pold.getChildren().add(new Text("Definition"));
            }else{
                pold.getChildren().add(new Text(String.valueOf(playerPos + 1)));
            }

            playerPos = (playerPos+moves);
            allowedToWalk = false;

        }


//        if(!allowedToWalk){
//            AlertBox.display("Erreur", "You are not allowed to walk, throw the die first");
//        }else {
//            int somme = (Integer.parseInt(rightDie.getText()) + Integer.parseInt(leftDie.getText()));
//            System.out.println("pos to move to is greater than 100 : "+playerPos+" + "+somme +" = "+(playerPos+somme));
//            walkCount+= somme;
//            System.out.println(walkCount);
//            if(walkCount> 100){
//                somme = walkCount - 100;
//                pos = 99;
//            }
//
//            if((playerPos+somme) >= 100){
//                somme = playerPos+1 - somme;
//            }
//            if ((somme < (pos - playerPos)) || (somme > (pos - playerPos))) {
//                AlertBox.display("Erreur", "You are only allowed to walk " + (Integer.parseInt(rightDie.getText()) + Integer.parseInt(leftDie.getText())));
//            } else {
//                Pane p = panes.get(pos);
//                Pane pold = panes.get(playerPos);
//                Tile tile = tiles.get(playerPos);
//
//
//                p.getChildren().clear();
//                p.getChildren().add(player);
//
//                pold.getChildren().clear();
//                if(tile.getType().equals("Depart")){
//                    pold.getChildren().add(new Text("Depart"));
//                }else if(tile.getType().equals("Bonus")){
//                    pold.getChildren().add(new Text("Bonus"));
//                }else if(tile.getType().equals("Malus")){
//                    pold.getChildren().add(new Text("Malus"));
//                }else if(tile.getType().equals("Saut")){
//                    pold.getChildren().add(new Text("Saut"));
//                }else if(tile.getType().equals("Image")){
//                    pold.getChildren().add(new Text("Image"));
//                }else if(tile.getType().equals("Definition")){
//                    pold.getChildren().add(new Text("Definition"));
//                }else{
//                    pold.getChildren().add(new Text(String.valueOf(playerPos + 1)));
//                }
//
//                playerPos = pos;
////                stepsCount.setText(String.valueOf(walkCount));
//                allowedToWalk = false;
//            }
//
//        }
    }

    public void lancerDieClicked(){
        int randomleft = (int) (Math.random() * 6)+1;
        int randomRight = (int) (Math.random() * 6)+1;

        rightDie.setText(String.valueOf(randomRight));
        leftDie.setText(String.valueOf(randomleft));

        movesAdd.setText("Move "+(randomRight+randomleft)+" Steps");
        allowedToWalk = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panes = new ArrayList<>();
        tiles = new ArrayList<>();
        initBoad();
    }
}
